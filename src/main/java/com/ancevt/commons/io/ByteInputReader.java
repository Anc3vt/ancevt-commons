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











