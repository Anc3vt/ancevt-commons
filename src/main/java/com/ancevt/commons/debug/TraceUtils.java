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
package com.ancevt.commons.debug;

import com.ancevt.commons.log.ColorizedLogTurboFilter;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TraceUtils {

    public static final String RESET = "\u001B[0m";

    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String BLACK_BACKGROUND = "\u001B[40m";
    public static final String RED_BACKGROUND = "\u001B[41m";
    public static final String GREEN_BACKGROUND = "\u001B[42m";
    public static final String YELLOW_BACKGROUND = "\u001B[43m";
    public static final String BLUE_BACKGROUND = "\u001B[44m";
    public static final String PURPLE_BACKGROUND = "\u001B[45m";
    public static final String CYAN_BACKGROUND = "\u001B[46m";
    public static final String WHITE_BACKGROUND = "\u001B[47m";

    private static final String B = "<a>";
    private static final String R = "<r>";
    private static final String G = "<g>";
    private static final String Y = "<y>";
    private static final String BL = "<b>";
    private static final String P = "<p>";
    private static final String C = "<c>";
    private static final String W = "<w>";

    private static final String BG_B = "<A>";
    private static final String BG_R = "<R>";
    private static final String BG_G = "<G>";
    private static final String BG_Y = "<Y>";
    private static final String BG_BL = "<B>";
    private static final String BG_P = "<P>";
    private static final String BG_C = "<C>";
    private static final String BG_W = "<W>";

    private static final String RES = "<>";

    private static final String EMPTY = "";

    private static boolean enabled = true;

    private static long lastTime;

    @Getter
    @Setter
    private static boolean alsoStdoutPrint;

    private static final Map<String, Boolean> tracedOnceMap = new HashMap<>();

    public static void timeCheck(Object str) {

        ColorizedLogTurboFilter f = new ColorizedLogTurboFilter();

        if (lastTime != 0L) {
            long currentTime = System.currentTimeMillis();
            trace("[TC] " + str + ": " + (currentTime - lastTime));
        } else {
            trace("[TC] " + str);
        }
        lastTime = System.currentTimeMillis();
    }

    public static String debug(Object o) {
        String string = o + "<>";
        System.out.println(colorize(string));
        return string;
    }

    public static String colorize(String source) {
        if (!enabled) {
            source = source.replaceAll(B, EMPTY);
            source = source.replaceAll(R, EMPTY);
            source = source.replaceAll(G, EMPTY);
            source = source.replaceAll(Y, EMPTY);
            source = source.replaceAll(BL, EMPTY);
            source = source.replaceAll(P, EMPTY);
            source = source.replaceAll(C, EMPTY);
            source = source.replaceAll(W, EMPTY);

            source = source.replaceAll(BG_B, EMPTY);
            source = source.replaceAll(BG_R, EMPTY);
            source = source.replaceAll(BG_G, EMPTY);
            source = source.replaceAll(BG_Y, EMPTY);
            source = source.replaceAll(BG_BL, EMPTY);
            source = source.replaceAll(BG_P, EMPTY);
            source = source.replaceAll(BG_C, EMPTY);
            source = source.replaceAll(BG_W, EMPTY);

            source = source.replaceAll(RES, EMPTY);

            return source;
        }

        source = source.replaceAll(B, BLACK);
        source = source.replaceAll(R, RED);
        source = source.replaceAll(G, GREEN);
        source = source.replaceAll(Y, YELLOW);
        source = source.replaceAll(BL, BLUE);
        source = source.replaceAll(P, PURPLE);
        source = source.replaceAll(C, CYAN);
        source = source.replaceAll(W, WHITE);

        source = source.replaceAll(BG_B, BLACK_BACKGROUND);
        source = source.replaceAll(BG_R, RED_BACKGROUND);
        source = source.replaceAll(BG_G, GREEN_BACKGROUND);
        source = source.replaceAll(BG_Y, YELLOW_BACKGROUND);
        source = source.replaceAll(BG_BL, BLUE_BACKGROUND);
        source = source.replaceAll(BG_P, PURPLE_BACKGROUND);
        source = source.replaceAll(BG_C, CYAN_BACKGROUND);
        source = source.replaceAll(BG_W, WHITE_BACKGROUND);

        source = source.replaceAll(RES, RESET);

        return source;
    }

    public static boolean autodect() {
        try {
            setEnabled(System.getProperty("user.home").startsWith("/home/"));
        } catch (Exception ex) {
            setEnabled(false);
        }
        return isEnabled();
    }

    public static boolean isEnabled() {
        return enabled;
    }

    public static void setEnabled(boolean enabled) {
        TraceUtils.enabled = enabled;
    }

    public static void clearDefaultTraces() {
        for(int i = 0; i < 100; i ++) {
            for(int j = 1; j < 9; j ++) {
                trace("", "/tmp/" + j);
            }
        }
    }

    public final void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static Object trace(Object object) {
        return trace(object, "");
    }

    public static Object trace_1(Object object) {return trace(object, "/tmp/1");}

    public static Object trace_2(Object object) {return trace(object, "/tmp/2");}

    public static Object trace_3(Object object) {return trace(object, "/tmp/3");}

    public static Object trace_4(Object object) {return trace(object, "/tmp/4");}

    public static Object trace_5(Object object) {return trace(object, "/tmp/5");}

    public static Object trace_6(Object object) {return trace(object, "/tmp/6");}

    public static Object trace_7(Object object) {return trace(object, "/tmp/7");}

    public static Object trace_8(Object object) {return trace(object, "/tmp/8");}

    public static Object trace_9(Object object) {return trace(object, "/tmp/9");}


    public static Object trace(Object object, String file) {
        if (object == null) object = "null";

        if (object instanceof byte[]) {
            return trace(Arrays.toString((byte[]) object), file);
        }

        if (!tracedOnceMap.containsKey(file)) {
            tracedOnceMap.put(file, true);
            for(int i = 0; i < 64; i ++) {
                trace("", file);
            }
            trace(String.format("\n\n<W><a> %s %s <>\n\n", LocalDateTime.now(), file), file);
        }

        try {
            Files.write(Paths.get(file), colorize(object + "<>\n").getBytes(StandardCharsets.UTF_8),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.APPEND
            );
            if (alsoStdoutPrint) {
                System.out.println(colorize(object + "<>"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return object;
    }

}
