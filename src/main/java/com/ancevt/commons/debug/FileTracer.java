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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class FileTracer {
    public static String defaultFile = "/tmp/_trace.txt";

    public static void truncate(Object data) {
        truncate(defaultFile, data);
    }

    public static void println(Object data) {
        println(defaultFile, data);
    }

    public static void truncate(String filePath, Object data) {
        actualPrintln(filePath, data, false);
    }

    public static void println(String filePath, Object data) {
        actualPrintln(filePath, data, true);
    }

    public static void actualPrintln(String filePath, Object stringData, boolean append) {
        File file = new File(filePath);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(file, append))) {
            printWriter.println(stringData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
