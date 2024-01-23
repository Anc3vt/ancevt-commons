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

import java.util.concurrent.TimeUnit;

public class Async {

    public static void wait(long time, TimeUnit timeUnit) {
        new Lock().lock(time, timeUnit);
    }

    public static Thread run(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.setName("_async_" + thread.getName());
        thread.start();
        return thread;
    }

    public static Thread runLater(long time, TimeUnit timeUnit, Runnable runnable) {
        Thread thread = new Thread(() -> {
            new Lock().lock(time, timeUnit);
            runnable.run();
        });
        thread.setName("_async_" + thread.getName());
        thread.start();
        return thread;
    }
}
