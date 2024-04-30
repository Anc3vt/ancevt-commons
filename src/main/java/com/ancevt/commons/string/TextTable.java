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
package com.ancevt.commons.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextTable {
    private final boolean decor;
    private final List<String> headers;
    private final List<List<Object>> rows;

    public TextTable(boolean decor, String... headers) {
        this.decor = decor;
        this.headers = Arrays.asList(headers);
        this.rows = new ArrayList<>();
    }

    public void addRow(Object... rowData) {
        if (rowData.length != headers.size()) {
            throw new IllegalArgumentException("Number of columns in row must match number of headers");
        }
        rows.add(Arrays.asList(rowData));
    }

    public String render() {
        StringBuilder sb = new StringBuilder();
        if (decor) {
            renderDecorated(sb);
        } else {
            renderPlain(sb);
        }
        return sb.toString();
    }

    private void renderDecorated(StringBuilder sb) {
        int[] maxLengths = new int[headers.size()];

        // Calculate maximum length of each column
        for (int i = 0; i < headers.size(); i++) {
            maxLengths[i] = headers.get(i).length();
            for (List<Object> row : rows) {
                maxLengths[i] = Math.max(maxLengths[i], String.valueOf(row.get(i)).length());
            }
        }

        // Render header
        renderHorizontalLine(sb, maxLengths);
        renderRow(sb, headers, maxLengths);
        renderHorizontalLine(sb, maxLengths);

        // Render rows
        for (List<Object> row : rows) {
            renderRow(sb, row, maxLengths);
        }

        // Render bottom line
        renderHorizontalLine(sb, maxLengths);
    }

    private void renderPlain(StringBuilder sb) {
        // Render headers with alignment
        for (int i = 0; i < headers.size(); i++) {
            int maxLength = headers.get(i).length();
            for (List<Object> row : rows) {
                maxLength = Math.max(maxLength, String.valueOf(row.get(i)).length());
            }
            sb.append(headers.get(i));
            for (int j = 0; j < maxLength - headers.get(i).length() + 2; j++) {
                sb.append(" ");
            }
        }
        sb.append("\n");

        // Render rows with alignment
        for (List<Object> row : rows) {
            for (int i = 0; i < row.size(); i++) {
                int maxLength = String.valueOf(row.get(i)).length();
                for (List<Object> otherRow : rows) {
                    maxLength = Math.max(maxLength, String.valueOf(otherRow.get(i)).length());
                }
                sb.append(row.get(i));
                for (int j = 0; j < maxLength - String.valueOf(row.get(i)).length() + 2; j++) {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
    }

    private void renderHorizontalLine(StringBuilder sb, int[] maxLengths) {
        sb.append("+");
        for (int length : maxLengths) {
            for (int i = 0; i < length + 2; i++) {
                sb.append("-");
            }
            sb.append("+");
        }
        sb.append("\n");
    }

    private void renderRow(StringBuilder sb, List<?> rowData, int[] maxLengths) {
        sb.append("|");
        for (int i = 0; i < rowData.size(); i++) {
            sb.append(" ");
            sb.append(rowData.get(i));
            int spaces = maxLengths[i] - String.valueOf(rowData.get(i)).length();
            for (int j = 0; j < spaces + 3; j++) {
                sb.append(" ");
            }
            sb.append("|");
        }
        sb.append("\n");
    }
}
