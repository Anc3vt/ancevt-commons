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
package com.ancevt.commons.fs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class IsolatedDirectoryDictionaryUtil {

    public static void write(IsolatedDirectory isolatedDirectory, Map<String, Object> dictionary, String relativePath) {
        Map<String, String> toWrite = new HashMap<>();
        dictionary.forEach((k, v) -> toWrite.put(k, String.valueOf(v)));
        String json = JsonEngine.gson().toJson(toWrite);
        isolatedDirectory.writeString(json, relativePath);
    }

    public static Map<String, Object> read(IsolatedDirectory isolatedDirectory, String relativePath) {
        String json = isolatedDirectory.readString(relativePath);
        return JsonEngine.gson().fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
    }

    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    private static class JsonEngine {
        private static Gson gson;

        private static Gson gson() {
            if (gson == null) {
                gson = new GsonBuilder().setPrettyPrinting().create();
            }
            return gson;
        }
    }
}
