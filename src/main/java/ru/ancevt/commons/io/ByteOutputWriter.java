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

import java.util.ArrayList;
import java.util.List;

public class ByteOutputWriter {

    private enum WriteType {
        BYTE,
        SHORT,
        INT,
        FLOAT,
        BYTE_STRING,
        SHORT_STRING,
        INT_STRING
    }

    private final List<WriteType> writeTypes;
    private final ByteOutput byteOutput;

    private ByteOutputWriter(ByteOutput byteOutput) {
        writeTypes = new ArrayList<>();
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

    public ByteOutputWriter writeByte(int b) {
        byteOutput.writeByte(b);
        writeTypes.add(WriteType.BYTE);
        return this;
    }

    public ByteOutputWriter writeShort(int s) {
        byteOutput.writeShort(s);
        writeTypes.add(WriteType.SHORT);
        return this;
    }

    public ByteOutputWriter writeInt(int i) {
        byteOutput.writeInt(i);
        writeTypes.add(WriteType.INT);
        return this;
    }

    public ByteOutputWriter writeFloat(float f) {
        byteOutput.writeFloat(f);
        writeTypes.add(WriteType.FLOAT);
        return this;
    }

    public ByteOutputWriter writeUtf(Class<?> lengthType, String string) {
        byteOutput.writeUtf(lengthType, string);

        WriteType type;
        if (lengthType == byte.class) {
            type = WriteType.BYTE_STRING;
        } else if (lengthType == short.class) {
            type = WriteType.SHORT_STRING;
        } else if (lengthType == int.class) {
            type = WriteType.INT_STRING;
        } else {
            throw new IllegalStateException("Unsupported length type: " + lengthType);
        }

        writeTypes.add(type);
        return this;
    }

    public String debug() {
        ByteInput debugByteInput = ByteInput.newInstance(byteOutput.toArray());
        StringBuilder s = new StringBuilder();

        writeTypes.forEach(writeType -> {
            switch (writeType) {
                case BYTE -> s.append("b)").append(debugByteInput.readUnsignedByte()).append(' ');
                case SHORT -> s.append("s)").append(debugByteInput.readUnsignedShort()).append(' ');
                case INT -> s.append("i)").append(debugByteInput.readInt()).append(' ');
                case FLOAT -> s.append("f)").append(debugByteInput.readFloat()).append(' ');
                case BYTE_STRING -> {
                    s.append("b)b ");
                    s.append("S)").append(debugByteInput.readUtf(byte.class)).append(' ');
                }
                case SHORT_STRING -> {
                    s.append("b)s ");
                    s.append("S)").append(debugByteInput.readUtf(short.class)).append(' ');
                }
                case INT_STRING -> {
                    s.append("b)i ");
                    s.append("S)").append(debugByteInput.readUtf(int.class)).append(' ');
                }
            }
        });

        return s.toString();
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






























