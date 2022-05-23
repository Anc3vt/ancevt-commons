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
    }
}
