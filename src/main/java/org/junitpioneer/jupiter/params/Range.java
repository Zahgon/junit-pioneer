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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An iterator for numerical ranges, used as the backing logic for {@link RangeSourceArgumentsProvider}.
 * @param <N> The numerical type used by the range.
 */
abstract class Range<N extends Number & Comparable<N>> implements Iterator<N> {

    private final N from;

    private final N to;

    private final N step;

    private final boolean closed;

    private N current;

    private final int sign;

    private final N zero;

    Range(N from, N to, N step, boolean closed, N zero) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.closed = closed;
        this.zero = zero;
        current = null;
        sign = step.compareTo(getZero());
    }

    /**
     * Asserts the range is valid.
     * @throws IllegalArgumentException if the range is not valid
     */
    void validate() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    boolean isValidDescending() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    N getStep() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    N getCurrent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * The next value in the range. Calling {@link #next()} will return this value and advance the iterator to it.
     */
    abstract N nextValue();

    private N getNextValue() {
        if (current == null) {
            return from;
        }
        return nextValue();
    }

    /**
     * The value of the no-op "zero", illegal step in terms of N
     */
    private N getZero() {
        return zero;
    }

    @Override
    public boolean hasNext() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public N next() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
