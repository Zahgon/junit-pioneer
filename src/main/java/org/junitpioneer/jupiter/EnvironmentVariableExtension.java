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

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Function;
import org.junit.jupiter.api.extension.ExtensionContext;

class EnvironmentVariableExtension extends AbstractEntryBasedExtension<String, String, ClearEnvironmentVariable, SetEnvironmentVariable, RestoreEnvironmentVariables> {

    // package visible to make accessible for tests
    static final AtomicBoolean REPORTED_WARNING = new AtomicBoolean(false);

    static final String WARNING_KEY = EnvironmentVariableExtension.class.getSimpleName();

    static final String WARNING_VALUE = "This extension uses reflection to access and modify JDK internals, which is fragile." + "Have a look at the documentation for further details:" + "https://junit-pioneer.org/docs/environment-variables/#warnings-for-reflective-access";

    @Override
    protected Function<ClearEnvironmentVariable, String> clearKeyMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Function<SetEnvironmentVariable, String> setKeyMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Function<SetEnvironmentVariable, String> setValueMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void reportWarning(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void clearEntry(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected String getEntry(String key) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void setEntry(String key, String value) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * This implementation uses the "Post swap" strategy, returning a clone of the environment variables
     * which will be restored in {@link AbstractEntryBasedExtension#prepareToExitRestorableContext}.
     *
     * <p>See {@link AbstractEntryBasedExtension#prepareToEnterRestorableContext} for more details.</p>
     *
     * @return A clone of the current environment variables, as a {@code Properties} object.
     */
    @Override
    protected Properties prepareToEnterRestorableContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void prepareToExitRestorableContext(Properties restoreMe) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
