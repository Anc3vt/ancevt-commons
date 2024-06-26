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

import com.ancevt.commons.platform.OsUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.StringTokenizer;

@Getter
@Slf4j
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class IsolatedDirectory {

    @Getter
    private final Path baseDir;

    public void backup(String relativePath) {
        try {
            Path path = resolvePath(relativePath);
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss-SSS"));
            Files.copy(path, path.resolveSibling(path.getFileName() + "." + time));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public Path resolvePath(String relativePath) {
        StringTokenizer stringTokenizer = new StringTokenizer(relativePath, "/");

        Path p = baseDir();

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (!token.isEmpty()) {
                p = p.resolve(token);
            }
        }

        return p;
    }

    public void delete(String relativePath) {
        Path path = resolvePath(relativePath);
        try {
            Files.deleteIfExists(path);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    public void deleteRecursively(String relativePath) {
        Path path = resolvePath(relativePath);
        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }


    public void writeString(String data, String relativePath) {
        writeBytes(data.getBytes(StandardCharsets.UTF_8), relativePath);
    }

    public void writeBytes(byte[] data, String path) {
        try {
            System.out.println("Write bytes to " + resolvePath(path).toAbsolutePath());

            Files.write(
                resolvePath(path),
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
        return new String(readBytes(relativePath), StandardCharsets.UTF_8);
    }

    public byte[] readBytes(String relativePath) {
        Path path = resolvePath(relativePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new IsolatedDirectoryException(e);
        }
    }

    public Path baseDir() {
        if (!Files.exists(baseDir)) {
            try {
                Files.createDirectories(baseDir);
            } catch (IOException e) {
                throw new IsolatedDirectoryException(e);
            }
        }

        return baseDir;
    }

    public Path createSubdirectories(String relativePath) {
        Path path = baseDir();

        StringTokenizer stringTokenizer = new StringTokenizer(relativePath, "/");

        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (!token.isEmpty()) {
                path = path.resolve(token);
            }
        }

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
                throw new IsolatedDirectoryException(e);
            }
        }

        return path;
    }

    public boolean isExists(String relativePath) {
        return Files.exists(resolvePath(relativePath));
    }

    public Optional<String> checkExists(String relativePath) {
        Path path = resolvePath(relativePath);
        if (Files.exists(path)) {
            return Optional.of(relativePath);
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName() + "{");
        sb.append("baseDir=").append(baseDir);
        sb.append('}');
        return sb.toString();
    }

    public static IsolatedDirectory newIsolatedDirectory(Path baseDir) {
        return new IsolatedDirectory(baseDir);
    }

    public static IsolatedDirectory newIsolatedDirectoryInApplicationData(Path relativePath) {
        return new IsolatedDirectory(OsUtils.getApplicationDataPath().resolve(relativePath));
    }
}
