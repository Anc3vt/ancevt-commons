/**
 * Copyright (C) 2022 the original author or authors.
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











