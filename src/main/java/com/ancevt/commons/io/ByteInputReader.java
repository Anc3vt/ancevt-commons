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
package com.ancevt.commons.io;

public class ByteInputReader {

    private final ByteInput byteInput;

    private ByteInputReader(ByteInput byteInput) {
        this.byteInput = byteInput;
    }

    private ByteInputReader(byte[] bytes) {
        this(ByteInput.newInstance(bytes));
    }

    public byte[] getBytes() {
        return byteInput.getBytes();
    }

    public boolean hasNextData() {
        return byteInput.hasNextData();
    }

    public String readUtf(Class<?> lengthType) {
        return byteInput.readUtf(lengthType);
    }

    public int readByte() {
        return byteInput.readUnsignedByte();
    }

    public int readShort() {
        return byteInput.readUnsignedShort();
    }

    public int readInt() {
        return byteInput.readInt();
    }

    public float readFloat() {
        return byteInput.readFloat();
    }
    public byte[] readBytes(int length) {
        return byteInput.readBytes(length);
    }

    public static ByteInputReader newInstance(byte[] bytes) {
        return new ByteInputReader(bytes);
    }
}











