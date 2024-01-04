/**
 * Copyright (C) 2023 the original author or authors.
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
package com.ancevt.commons.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class ListSplitter {

    public static <T> List<List<T>> split(List<T> sourceList, int partCount) {
        List<List<T>> result = new ArrayList<>(partCount);

        if (partCount > sourceList.size()) {
            return sourceList.stream().map(Arrays::asList).collect(Collectors.toList());
        }

        final int partSize = sourceList.size() / partCount;

        for (int i = 0; i < partCount; i++) {
            int rangeA = i * partSize;
            int rangeB = (i + 1) * partSize;

            boolean lastIter = i == partCount - 1;
            boolean hasElementLeft = rangeB < sourceList.size();

            if (lastIter && hasElementLeft) {
                rangeB = sourceList.size();
            }

            List<T> sublist = sourceList.subList(rangeA, rangeB);
            if (!sublist.isEmpty()) {
                result.add(new ArrayList<>(sublist));
            }
        }

        return result;
    }


    public static void main(String[] args) throws InterruptedException {
        final int partCount = 4;

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add(Math.random() + "");
        }

        List<List<String>> s = split(list, 100);

        System.out.println(s);

    }


}
