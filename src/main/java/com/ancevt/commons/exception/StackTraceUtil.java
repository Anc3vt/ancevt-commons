package com.ancevt.commons.exception;

import java.util.Arrays;

public class StackTraceUtil {

    public static String stringify(Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();

        stringBuilder.append(throwable.getLocalizedMessage());
        stringBuilder.append(System.lineSeparator());

        Arrays.stream(stackTraceElements).forEach(stackTraceElement ->
                stringBuilder
                        .append("\t")
                        .append(stackTraceElement.toString())
                        .append(System.lineSeparator())
        );

        Throwable cause = throwable.getCause();
        if (cause != null) {
            stringBuilder.append("Caused by: ");
            stringBuilder.append(stringify(cause));
        }

        return stringBuilder.toString();
    }
}
