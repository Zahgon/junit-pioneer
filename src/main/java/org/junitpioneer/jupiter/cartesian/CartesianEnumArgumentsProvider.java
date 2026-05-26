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

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toSet;
import java.lang.reflect.Parameter;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.platform.commons.PreconditionViolationException;
import org.junit.platform.commons.support.AnnotationSupport;

/**
 * This is basically an enhanced copy of Jupiter's {@code EnumArgumentsProvider},
 * except it does NOT support {@code @ParameterizedTest} and implements
 * {@link CartesianParameterArgumentsProvider} for use with {@code @CartesianTest}.
 */
class CartesianEnumArgumentsProvider<E extends Enum<E>> implements CartesianParameterArgumentsProvider<E> {

    @Override
    public Stream<E> provideArguments(ExtensionContext context, Parameter parameter) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Set<E> getEnumConstants(CartesianTest.Enum enumSource, Class<?> parameterType) {
        Class<E> enumClass = determineEnumClass(enumSource, parameterType);
        return EnumSet.allOf(enumClass);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private Class<E> determineEnumClass(CartesianTest.Enum enumSource, Class<?> parameterType) {
        Class enumClass = enumSource.value();
        if (enumClass.equals(NullEnum.class)) {
            if (!Enum.class.isAssignableFrom(parameterType))
                throw new PreconditionViolationException(String.format("Parameter of type %s must reference an Enum type", parameterType));
            enumClass = parameterType;
        }
        return enumClass;
    }

    enum NullEnum {
    }
}
