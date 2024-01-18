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
package com.ancevt.commons.concurrent;


import com.ancevt.commons.util.ListSplitter;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ExecutorHelper {

    public static <T> void splitAndExecute(List<T> allElements, int threadCount, Consumer<List<T>> partConsumer) {
        List<List<T>> lists = ListSplitter.split(allElements, threadCount);

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);

        List<Callable<Void>> tasks = lists.stream().map(elements -> (Callable<Void>) () -> {
            partConsumer.accept(elements);
            return null;
        }).collect(Collectors.toList());

        try {
            executorService.invokeAll(tasks);
            executorService.shutdown();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
