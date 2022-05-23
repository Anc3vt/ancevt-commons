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
