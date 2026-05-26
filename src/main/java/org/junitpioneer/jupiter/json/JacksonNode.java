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

import java.io.UncheckedIOException;
import java.lang.reflect.Type;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * A {@link Node} implementation for Jackson 2.
 */
class JacksonNode implements Node {

    private final ObjectMapper objectMapper;

    private final JsonNode node;

    JacksonNode(ObjectMapper objectMapper, JsonNode node) {
        this.objectMapper = objectMapper;
        this.node = node;
    }

    @Override
    public boolean isArray() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Stream<Node> elements() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public <T> T toType(Type type) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Optional<Node> getNode(String name) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public Object value(Type typeHint) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }
}
