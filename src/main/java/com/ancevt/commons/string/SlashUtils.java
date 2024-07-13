package com.ancevt.commons.string;

import java.io.File;

public class SlashUtils {

    public static String makeEndWithSlash(String source) {
        return source.endsWith("/") ? source : source + "/";
    }

    public static String makeNotEndWithSlash(String source) {
        return source.endsWith("/") ? source.substring(0, source.length() - 1) : source;
    }

    public static String makeEndWithBackslash(String source) {
        return source.endsWith("\\") ? source : source + "\\";
    }

    public static String makeNotEndWithBackslash(String source) {
        return source.endsWith("\\") ? source.substring(0, source.length() - 1) : source;
    }

    public static String makeEndWithFileSeparator(String source) {
        return source.endsWith(File.separator) ? source : source + File.separator;
    }

    public static String makeNotEndWithFileSeparator(String source) {
        return source.endsWith(File.separator) ? source.substring(0, source.length() - 1) : source;
    }

}
