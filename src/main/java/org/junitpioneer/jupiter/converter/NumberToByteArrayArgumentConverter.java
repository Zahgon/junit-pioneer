/*
 * Copyright 2016-2023 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the Eclipse Public License v2.0 which
 * accompanies this distribution and is available at
 *
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.junitpioneer.jupiter.converter;

import static java.lang.String.format;
import static org.junitpioneer.jupiter.converter.NumberToByteArrayConversion.ByteOrder.BIG_ENDIAN;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.TypedArgumentConverter;
import org.junit.jupiter.params.support.AnnotationConsumer;

class NumberToByteArrayArgumentConverter extends TypedArgumentConverter<Number, byte[]> implements AnnotationConsumer<NumberToByteArrayConversion> {

    private ByteOrder order;

    public NumberToByteArrayArgumentConverter() {
        super(Number.class, byte[].class);
    }

    @Override
    public void accept(NumberToByteArrayConversion annotation) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    @Override
    protected byte[] convert(Number source) throws ArgumentConversionException {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private ByteOrder getByteOrder(NumberToByteArrayConversion annotation) {
        if (annotation.order() == BIG_ENDIAN) {
            return ByteOrder.BIG_ENDIAN;
        } else {
            return ByteOrder.LITTLE_ENDIAN;
        }
    }
}
