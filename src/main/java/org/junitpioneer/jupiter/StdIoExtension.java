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
import static org.junitpioneer.internal.PioneerAnnotationUtils.findClosestEnclosingAnnotation;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionConfigurationException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

class StdIoExtension implements ParameterResolver, BeforeEachCallback, AfterEachCallback {

    static final String SEPARATOR = System.getProperty("line.separator");

    private static final Namespace NAMESPACE = Namespace.create(StdIoExtension.class);

    private static final String SYSTEM_IN_KEY = "StdIo_System_In";

    private static final String SYSTEM_OUT_KEY = "StdIo_System_Out";

    private static final String SYSTEM_ERR_KEY = "StdIo_System_Err";

    private static final String STD_IN_KEY = "StdIo_Std_In";

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private StdOut prepareStdOut(ExtensionContext context) {
        storeStdOut(context);
        return createOut();
    }

    private void storeStdOut(ExtensionContext context) {
        //NOSONAR never writing to System.out, only storing it
        context.getStore(NAMESPACE).put(SYSTEM_OUT_KEY, System.out);
    }

    private StdOut createOut() {
        StdOut out = new StdOut();
        System.setOut(new PrintStream(out));
        return out;
    }

    private StdIn createSwapStoreStdIn(ExtensionContext context, String[] source) {
        StdIn newStdIn = new StdIn(source);
        swapAndStoreIn(context, newStdIn);
        return newStdIn;
    }

    private void swapAndStoreIn(ExtensionContext context, StdIn stdIn) {
        //NOSONAR never reading from System.in, only storing it
        context.getStore(NAMESPACE).put(SYSTEM_IN_KEY, System.in);
        context.getStore(NAMESPACE).put(STD_IN_KEY, stdIn);
        //NOSONAR required to redirect output
        System.setIn(stdIn);
    }

    private StdErr prepareStdErr(ExtensionContext context) {
        storeStdErr(context);
        return createErr();
    }

    private void storeStdErr(ExtensionContext context) {
        //NOSONAR never writing to System.err, only storing it
        context.getStore(NAMESPACE).put(SYSTEM_ERR_KEY, System.err);
    }

    private StdErr createErr() {
        StdErr err = new StdErr();
        System.setErr(new PrintStream(err));
        return err;
    }

    @Override
    public void beforeEach(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void afterEach(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
