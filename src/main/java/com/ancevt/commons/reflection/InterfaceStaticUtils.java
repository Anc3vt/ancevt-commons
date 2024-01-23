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
package com.ancevt.commons.reflection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.Map;

public class InterfaceStaticUtils {

    private InterfaceStaticUtils(){}

    public static void clearAll(Class<?> clazz) {
        Reflections reflections = new Reflections(clazz.getPackage().getName(), new SubTypesScanner(false));
        new HashSet<>(reflections.getSubTypesOf(clazz)).forEach(InterfaceStaticUtils::clear);
    }

    public static <T> void clear(Class<T> iface) {
        Field[] declaredFields = iface.getFields();
        for (Field field : declaredFields) {
            if (Modifier.isStatic(field.getModifiers())) {
                try {
                    Map<T, Object> map = (Map<T, Object>) field.get(null);
                    map.clear();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
