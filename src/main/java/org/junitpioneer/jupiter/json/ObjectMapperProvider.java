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

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * Service interface for providing a custom {@link com.fasterxml.jackson.databind.ObjectMapper} instance at runtime.
 * The default implementation doesn't register any additional Jackson modules.
 *
 * @see com.fasterxml.jackson.databind.Module
 */
public interface ObjectMapperProvider {

    ObjectMapper get();

    default ObjectMapper getLenient() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    String id();
}
