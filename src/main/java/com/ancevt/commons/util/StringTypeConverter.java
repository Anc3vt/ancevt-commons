/**
 * Copyright (C) 2024 the original author or authors.
 * See the notice.md file distributed with this work for additional
 * information regarding copyright ownership.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ancevt.commons.util;

import static java.lang.Byte.parseByte;
import static java.lang.Double.parseDouble;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Short.parseShort;

public class StringTypeConverter {

    private final String sourceString;

    public StringTypeConverter(Object source) {
        sourceString = String.valueOf(source);
    }

    public int toInt() {
        return parseInt(sourceString);
    }

    public int toIntOr(int defaultValue) {
        try {
            return toInt();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public float toFloat() {
        return parseFloat(sourceString);
    }

    public float toFloatOr(float defaultValue) {
        try {
            return toFloat();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public double toDouble() {
        return parseDouble(sourceString);
    }

    public double toDoubleOr(double defaultValue) {
        try {
            return toDouble();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public long toLong() {
        return parseLong(sourceString);
    }

    public long toLongOr(long defaultValue) {
        try {
            return toLong();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public byte toByte() {
        return parseByte(sourceString);
    }

    public byte toByteOr(byte defaultValue) {
        try {
            return toByte();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public short toShort() {
        return parseShort(sourceString);
    }

    public short toShortOr(short defaultValue) {
        try {
            return toShort();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean toBoolean() {
        return "true".equals(sourceString);
    }

    public String getSourceString() {
        return sourceString;
    }

    public static StringTypeConverter convert(Object source) {
        return new StringTypeConverter(source);
    }

    public static void main(String[] args) {
        int i = convert("1").toIntOr(0);
        System.out.println(i);
    }
}
