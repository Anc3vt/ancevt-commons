package ru.ancevt.commons.concurrent;

import java.util.concurrent.TimeUnit;

public class Async {

    public static void runLater(long time, TimeUnit timeUnit, Runnable runnable) {
        new Thread(()->{
            new Lock().lock(time, timeUnit);
            runnable.run();
        }).start();
    }

}
