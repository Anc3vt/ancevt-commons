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
