/*
 *   Ancevt Commons Library
 *   Copyright (C) 2022 Ancevt (i@ancevt.ru)
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
package ru.ancevt.commons.io;

public class ByteOutputWriter {

    private final ByteOutput byteOutput;

    private ByteOutputWriter(ByteOutput byteOutput) {
        this.byteOutput = byteOutput;
    }

    private ByteOutputWriter(int initialLength) {
        this(ByteOutput.newInstance(initialLength));
    }

    private ByteOutputWriter() {
        this(ByteOutput.newInstance());
    }

    public byte[] toArray() {
        return byteOutput.toArray();
    }

    public ByteOutputWriter writeBytes(byte[] bytes) {
        byteOutput.write(bytes);
        return this;
    }

    public ByteOutputWriter writeByte(int b) {
        byteOutput.writeByte(b);
        return this;
    }

    public ByteOutputWriter writeShort(int s) {
        byteOutput.writeShort(s);
        return this;
    }

    public ByteOutputWriter writeInt(int i) {
        byteOutput.writeInt(i);
        return this;
    }

    public ByteOutputWriter writeFloat(float f) {
        byteOutput.writeFloat(f);
        return this;
    }

    public ByteOutputWriter writeUtf(Class<?> lengthType, String string) {
        byteOutput.writeUtf(lengthType, string);
        return this;
    }

    public static ByteOutputWriter newInstance(ByteOutput byteOutput) {
        return new ByteOutputWriter(byteOutput);
    }

    public static ByteOutputWriter newInstance(int length) {
        return new ByteOutputWriter(length);
    }

    public static ByteOutputWriter newInstance() {
        return new ByteOutputWriter();
    }
}






























