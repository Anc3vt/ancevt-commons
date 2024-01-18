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
package com.ancevt.commons.time;

public class HumanReadableTimeParser {

    private final int T_MILLI = 1;
    private final int T_SECOND = T_MILLI * 1000;
    private final int T_MINUTE = T_SECOND * 60;
    private final int T_HOUR = T_MINUTE * 60;
    private final int T_DAY = T_HOUR * 24;

    private long millis;

    private boolean letterExists;

    public HumanReadableTimeParser(String pattern) {

        StringBuilder numericSb = new StringBuilder();
        StringBuilder numericSbWithoutLetters = new StringBuilder();

        millis = 0;

        for (int i = 0; i < pattern.length(); i++) {
            final char c = pattern.charAt(i);

            if (Character.isDigit(c)) {
                numericSb.append(c);
                numericSbWithoutLetters.append(c);
            } else {
                letterExists = true;

                switch (c) {
                    case 'm':
                        if (i < pattern.length() - 1 && pattern.charAt(i + 1) == 's') {
                            millis += resetStringBuilder(numericSb) * T_MILLI;
                            i++;
                            break;
                        }
                        millis += resetStringBuilder(numericSb) * T_MINUTE;
                        break;

                    case 's':
                        millis += resetStringBuilder(numericSb) * T_SECOND;
                        break;

                    case 'h':
                        millis += resetStringBuilder(numericSb) * T_HOUR;
                        break;

                    case 'd':
                        millis += resetStringBuilder(numericSb) * T_DAY;
                        break;
                }
            }
        }

        if(!letterExists) {
            millis = resetStringBuilder(numericSbWithoutLetters);
        }
    }

    private static long resetStringBuilder(StringBuilder stringBuilder) {
        final String numberString = stringBuilder.toString();
        stringBuilder.setLength(0);
        return Long.parseLong(numberString);
    }

    public long getSeconds() {
        return millis / 1000L;
    }

    public long getMillis() {
        return millis;
    }
}
