package com.ancevt.commons.properties;


import java.util.Properties;

public class PropertyWrapper {

    public static Properties argsToProperties(String[] args, Properties properties) {
        for (String arg : args) {
            if (arg.startsWith("-P")) {
                arg = arg.substring(2);
                String[] split = arg.split("=");
                String key = split[0];
                String value = split[1];
                properties.setProperty(key, value);
            }
        }

        return properties;
    }
}
