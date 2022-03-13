package com.ancevt.commons.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcher {
    public static boolean check(String text, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
}
