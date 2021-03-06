/**
 * Copyright (C) 2022 the original author or authors.
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

import java.util.Map;
import java.util.function.Consumer;

public class NullUtils {

    private NullUtils() {
    }

    public static <T> void ifNotNullOrElse(T object, Consumer<T> value, Runnable or) {
        if (object != null) {
            value.accept(object);
        } else {
            or.run();
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = Map.of("key", "value");

        ifNotNullOrElse(map.get("key"), value -> {
            System.out.println(value);
        }, () -> {
            System.out.println("null");
        });
    }
}
