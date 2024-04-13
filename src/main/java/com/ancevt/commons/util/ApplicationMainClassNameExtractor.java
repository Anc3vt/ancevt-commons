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

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Utility class for extracting the main class name of the application.
 * <p>
 * This class provides a static method to retrieve the fully qualified class name of the main class
 * by analyzing the stack trace. It should be noted that this method may not always provide accurate
 * results, especially in environments where the application lifecycle is managed by a server
 * or when executed in a multi-threaded context.
 * </p>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationMainClassNameExtractor {
    /**
     * Retrieves the fully qualified name of the main class of the application.
     *
     * @return The fully qualified name of the main class.
     * @throws MainClassNameExtractorException if the main class name cannot be extracted reliably.
     */
    public static String getMainClassName() throws MainClassNameExtractorException {
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
