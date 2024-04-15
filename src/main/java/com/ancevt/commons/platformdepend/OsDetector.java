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
package com.ancevt.commons.platformdepend;

public class OsDetector {

    private static final String OS = System.getProperty("os.name");

    public static boolean isWindows() {
        if (OS == null) return false;
        return OS.toLowerCase().contains("win");
    }

    public static boolean isMac() {
        if (OS == null) return false;
        return OS.toLowerCase().contains("mac");
    }

    public static boolean isSolaris() {
        if (OS == null) return false;
        return OS.toLowerCase().contains("sunos");
    }

    public static boolean isUnix() {
        if (OS == null) return false;
        String os = OS.toLowerCase();
        return os.contains("nix")
            || os.contains("nux")
            || os.contains("aix");
    }
}

