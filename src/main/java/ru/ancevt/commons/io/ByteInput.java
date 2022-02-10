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

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteInput implements DataInput {

    private final DataInputStream dataInputStream;
    private final ByteArrayInputStream byteArrayInputStream;

    ByteInput(byte[] bytes) {
        dataInputStream = new DataInputStream(
                byteArrayInputStream = new ByteArrayInputStream(bytes)
        );
    }

    private void handleIOException(IOException ex) {
        throw new IllegalStateException(ex);
    }

    public boolean hasNextData() {
        return byteArrayInputStream.available() > 0;
    }

    public String readUtf(Class<?> lengthType) {
        int len = 0;
        if (lengthType == byte.class) {
            len = readUnsignedByte();
        } else if (lengthType == short.class) {
            len = readUnsignedShort();
        } else if (lengthType == int.class) {
            len = readInt();
        }
        return readUtf(len);
    }

    public String readUtf(int length) {
        byte[] b = new byte[length];
        readFully(b);
        return new String(b, StandardCharsets.UTF_8);
    }

    @Override
    public void readFully(byte[] b) {
        try {
            dataInputStream.readFully(b);
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void readFully(byte[] b, int off, int len) {
        try {
            dataInputStream.readFully(b, off, len);
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int skipBytes(int n) {
        try {
            return dataInputStream.skipBytes(n);
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean readBoolean() {
        try {
            return dataInputStream.readBoolean();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public byte readByte() {
        try {
            return dataInputStream.readByte();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readUnsignedByte() {
        try {
            return dataInputStream.readUnsignedByte();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public short readShort() {
        try {
            return dataInputStream.readShort();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readUnsignedShort() {
        try {
            return dataInputStream.readUnsignedShort();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public char readChar() {
        try {
            return dataInputStream.readChar();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readInt() {
        try {
            return dataInputStream.readInt();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public long readLong() {
        try {
            return dataInputStream.readLong();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public float readFloat() {
        try {
            return dataInputStream.readFloat();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public double readDouble() {
        try {
            return dataInputStream.readDouble();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Deprecated
    public String readLine() {
        throw new IllegalStateException("not implemented");
    }

    @Override
    public String readUTF() {
        try {
            return dataInputStream.readUTF();
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }
}
