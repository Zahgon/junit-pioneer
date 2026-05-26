/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.vintage;

import static java.lang.String.format;
import static org.junit.platform.commons.support.AnnotationSupport.findAnnotation;
import java.util.Optional;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.TestExecutionExceptionHandler;
import org.opentest4j.AssertionFailedError;

/**
 * This extension implements the expected exception behavior of {@link Test @Test}, where a test only passes if it throws
 * an exception of the specified type.
 */
class ExpectedExceptionExtension implements TestExecutionExceptionHandler, AfterTestExecutionCallback {

    /*
	 * This extension implements the exception handler callback to compare the thrown exception
	 * to what was expected. The after test callback (which is called later) builds on
	 * the results of that check and fails the test if an exception was expected but not thrown.
	 */
    static final String EXPECTED_EXCEPTION_WAS_NOT_THROWN = "Expected exception %s was not thrown.";

    private static final Namespace NAMESPACE = Namespace.create(ExpectedExceptionExtension.class);

    private static final String KEY = "ExceptionWasThrown";

    @Override
    public void handleTestExecutionException(ExtensionContext context, Throwable throwable) throws Throwable {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    // vintage @Test is deprecated (not for removal)
    @SuppressWarnings("deprecation")
    private static Optional<? extends Class<? extends Throwable>> expectedException(ExtensionContext context) {
        return findAnnotation(context.getElement(), Test.class).map(Test::expected).filter(exceptionType -> exceptionType != Test.None.class);
    }

    private static void storeExceptionStatus(ExtensionContext context, EXCEPTION thrown) {
        context.getStore(NAMESPACE).put(KEY, thrown);
    }

    private static EXCEPTION loadExceptionStatus(ExtensionContext context) {
        EXCEPTION thrown = context.getStore(NAMESPACE).get(KEY, EXCEPTION.class);
        if (thrown == null) {
            return EXCEPTION.WAS_NOT_THROWN;
        } else {
            return thrown;
        }
    }

    private enum EXCEPTION {

        WAS_NOT_THROWN, WAS_THROWN_AS_EXPECTED, WAS_THROWN_NOT_AS_EXPECTED
    }
}
