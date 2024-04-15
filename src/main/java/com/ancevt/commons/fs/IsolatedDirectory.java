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
package com.ancevt.commons.fs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.StringTokenizer;

@Getter
@Slf4j
@NoArgsConstructor
public class IsolatedDirectory {

    @Setter
    @Getter
    private Path baseDir = Paths.get(System.getProperty("user.home")).resolve(".isolated-directory-utils");

    public IsolatedDirectory(Path baseDir) {
        this.baseDir = baseDir;
    }

    public void backup(Path file) {
        try {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS"));
            Files.copy(file, file.resolveSibling(file.getFileName() + "." + time));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Path relativePath(String relativePath) {
        StringTokenizer stringTokenizer = new StringTokenizer(relativePath, "/");

        Path p = dir();

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (!token.isEmpty()) {
                p = p.resolve(token);
            }
        }

        return p;
    }

    public void delete(String relativePath) {
        Path file = relativePath(relativePath);
        try {
            Files.deleteIfExists(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void writeString(String data, String relativePath) {
        writeBytes(data.getBytes(StandardCharsets.UTF_8), relativePath);
    }

    public void writeBytes(byte[] data, String relativePath) {
        try {
            Files.write(
                relativePath(relativePath),
                data,
                StandardOpenOption.WRITE,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            );
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


    public String readString(String relativePath) {
        return new String(readBytes(relativePath(relativePath)), StandardCharsets.UTF_8);
    }

    public byte[] readBytes(Path file) {
        try {
            return Files.readAllBytes(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new IsolatedDirectoryException(e);
        }
    }

    public Path dir() {
        if (!Files.exists(baseDir)) {
            try {
                Files.createDirectories(baseDir);
            } catch (IOException e) {
                throw new IsolatedDirectoryException(e);
            }
        }

        return baseDir;
    }

    public Path dir(String relativePath) {
        Path p = dir();

        StringTokenizer stringTokenizer = new StringTokenizer(relativePath, "/");

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (!token.isEmpty()) {
                p = p.resolve(token);
            }
        }

        if (!Files.exists(p)) {
            try {
                Files.createDirectories(p);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new IsolatedDirectoryException(e);
            }
        }

        return p;
    }

    public Optional<String> checkExists(String relativePath) {
        Path p = relativePath(relativePath);
        if (Files.exists(p)) {
            return Optional.of(relativePath);
        }
        return Optional.empty();
    }

}
