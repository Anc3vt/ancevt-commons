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
package com.ancevt.commons.debug;

public class Trace {

    public static void trace(String varNames, Object... o) {
        StringBuilder s = new StringBuilder();

        varNames = varNames.replaceAll("\\s+", "");

        String[] split = varNames.split(",");
        for (int i = 0; i < o.length; i++) {
            s.append(split[i]).append(": ").append(o[i]).append(", ");
        }

        System.out.println(s.substring(0, s.length() - 2));
    }

    public static void main(String[] args) {
        trace("a, b, c", 1, 2, 3);
    }
}
