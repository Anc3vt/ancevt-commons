package ru.ancevt.commons.io;

import java.util.ArrayList;
import java.util.List;

public class ByteInputReader {

    private enum ReadType {
        BYTE,
        SHORT,
        INT,
        FLOAT,
        BYTE_STRING,
        SHORT_STRING,
        INT_STRING
    }

    private final ByteInput byteInput;
    private final List<ReadType> readTypes;

    private ByteInputReader(ByteInput byteInput) {
        this.byteInput = byteInput;
        this.readTypes = new ArrayList<>();
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
        if (lengthType == byte.class) {
            readTypes.add(ReadType.BYTE_STRING);
        } else if (lengthType == short.class) {
            readTypes.add(ReadType.SHORT_STRING);
        } else if (lengthType == int.class) {
            readTypes.add(ReadType.INT_STRING);
        }

        return byteInput.readUtf(lengthType);
    }

    public int readByte() {
        readTypes.add(ReadType.BYTE);
        return byteInput.readUnsignedByte();
    }

    public int readShort() {
        readTypes.add(ReadType.SHORT);
        return byteInput.readUnsignedShort();
    }

    public int readInt() {
        readTypes.add(ReadType.INT);
        return byteInput.readInt();
    }

    public float readFloat() {
        readTypes.add(ReadType.FLOAT);
        return byteInput.readFloat();
    }

    public String debug() {
        ByteInput debugByteInput = ByteInput.newInstance(byteInput.getBytes());
        StringBuilder s = new StringBuilder();

        readTypes.forEach(readType -> {
            switch (readType) {
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

    public static ByteInputReader newInstance(byte[] bytes) {
        return new ByteInputReader(bytes);
    }
}











