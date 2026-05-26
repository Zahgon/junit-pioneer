/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.jupiter;

import static java.lang.String.format;
import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContextProvider;
import org.junit.platform.commons.support.AnnotationSupport;
import org.junitpioneer.internal.PioneerAnnotationUtils;
import org.junitpioneer.internal.TestNameFormatter;
import org.opentest4j.MultipleFailuresError;
import org.opentest4j.TestAbortedException;

class RetryingTestExtension implements TestTemplateInvocationContextProvider, TestExecutionExceptionHandler {

    private static final Namespace NAMESPACE = Namespace.create(RetryingTestExtension.class);

    @Override
    public boolean supportsTestTemplate(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static FailedTestRetrier retrierFor(ExtensionContext context) {
        var testMethod = context.getRequiredTestMethod();
        return context.getStore(NAMESPACE).computeIfAbsent(testMethod.toString(), __ -> FailedTestRetrier.createFor(testMethod, context), FailedTestRetrier.class);
    }

    private static class FailedTestRetrier implements Iterator<RetryingTestInvocationContext> {

        private final int maxRetries;

        private final int minSuccess;

        private final int suspendForMs;

        private final Class<? extends Throwable>[] expectedExceptions;

        private final List<TestAbortedException> seenExceptions;

        private final TestNameFormatter formatter;

        private int retriesSoFar;

        private int exceptionsSoFar;

        private boolean seenFailedAssumption;

        private boolean seenUnexpectedException;

        private FailedTestRetrier(int maxRetries, int minSuccess, int suspendForMs, Class<? extends Throwable>[] expectedExceptions, TestNameFormatter formatter) {
            this.maxRetries = maxRetries;
            this.minSuccess = minSuccess;
            this.suspendForMs = suspendForMs;
            this.expectedExceptions = expectedExceptions;
            this.seenExceptions = new ArrayList<>();
            this.retriesSoFar = 0;
            this.exceptionsSoFar = 0;
            this.formatter = formatter;
        }

        static FailedTestRetrier createFor(Method test, ExtensionContext context) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        <E extends Throwable> void failed(E exception) throws E {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        private boolean expectedException(Throwable exception) {
            // if not expected exceptions were specified, all are expected
            if (expectedExceptions.length == 0)
                return true;
            return Arrays.stream(expectedExceptions).anyMatch(type -> type.isInstance(exception));
        }

        private void suspendFor(int millis) {
            if (millis < 1) {
                return;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(millis);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                throw new IllegalStateException("Thread interrupted during retry suspension.", ex);
            }
        }

        private boolean isFirstExecution() {
            return retriesSoFar == 0;
        }

        @Override
        public boolean hasNext() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @Override
        public RetryingTestInvocationContext next() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
