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
