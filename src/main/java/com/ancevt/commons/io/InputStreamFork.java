package com.ancevt.commons.io;

import lombok.Getter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InputStreamFork {

    @Getter
    private final List<InputStream> inputStreams = new ArrayList<>();

    public int size() {
        return inputStreams.size();
    }

    public InputStream left() {
        return inputStreams.get(0);
    }

    public InputStream right() {
        if(inputStreams.size() > 1) {
            return inputStreams.get(1);
        } else {
            throw new IllegalStateException("");
        }

    }

    public static InputStreamFork fork(InputStream inputStream) {
        return fork(inputStream, 2);
    }

    public static InputStreamFork fork(InputStream inputStream, int count) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        InputStreamFork result = new InputStreamFork();

        for (int i = 0; i < count; i++) {
            result.inputStreams.add(new ByteArrayInputStream(baos.toByteArray()));
        }

        return result;
    }


}
