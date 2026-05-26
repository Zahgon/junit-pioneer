/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.jupiter.issue;

import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.toUnmodifiableList;
import static org.junit.platform.engine.TestExecutionResult.Status;
import java.util.List;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Stream;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.engine.reporting.ReportEntry;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.junitpioneer.jupiter.IssueProcessor;
import org.junitpioneer.jupiter.IssueTestCase;
import org.junitpioneer.jupiter.IssueTestSuite;

/**
 * This listener collects the names and results of all tests, which are annotated with the {@link org.junitpioneer.jupiter.Issue @Issue} annotation.
 * After all tests are finished the results are provided to an {@link IssueProcessor} for further processing.
 */
public class IssueExtensionExecutionListener implements TestExecutionListener {

    public static final String REPORT_ENTRY_KEY = "IssueExtension";

    public static final String TIME_REPORT_KEY = "IssueExtensionTimeReport";

    /**
     * This listener will be active as soon as Pioneer is on the class/module path, regardless of whether {@code @Issue} is actually used.
     * To prevent superfluous computation and memory use, we "deactivate" this listener if it is not needed.
     * That's the case when we detect no {@code IssueProcessor} - presumably nobody uses this extension then.
     */
    private final boolean active;

    private final ConcurrentMap<String, IssueTestCaseBuilder> testCases;

    public IssueExtensionExecutionListener() {
        this.active = ServiceLoader.load(IssueProcessor.class).iterator().hasNext();
        this.testCases = new ConcurrentHashMap<>();
    }

    @Override
    public void reportingEntryPublished(TestIdentifier testIdentifier, ReportEntry entry) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public void testPlanExecutionFinished(TestPlan testPlan) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    List<IssueTestSuite> createIssueTestSuites() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private List<IssueTestCase> getIssueTestCases(IssueTestCaseBuilder builder) {
        return List.of(builder.build());
    }

    private List<IssueTestCase> mergeIssueTestCases(List<IssueTestCase> first, List<IssueTestCase> second) {
        return Stream.concat(first.stream(), second.stream()).collect(toUnmodifiableList());
    }
}
