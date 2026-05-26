/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.internal;

import static org.junit.platform.commons.support.ReflectionSupport.findMethod;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Pioneer-internal utility class.
 * DO NOT USE THIS CLASS - IT MAY CHANGE SIGNIFICANTLY IN ANY MINOR UPDATE.
 *
 * @see PioneerAnnotationUtils
 */
public class PioneerUtils {

    private PioneerUtils() {
        // private constructor to prevent instantiation of utility class
    }

    /**
     * A {@link java.util.stream.Collectors#toSet() toSet} collector that throws an {@link IllegalStateException}
     * on duplicate elements (according to {@link Object#equals(Object) equals}).
     */
    public static <T> Collector<T, Set<T>, Set<T>> distinctToSet() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private static <T> void addButThrowIfDuplicate(Set<T> set, T element) {
        boolean newElement = set.add(element);
        if (!newElement) {
            throw new IllegalStateException("Duplicate element '" + element + "'.");
        }
    }

    /**
     * Find the first {@link Method} of the supplied class or interface that
     * meets the specified criteria, beginning with the specified class or
     * interface and traversing its enclosing classes until such a method is
     * found or the top level class is reached.
     *
     * <p>The algorithm does not search for methods in {@link java.lang.Object}.</p>
     *
     * @param clazz the class or interface in which to find the method; never {@code null}
     * @param methodName the name of the method to find; never {@code null} or empty
     * @param parameterTypes the types of parameters accepted by the method, if any;
     * never {@code null}
     * @return an {@code Optional} containing the method found; never {@code null}
     * but potentially empty if no such method could be found
     * @see org.junit.platform.commons.support.ReflectionSupport#findMethod(Class, String, Class...)
     */
    public static Optional<Method> findMethodCurrentOrEnclosing(Class<?> clazz, String methodName, Class<?>... parameterTypes) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Find all (parent) {@code ExtensionContext}s via {@link ExtensionContext#getParent()}.
     *
     * @param context the context for which to find all (parent) contexts; never {@code null}
     * @return a list of all contexts, "outwards" in the {@link ExtensionContext#getParent() getParent}-order,
     *         beginning with the given context; never {@code null} or empty
     */
    public static List<ExtensionContext> findAllContexts(ExtensionContext context) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static String nullSafeToString(Object object) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * Replaces all primitive types with the appropriate wrapper types.
     *
     * @return the wrapped class of the primitive type, or the passed class
     * @see MethodType#wrap()
     */
    @SuppressWarnings("unchecked")
    public static <T> Class<T> wrap(Class<T> clazz) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static List<List<?>> cartesianProduct(List<List<?>> lists) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Locale createLocale(String language, String country, String variant) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Locale createLocale(String language, String country) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public static Locale createLocale(String language) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
