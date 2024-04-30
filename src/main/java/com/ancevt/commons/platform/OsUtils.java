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
package com.ancevt.commons.platform;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.nio.file.Paths;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OsUtils {

    public enum OperatingSystem {
        WINDOWS,
        MACOS,
        UNIX_LIKE,
        SOLARIS,
        FREEBSD,
        HPUX,
        OTHER
    }

    public static OperatingSystem getOperatingSystem() {
        String osName = ((String) System.getProperties().getOrDefault("os.name", "")).toLowerCase();

        if (osName.contains("win")) {
            return OperatingSystem.WINDOWS;
        } else if (osName.contains("nix") || osName.contains("nux") || osName.contains("aix")) {
            return OperatingSystem.UNIX_LIKE;
        } else if (osName.contains("mac")) {
            return OperatingSystem.MACOS;
        } else if (osName.contains("sunos")) {
            return OperatingSystem.SOLARIS;
        } else if (osName.contains("freebsd")) {
            return OperatingSystem.FREEBSD;
        } else if (osName.contains("hp-ux")) {
            return OperatingSystem.HPUX;
        } else {
            return OperatingSystem.OTHER;
        }
    }

    public static Path getApplicationDataPath() {
        Path userHomePath = Paths.get(System.getProperty("user.home"));

        switch (OsUtils.getOperatingSystem()) {
            case WINDOWS:
                String localAppDataPathEnvironmentVariableValue = System.getenv("LOCALAPPDATA");
                String appDataEnvironmentVariableValue = System.getenv("APPDATA");

                if (localAppDataPathEnvironmentVariableValue != null) {
                    return Paths.get(localAppDataPathEnvironmentVariableValue);
                } else if (appDataEnvironmentVariableValue != null) {
                    return Paths.get(appDataEnvironmentVariableValue);
                } else {
                    return userHomePath;
                }
            case MACOS:
            case UNIX_LIKE:
            case SOLARIS:
            case FREEBSD:
            case HPUX:
                return userHomePath;

            default:
                throw new UnsupportedOperationException("Unsupported operating system");
        }
    }
}
