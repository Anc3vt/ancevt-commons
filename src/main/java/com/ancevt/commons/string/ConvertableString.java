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
package com.ancevt.commons.string;

import lombok.Getter;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Function;
import java.util.function.IntSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class ConvertableString {

    @Getter
    private final String string;

    private ConvertableString(String string) {
        this.string = string;
    }

    public static ConvertableString convert(String string) {
        return new ConvertableString(string);
    }

    public boolean isNull() {
        return string == null;
    }

    public int toInt() {
        return Integer.parseInt(string);
    }

    public long toLong() {
        return Long.parseLong(string);
    }

    public byte toByte() {
        return Byte.parseByte(string);
    }

    public short toShort() {
        return Short.parseShort(string);
    }

    public float toFloat() {
        return Float.parseFloat(string);
    }

    public double toDouble() {
        return Double.parseDouble(string);
    }

    public boolean toBoolean() {
        return Boolean.parseBoolean(string);
    }

    public char toChar() {
        return string.charAt(0);
    }

    public int toIntOrDefault(int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long toLongOrDefault(long defaultValue) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public byte toByteOrDefault(byte defaultValue) {
        try {
            return Byte.parseByte(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public short toShortOrDefault(short defaultValue) {
        try {
            return Short.parseShort(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public float toFloatOrDefault(float defaultValue) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double toDoubleOrDefault(double defaultValue) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean toBooleanOrDefault(boolean defaultValue) {
        return Boolean.parseBoolean(string);
    }

    public char toChar(char defaultValue) {
        if (!string.isEmpty()) {
            return string.charAt(0);
        } else {
            return defaultValue;
        }
    }

    public int toIntOrSupply(IntSupplier supplier) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return supplier.getAsInt();
        }
    }

    public long toLongOrSupply(LongSupplier supplier) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return supplier.getAsLong();
        }
    }

    public byte toByteOrSupply(Supplier<Byte> supplier) {
        try {
            return Byte.parseByte(string);
        } catch (NumberFormatException e) {
            return supplier.get();
        }
    }

    public short toShortOrSupply(Supplier<Short> supplier) {
        try {
            return Short.parseShort(string);
        } catch (NumberFormatException e) {
            return supplier.get();
        }
    }

    public float toFloatOrSupply(Supplier<Float> supplier) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return supplier.get();
        }
    }

    public double toDoubleOrSupply(DoubleSupplier supplier) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return supplier.getAsDouble();
        }
    }

    public boolean toBooleanOrSupply(BooleanSupplier supplier) {
        try {
            return Boolean.parseBoolean(string);
        } catch (Exception ex) {
            return supplier.getAsBoolean();
        }
    }

    public char toCharOrSupply(Supplier<Character> supplier) {
        if (!string.isEmpty()) {
            return string.charAt(0);
        } else {
            return supplier.get();
        }
    }


    public int toIntOrCompute(ToIntFunction<String> fn) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return fn.applyAsInt(string);
        }
    }

    public long toLongOrCompute(ToLongFunction<String> fn) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return fn.applyAsLong(string);
        }
    }

    public byte toByteOrCompute(Function<String, Byte> fn) {
        try {
            return Byte.parseByte(string);
        } catch (NumberFormatException e) {
            return fn.apply(string);
        }
    }

    public short toShortOrCompute(Function<String, Short> fn) {
        try {
            return Short.parseShort(string);
        } catch (NumberFormatException e) {
            return fn.apply(string);
        }
    }

    public float toFloatOrCompute(Function<String, Float> fn) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return fn.apply(string);
        }
    }

    public double toDoubleOrCompute(ToDoubleFunction<String> fn) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return fn.applyAsDouble(string);
        }
    }

    public boolean toBooleanOrCompute(Function<String, Boolean> fn) {
        try {
            return Boolean.parseBoolean(string);
        } catch (Exception ex) {
            return fn.apply(string);
        }
    }

    public char toCharOrCompute(Function<String, Character> fn) {
        if (!string.isEmpty()) {
            return string.charAt(0);
        } else {
            return fn.apply(string);
        }
    }


}
