/**
 * Copyright (C) 2023 the original author or authors.
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

    public byte[] toByteArray() {
        return byteOutput.toArray();
    }

    public boolean hasData() {
        return byteOutput.toArray().length > 0;
    }

    public synchronized ByteOutputWriter writeBytes(byte[] bytes) {
        byteOutput.write(bytes);
        return this;
    }

    public synchronized ByteOutputWriter writeByte(int b) {
        byteOutput.writeByte(b);
        return this;
    }

    public synchronized ByteOutputWriter writeShort(int s) {
        byteOutput.writeShort(s);
        return this;
    }

    public synchronized ByteOutputWriter writeInt(int i) {
        byteOutput.writeInt(i);
        return this;
    }

    public synchronized ByteOutputWriter writeFloat(float f) {
        byteOutput.writeFloat(f);
        return this;
    }

    public synchronized ByteOutputWriter writeUtf(Class<?> lengthType, String string) {
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






























