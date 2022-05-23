package com.ancevt.commons.util;

import java.util.Map;

public class ApplicationMainClassNameExtractor {

    public static String get() {
        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Map.Entry<Thread, StackTraceElement[]> entry : map.entrySet()) {
            Thread thread = entry.getKey();
            if (thread.getId() == 1) {
                StackTraceElement[] stackTraceElements = entry.getValue();
                for (int i = stackTraceElements.length - 1; i >= 0; i--) {
                    StackTraceElement stackTraceElement = stackTraceElements[i];
                    if (stackTraceElement.getMethodName().equals("main")) {
                        return stackTraceElement.getClassName();
                    }
                }
            }
        }
        throw new MainClassNameExtractorException("Unable to extract application main class name");
    }

    public static class MainClassNameExtractorException extends RuntimeException {

        public MainClassNameExtractorException(String message) {
            super(message);
        }
    }
}
