/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.jupiter.cartesian;

import static java.util.stream.Collectors.toUnmodifiableList;
import java.lang.reflect.Array;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.support.AnnotationConsumer;
import org.junit.platform.commons.PreconditionViolationException;

/**
 * This is a slightly modified copy of Jupiter's {@code ValueArgumentsProvider},
 * except it does NOT support {@code @ParameterizedTest} and implements {@link CartesianArgumentsProvider}
 * for use with {@code @CartesianTest}.
 */
class CartesianValueArgumentsProvider implements CartesianParameterArgumentsProvider<Object>, AnnotationConsumer<CartesianTest.Values> {

    private Object[] arguments;

    @Override
    public void accept(CartesianTest.Values source) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Stream<Object> provideArguments(ExtensionContext context, Parameter parameter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
