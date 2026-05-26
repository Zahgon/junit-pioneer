/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.jupiter.params;

import static java.lang.String.format;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.platform.commons.support.AnnotationSupport;
import org.opentest4j.TestAbortedException;

class DisableIfArgumentExtension implements InvocationInterceptor {

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static void checkRequiredAnnotations(Method testMethod) {
        if (AnnotationSupport.findAnnotation(testMethod, DisableIfAnyArgument.class).isEmpty() && AnnotationSupport.findAnnotation(testMethod, DisableIfAllArguments.class).isEmpty() && AnnotationSupport.findRepeatableAnnotations(testMethod, DisableIfArgument.class).isEmpty()) {
            throw new ExtensionConfigurationException("Required at least one of the following: @DisableIfArgument, @DisableIfAllArguments, @DisableIfAnyArgument but found none.");
        }
    }

    private static void verifyNonEmptyInputs(DisableIfAllArguments annotation) {
        if (annotation.contains().length > 0 == annotation.matches().length > 0)
            throw invalidInputs(DisableIfAllArguments.class);
    }

    private static void verifyNonEmptyInputs(DisableIfAnyArgument annotation) {
        if (annotation.contains().length > 0 == annotation.matches().length > 0)
            throw invalidInputs(DisableIfAnyArgument.class);
    }

    private static void verifyNonEmptyInputs(DisableIfArgument annotation) {
        if (annotation.contains().length > 0 == annotation.matches().length > 0)
            throw invalidInputs(DisableIfArgument.class);
    }

    private static ExtensionConfigurationException invalidInputs(Class<?> annotationClass) {
        return new ExtensionConfigurationException(format("%s requires that either `contains` or `matches` is set.", annotationClass.getSimpleName()));
    }

    private Object findArgument(Method testMethod, List<Object> arguments, DisableIfArgument annotation, int index) {
        if (!annotation.name().isEmpty() && annotation.index() > -1)
            throw new ExtensionConfigurationException("Using both name and index parameter targeting in a single @DisableIfArgument is not permitted.");
        if (!annotation.name().isEmpty()) {
            // get argument by name only works if information is present
            if (testMethod.getParameters()[0].isNamePresent())
                return findArgumentByName(testMethod, arguments, annotation.name());
            else
                throw new ParameterResolutionException(format("%s: Could not resolve parameter by name (%s).", testMethod.getName(), annotation.name()));
        }
        // get argument by explicit index (if present)
        if (annotation.index() > -1)
            return findArgumentByIndex(arguments, annotation.index());
        // get argument by annotation index (implicit)
        return arguments.get(index);
    }

    private Object findArgumentByName(Method testMethod, List<Object> arguments, String name) {
        return arguments.get(findParameterIndexFromName(testMethod, name));
    }

    private int findParameterIndexFromName(Method testMethod, String name) {
        Parameter[] parameters = testMethod.getParameters();
        for (int i = 0; i < parameters.length; i++) if (parameters[i].getName().equals(name))
            return i;
        throw new ParameterResolutionException(format("Could not find parameter named %s in test %s", name, testMethod));
    }

    private Object findArgumentByIndex(List<Object> arguments, int index) {
        verifyValidIndex(arguments, index);
        return arguments.get(index);
    }

    private void verifyValidIndex(List<Object> arguments, int index) {
        if (index > arguments.size())
            throw new ExtensionConfigurationException(format("Annotation has invalid index [%s], should be less than %s", index, arguments.size()));
    }

    private static class ArgumentChecker {

        private final List<Object> arguments;

        private final boolean checkAny;

        private ArgumentChecker(List<Object> arguments, boolean checkAny) {
            this.arguments = arguments;
            this.checkAny = checkAny;
        }

        static ArgumentChecker checkAll(List<Object> arguments) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static ArgumentChecker checkAny(List<Object> arguments) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        static ArgumentChecker check(Object argument) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void matches(String[] matches) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private void matchAll(Predicate<Object> check) {
            if (arguments.stream().allMatch(check))
                throw new TestAbortedException("All arguments matched one or more regular expression(s) from the `matches` array.");
        }

        private void matchAny(Predicate<Object> check) {
            if (arguments.stream().anyMatch(check))
                throw new TestAbortedException("One or more arguments matched a regular expression from the `matches` array.");
        }

        public void contains(String[] contains) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private void containsAll(Predicate<Object> check) {
            if (arguments.stream().allMatch(check))
                throw new TestAbortedException("All arguments contained one or more value(s) from the `contains` array.");
        }

        private void containsAny(Predicate<Object> check) {
            if (arguments.stream().anyMatch(check))
                throw new TestAbortedException("One or more arguments contained a value from the `contains` array.");
        }
    }
}
