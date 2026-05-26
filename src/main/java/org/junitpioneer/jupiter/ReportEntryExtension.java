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
import static org.junitpioneer.jupiter.ReportEntry.PublishCondition.ALWAYS;
import static org.junitpioneer.jupiter.ReportEntry.PublishCondition.ON_ABORTED;
import static org.junitpioneer.jupiter.ReportEntry.PublishCondition.ON_FAILURE;
import static org.junitpioneer.jupiter.ReportEntry.PublishCondition.ON_SUCCESS;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junitpioneer.internal.PioneerAnnotationUtils;
import org.junitpioneer.internal.PioneerUtils;

class ReportEntryExtension implements TestWatcher, BeforeEachCallback, InvocationInterceptor {

    private static final ExtensionContext.Namespace NAMESPACE = ExtensionContext.Namespace.create(ReportEntryExtension.class);

    private static final String KEY = "ReportEntry";

    @Override
    public void beforeEach(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Stream<ReportEntry> findAnnotations(ExtensionContext context) {
        return PioneerAnnotationUtils.findAllEnclosingRepeatableAnnotations(context, ReportEntry.class);
    }

    private static void verifyReportEntry(ExtensionContext context, ReportEntry entry) {
        verifyParameterCount(context, entry);
        verifyKeyValueAreNotBlank(entry);
        verifyKeyNotParameterized(entry);
    }

    private static void verifyParameterCount(ExtensionContext context, ReportEntry entry) {
        if (hasTestParameterVariables(entry.value())) {
            int highest = getHighestNumberedParameter(entry);
            if (context.getRequiredTestMethod().getParameterCount() <= highest) {
                String message = "Report entry contains unresolved variable(s): { key=\"%s\" value=\"%s\" }";
                throw new ExtensionConfigurationException(format(message, entry.key(), entry.value()));
            }
        }
    }

    private static int getHighestNumberedParameter(ReportEntry entry) {
        int highest = 0;
        Matcher matcher = Pattern.compile("\\{[0-9]+}").matcher(entry.value());
        while (matcher.find()) highest = Math.max(getVariableNumber(entry, matcher), highest);
        return highest;
    }

    private static int getVariableNumber(ReportEntry entry, Matcher matcher) {
        return Integer.parseInt(entry.value().substring(matcher.start() + 1, matcher.end() - 1));
    }

    private static void verifyKeyValueAreNotBlank(ReportEntry entry) {
        if (entry.key().isEmpty() || entry.value().isEmpty()) {
            String message = "Report entries can't have blank key or value: { key=\"%s\", value=\"%s\" }";
            throw new ExtensionConfigurationException(format(message, entry.key(), entry.value()));
        }
    }

    private static void verifyKeyNotParameterized(ReportEntry entry) {
        if (hasTestParameterVariables(entry.key())) {
            String message = "Report entry can not have variables in the key: { key=\"%s\" value=\"%s\" }";
            throw new ExtensionConfigurationException(format(message, entry.key(), entry.value()));
        }
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void testSuccessful(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private void publishOnConditions(ExtensionContext context, ReportEntry.PublishCondition... conditions) {
        findAnnotations(context).filter(entry -> Arrays.asList(conditions).contains(entry.when())).forEach(entry -> context.publishReportEntry(entry.key(), parseVariables(entry.value(), context)));
    }

    private String parseVariables(String value, ExtensionContext context) {
        if (!hasTestParameterVariables(value))
            return value;
        String parsed = value;
        List<?> list = context.getStore(NAMESPACE).get(KEY, List.class);
        for (int i = 0; i < list.size(); i++) {
            parsed = parsed.replaceAll("\\{" + i + "}", PioneerUtils.nullSafeToString(list.get(i)));
        }
        return parsed;
    }

    private static boolean hasTestParameterVariables(String value) {
        return value.matches(".*\\{[0-9]+}.*");
    }

    @Override
    public void interceptTestTemplateMethod(Invocation<Void> invocation, ReflectiveInvocationContext<Method> invocationContext, ExtensionContext extensionContext) throws Throwable {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
