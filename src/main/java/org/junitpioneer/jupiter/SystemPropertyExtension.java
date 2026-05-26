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

import java.util.Properties;
import java.util.function.Function;

class SystemPropertyExtension extends AbstractEntryBasedExtension<String, String, ClearSystemProperty, SetSystemProperty, RestoreSystemProperties> {

    @Override
    protected Function<ClearSystemProperty, String> clearKeyMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Function<SetSystemProperty, String> setKeyMapper() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected Function<SetSystemProperty, String> setValueMapper() {
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
     * This implementation uses the "Preemptive swap" strategy.
     *
     * <p>Since {@link Properties} allows a wrapped default instance and Object values,
     * cloning is difficult:</p>
     *
     * <ul>
     * <li>It is difficult to tell which values are defaults and which are "top level",
     * thus a clone might contain the same effective values, but be flattened without defaults.</li>
     * <li>Object values in a wrapped default instance cannot be accessed without reflection.</li>
     * </ul>
     *
     * <p>The "Preemptive swap" strategy ensure that the original Properties are restored, however
     * complex they were. Any artifacts resulting from a flattened default structure are limited
     * to the context of the test.</p>
     *
     * <p>See {@link AbstractEntryBasedExtension#prepareToEnterRestorableContext} for more details.</p>
     *
     * @return The original {@link System#getProperties} object
     */
    @Override
    protected Properties prepareToEnterRestorableContext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected void prepareToExitRestorableContext(Properties properties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * A clone of the String values of the passed {@code Properties}, including defaults.
     *
     * <p>The clone will have the same effective values, but may not use the same nested
     * structure as the original. Object values, which are technically possible,
     * are not included in the clone.</p>
     *
     * @param original {@code Properties} to be cloned.
     * @return A new {@code Properties} instance containing the same effective entries as the original.
     */
    static Properties createEffectiveClone(Properties original) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
