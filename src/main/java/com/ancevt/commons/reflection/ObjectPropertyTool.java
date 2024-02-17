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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectPropertyTool {

    private final Map<Class<?>, TypeFilter<?>> typeFilterMap = new HashMap<>();

    public interface TypeFilter<T> {

        String encode(T o);

        T decode(String src);
    }

    public <T> void addTypeFilter(Class<? extends T> type, TypeFilter<T> typeFilter) {
        typeFilterMap.put(type, typeFilter);
    }

    public void removeTypeFilter(Class<?> type) {
        typeFilterMap.remove(type);
    }

    public Object setProperties(Object object, Map<String, Object> propertyMap) {

        propertyMap.forEach((key, value) -> {
            try {
                Method method = getSetter(object, key);
                if (method == null) return;

                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> type = parameterTypes[0];

                String valueStr = String.valueOf(value);

                if (typeFilterMap.containsKey(type)) {
                    TypeFilter<Object> typeFilter = (TypeFilter<Object>) typeFilterMap.get(type);
                    method.invoke(object, typeFilter.decode(valueStr));
                } else if (type == byte.class) {
                    method.invoke(object, Byte.parseByte(round(valueStr)));
                } else if (type == short.class) {
                    method.invoke(object, Short.parseShort(round(valueStr)));
                } else if (type == char.class) {
                    method.invoke(object, valueStr.charAt(0));
                } else if (type == long.class) {
                    method.invoke(object, Long.parseLong(round(valueStr)));
                } else if (type == int.class) {
                    method.invoke(object, Integer.parseInt(round(valueStr)));
                } else if (type == float.class) {
                    method.invoke(object, Float.parseFloat(valueStr));
                } else if (type == double.class) {
                    method.invoke(object, Double.parseDouble(valueStr));
                } else if (type == boolean.class) {
                    method.invoke(object, Objects.equals("true", valueStr.toLowerCase()));
                } else if (type == String.class) {
                    method.invoke(object, String.valueOf(value));
                }

            } catch (IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
                throw new ObjectPropertyException(String.format("Couldn't set property `%s` to `%s`", key, value), e);
            }
        });

        return object;
    }

    private String round(String number) {
        return number.contains(".") ? number.substring(0, number.indexOf(".")) : number;
    }

    public Map<String, Object> getProperties(Object object) {
        return getProperties(object, new HashMap<>());
    }

    public Map<String, Object> getProperties(Object object, Map<String, Object> propertyMap) {
        try {
            List<Class<?>> ifaces = getAllInterfaces(object);
            for (Class<?> iface : ifaces) {
                for (Method method : iface.getDeclaredMethods()) {
                    getPropertiesGetterMethodCall(object, propertyMap, method);
                }
            }

            List<Method> methods = getAllObjectMethods(object);
            for (Method method : methods) {
                getPropertiesGetterMethodCall(object, propertyMap, method);
            }

            return propertyMap;

        } catch (InvocationTargetException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public Object createObject(Class<?> type, Map<String, Object> propertyMap) {
        try {
            Object result = type.getConstructor().newInstance();
            setProperties(result, propertyMap);
            return result;
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private void getPropertiesGetterMethodCall(Object o, Map<String, Object> propertyMap, Method method) throws IllegalAccessException, InvocationTargetException {
        Class<?> type = method.getReturnType();
        if (!method.isAnnotationPresent(Property.class) || type == void.class) return;
        String property = methodNameToProperty(method.getName());

        if (typeFilterMap.containsKey(type)) {
            TypeFilter<Object> typeFilter = (TypeFilter<Object>) typeFilterMap.get(type);
            propertyMap.put(property, typeFilter.encode(method.invoke(o)));
        } else {
            propertyMap.put(property, method.invoke(o));
        }
    }

    private static String removeMethodAccessor(String methodName) {
        if (isNotEmptyWithTrim(methodName)) {
            if (methodName.startsWith("get")) {
                return methodName.length() > 3 ? methodName.substring(3) : methodName;
            } else if (methodName.startsWith("is")) {
                return methodName.length() > 2 ? methodName.substring(2) : methodName;
            } else if (methodName.startsWith("set")) {
                return methodName.length() > 3 ? methodName.substring(3) : methodName;
            }
        }
        return methodName;
    }

    private static String methodNameToProperty(String methodName) {
        return firstCharLowerCase(removeMethodAccessor(methodName));
    }

    private static Method getSetter(Object object, String property) {
        List<Class<?>> ifaces = getAllInterfaces(object);
        for (Class<?> iface : ifaces) {
            for (Method method : iface.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Property.class) &&
                    method.getName().equals(propertyToSetterMethodName(property))) {
                    return method;
                }
            }
        }

        List<Method> methods = getAllObjectMethods(object);
        for (Method method : methods) {
            if (method.isAnnotationPresent(Property.class)) {
                if (method.getName().equals(propertyToSetterMethodName(property))) {
                    return method;
                }
            }
        }

        return null;
    }

    private static List<Method> getAllObjectMethods(Object object) {
        List<Method> methods = new ArrayList<>();
        collectAllObjectMethods(methods, object.getClass());
        return methods;
    }

    private static void collectAllObjectMethods(List<Method> methods, Class<?> clazz) {
        List<Method> currentMethods = Arrays.stream(clazz.getMethods()).collect(Collectors.toList());
        methods.addAll(currentMethods);
        if (clazz.getSuperclass() != Object.class) collectAllObjectMethods(methods, clazz.getSuperclass());
    }

    private static List<Class<?>> getAllInterfaces(Object object) {
        List<Class<?>> ifaces = new ArrayList<>();
        collectAllInterfaces(ifaces, object.getClass());
        return ifaces;
    }

    private static void collectAllInterfaces(List<Class<?>> ifaces, Class<?> clazz) {
        List<Class<?>> currentInterfaces = Arrays.stream(clazz.getInterfaces()).collect(Collectors.toList());
        ifaces.addAll(currentInterfaces);
        currentInterfaces.forEach(c -> collectAllInterfaces(ifaces, c));
        if (clazz.getSuperclass() != null)
            collectAllInterfaces(ifaces, clazz.getSuperclass());
    }

    private static String propertyToSetterMethodName(String propertyName) {
        return addSetterMethodAccessor(propertyName);
    }

    private static String addSetterMethodAccessor(String methodName) {
        return "set" + firstCharUpperCase(methodName);
    }

    private static String firstCharLowerCase(String methodName) {
        if (isNotEmptyWithTrim(methodName)) {
            return Character.toLowerCase(methodName.charAt(0))
                + (methodName.length() > 1 ? methodName.substring(1) : "");
        }
        return methodName;
    }

    private static String firstCharUpperCase(String methodName) {
        if (isNotEmptyWithTrim(methodName)) {
            return Character.toUpperCase(methodName.charAt(0))
                + (methodName.length() > 1 ? methodName.substring(1) : "");
        }
        return methodName;
    }

    private static boolean isNotEmptyWithTrim(String s) {
        return !isEmptyWithTrim(s);
    }

    private static boolean isEmptyWithTrim(String s) {
        return s == null || s.trim().length() == 0;
    }


}
