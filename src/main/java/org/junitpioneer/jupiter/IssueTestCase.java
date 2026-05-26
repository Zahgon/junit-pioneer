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

import static java.util.Objects.requireNonNull;
import java.util.Objects;
import java.util.Optional;
import org.junit.platform.engine.TestExecutionResult.Status;

/**
 * Represents the execution result of test method, which is annotated with {@link Issue}.
 *
 * <p>Once Pioneer baselines against Java 17, this will be a record.</p>
 *
 * @since 1.1
 * @see Issue
 * @see IssueProcessor
 */
public final class IssueTestCase {

    private static final String NO_RESULT_EXCEPTION_MESSAGE = "The test case result should never be null (Nicolai thinks). If you see this exception, he was wrong - please open an issue at https://github.com/junit-pioneer/junit-pioneer/issues/new/choose .";

    private final String testId;

    private final Status result;

    // no `OptionalLong` because its API doesn't have `map`
    private final Optional<Long> elapsedTime;

    /**
     * @param testId Unique name of the test method
     * @param result Result of the execution
     * @param elapsedTime The (optional) duration of test execution
     */
    public IssueTestCase(String testId, Status result, Optional<Long> elapsedTime) {
        this.testId = requireNonNull(testId);
        this.result = requireNonNull(result, NO_RESULT_EXCEPTION_MESSAGE);
        this.elapsedTime = elapsedTime;
    }

    /**
     * @param testId Unique name of the test method
     * @param result Result of the execution
     */
    public IssueTestCase(String testId, Status result) {
        this(testId, result, Optional.empty());
    }

    /**
     * @param testId Unique name of the test method
     * @param result Result of the execution
     * @param elapsedTime The duration of test execution
     */
    public IssueTestCase(String testId, Status result, long elapsedTime) {
        this(testId, result, Optional.of(elapsedTime));
    }

    /**
     * Returns the unique name of the test method.
     *
     * @return Unique name of the test method
     */
    public String testId() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the result of the test methods' execution.
     *
     * @return Result of the test methods' execution.
     */
    public Status result() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Returns the elapsed time since the start of test methods' execution in milliseconds.
     *
     * @return The elapsed time in ms.
     */
    public Optional<Long> elapsedTime() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public boolean equals(Object o) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public int hashCode() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
