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
package com.ancevt.commons.exception;

import java.util.Arrays;

public class StackTraceUtil {

    public static String stringify(Throwable throwable) {
        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();


        stringBuilder.append(String.format("Exception %s: ", throwable.getClass().getName()));
        stringBuilder.append(throwable.getLocalizedMessage());
        stringBuilder.append(System.lineSeparator());


        Arrays.stream(stackTraceElements).forEach(stackTraceElement ->
            stringBuilder
                .append("  ")
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
