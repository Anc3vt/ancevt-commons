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

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ByteOutput {

    private final ByteArrayOutputStream byteArrayOutputStream;
    private final DataOutputStream dataOutputStream;

    private ByteOutput(int initialSize) {
        byteArrayOutputStream = new ByteArrayOutputStream(initialSize);
        dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    private ByteOutput() {
        byteArrayOutputStream = new ByteArrayOutputStream();
        dataOutputStream = new DataOutputStream(byteArrayOutputStream);
    }

    private void handleIOException(IOException ex) {
        throw new IllegalStateException(ex);
    }

    public byte[] toArray() {
        return byteArrayOutputStream.toByteArray();
    }

    public boolean hasData() {
        return byteArrayOutputStream.size() > 0;
    }

    public ByteOutput writeUtf(Class<?> lengthType, String string) {
        try {
            byte[] stringBytes = string.getBytes(StandardCharsets.UTF_8);

            if(lengthType == byte.class) {
                dataOutputStream.writeByte(stringBytes.length);
            } else if (lengthType == short.class) {
                dataOutputStream.writeShort(stringBytes.length);
            } else if (lengthType == int.class) {
                dataOutputStream.writeInt(stringBytes.length);
            }
            dataOutputStream.write(stringBytes);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput write(int b) {
        try {
            dataOutputStream.write(b);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput write(byte[] b) {
        try {
            dataOutputStream.write(b);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput write(byte[] b, int off, int len) {
        try {
            dataOutputStream.write(b, off, len);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeBoolean(boolean v) {
        try {
            dataOutputStream.writeBoolean(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeByte(int v) {
        try {
            dataOutputStream.writeByte(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeShort(int v) {
        try {
            dataOutputStream.writeShort(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeChar(int v) {
        try {
            dataOutputStream.writeChar(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeInt(int v) {
        try {
            dataOutputStream.writeInt(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeLong(long v) {
        try {
            dataOutputStream.writeLong(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeFloat(float v) {
        try {
            dataOutputStream.writeFloat(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeDouble(double v) {
        try {
            dataOutputStream.writeDouble(v);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeBytes(String s) {
        try {
            dataOutputStream.writeBytes(s);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeChars(String s) {
        try {
            dataOutputStream.writeChars(s);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public ByteOutput writeUTF(String s) {
        try {
            dataOutputStream.writeUTF(s);
            return this;
        } catch (IOException e) {
            handleIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public static ByteOutput newInstance(int length) {
        return new ByteOutput(length);
    }

    public static ByteOutput newInstance() {
        return new ByteOutput();
    }
}
