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
package com.ancevt.commons.io;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ByteInput implements DataInput {

    private static final int MAX_BUFFER_SIZE = Integer.MAX_VALUE - 8;
    private static final int DEFAULT_BUFFER_SIZE = 8192;

    private final DataInputStream dataInputStream;
    private final ByteArrayInputStream byteArrayInputStream;

    private final byte[] bytes;

    private ByteInput(byte[] bytes) {
        this.bytes = bytes;
        dataInputStream = new DataInputStream(
            byteArrayInputStream = new ByteArrayInputStream(bytes)
        );
    }

    public byte[] getBytes() {
        return bytes;
    }

    private void handleIfIOException(IOException ex) {
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

    public byte[] readBytes(int length) {
        try {
            return readNBytes(dataInputStream, length);
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    private byte[] readNBytes(InputStream is, int len) throws IOException {
        if (len < 0) {
            throw new IllegalArgumentException("len < 0");
        }

        List<byte[]> bufs = null;
        byte[] result = null;
        int total = 0;
        int remaining = len;
        int n;
        do {
            byte[] buf = new byte[Math.min(remaining, DEFAULT_BUFFER_SIZE)];
            int nread = 0;

            // read to EOF which may read more or less than buffer size
            while ((n = is.read(buf, nread,
                Math.min(buf.length - nread, remaining))) > 0) {
                nread += n;
                remaining -= n;
            }

            if (nread > 0) {
                if (MAX_BUFFER_SIZE - total < nread) {
                    throw new OutOfMemoryError("Required array size too large");
                }
                total += nread;
                if (result == null) {
                    result = buf;
                } else {
                    if (bufs == null) {
                        bufs = new ArrayList<>();
                        bufs.add(result);
                    }
                    bufs.add(buf);
                }
            }
            // if the last call to read returned -1 or the number of bytes
            // requested have been read then break
        } while (n >= 0 && remaining > 0);

        if (bufs == null) {
            if (result == null) {
                return new byte[0];
            }
            return result.length == total ?
                result : Arrays.copyOf(result, total);
        }

        result = new byte[total];
        int offset = 0;
        remaining = total;
        for (byte[] b : bufs) {
            int count = Math.min(b.length, remaining);
            System.arraycopy(b, 0, result, offset, count);
            offset += count;
            remaining -= count;
        }

        return result;
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
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void readFully(byte[] b, int off, int len) {
        try {
            dataInputStream.readFully(b, off, len);
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int skipBytes(int n) {
        try {
            return dataInputStream.skipBytes(n);
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean readBoolean() {
        try {
            return dataInputStream.readBoolean();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public byte readByte() {
        try {
            return dataInputStream.readByte();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readUnsignedByte() {
        try {
            return dataInputStream.readUnsignedByte();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public short readShort() {
        try {
            return dataInputStream.readShort();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readUnsignedShort() {
        try {
            return dataInputStream.readUnsignedShort();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public char readChar() {
        try {
            return dataInputStream.readChar();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public int readInt() {
        try {
            return dataInputStream.readInt();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public long readLong() {
        try {
            return dataInputStream.readLong();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public float readFloat() {
        try {
            return dataInputStream.readFloat();
        } catch (IOException e) {
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    @Override
    public double readDouble() {
        try {
            return dataInputStream.readDouble();
        } catch (IOException e) {
            handleIfIOException(e);
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
            handleIfIOException(e);
            throw new IllegalStateException(e);
        }
    }

    public static ByteInput newInstance(byte[] bytes) {
        return new ByteInput(bytes);
    }
}
