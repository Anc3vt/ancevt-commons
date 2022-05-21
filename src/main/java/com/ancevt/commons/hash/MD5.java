/*
 *   Ancevt Commons Library
 *   Copyright (C) 2022 Ancevt (me@ancevt.com)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ancevt.commons.hash;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static byte[] hash(byte[] bytes) {
        try {
            return MessageDigest.getInstance("MD5").digest(bytes);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String hash(String string) {
        return bytesToHex(hash(string.getBytes(StandardCharsets.UTF_8)));
    }

    public static String hashFile(String path) {
        try {
            return bytesToHex(hash(Files.readAllBytes(Path.of(path))));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5.hashFile("README.md"));
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
