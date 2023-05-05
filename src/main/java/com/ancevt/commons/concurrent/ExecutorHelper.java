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
