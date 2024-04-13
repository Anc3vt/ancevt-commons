package com.ancevt.commons.string;

public class ConvertableString {

    private final String string;

    private ConvertableString(String string) {
        this.string = string;
    }

    public static ConvertableString convert(String string) {
        return new ConvertableString(string);
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

    public int toInt(int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public long toLong(long defaultValue) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public byte toByte(byte defaultValue) {
        try {
            return Byte.parseByte(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public short toShort(short defaultValue) {
        try {
            return Short.parseShort(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public float toFloat(float defaultValue) {
        try {
            return Float.parseFloat(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public double toDouble(double defaultValue) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public boolean toBoolean(boolean defaultValue) {
        return Boolean.parseBoolean(string);
    }

    public char toChar(char defaultValue) {
        if (!string.isEmpty()) {
            return string.charAt(0);
        } else {
            return defaultValue;
        }
    }
}
