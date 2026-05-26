/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.jupiter.json;

class JsonConverterProvider {

    private static final boolean JACKSON_PRESENT = isJacksonObjectMapperClassPresent();

    static boolean isJacksonObjectMapperClassPresent() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    static JsonConverter getJsonConverter(String objectMapperId) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
