package com.ancevt.commons.debug;

public class Trace {

    public static void trace(String varNames, Object... o) {
        StringBuilder s = new StringBuilder();

        varNames = varNames.replaceAll("\\s+", "");

        String[] split = varNames.split(",");
        for (int i = 0; i < o.length; i++) {
            s.append(split[i]).append(": ").append(o[i]).append(", ");
        }

        System.out.println(s.substring(0, s.length() - 2));
    }

    public static void main(String[] args) {
        trace("a, b, c", 1, 2, 3);
    }
}
