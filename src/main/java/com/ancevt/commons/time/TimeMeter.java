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

import com.ancevt.commons.debug.TraceUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@RequiredArgsConstructor(staticName = "create")
@Slf4j
public class TimeMeter {

    private static final String EMPTY_STRING = "";

    private final List<Checkpoint> checkpoints = new ArrayList<>();

    private Checkpoint currentCheckpoint;

    private final Consumer<Checkpoint> beginCheckpointConsumer;
    private final Consumer<Checkpoint> endCheckpointConsumer;

    public Optional<Checkpoint> checkpoint() {
        return checkpoint(EMPTY_STRING);
    }

    public Optional<Checkpoint> checkpoint(String name) {
        if (!state()) {
            begin(name);
            return Optional.empty();
        } else {
            Optional<Checkpoint> result = Optional.of(end());
            begin(name);
            return result;
        }
    }

    public void begin() {
        begin(EMPTY_STRING);
    }

    public Checkpoint begin(String name) {
        if (currentCheckpoint != null) throw new IllegalStateException("Previous checkpoint is not ended");
        currentCheckpoint = Checkpoint.of(name, System.currentTimeMillis());
        beginCheckpointConsumer.accept(currentCheckpoint);
        return currentCheckpoint;
    }

    public Checkpoint end() {
        if (currentCheckpoint == null) throw new IllegalStateException("End before begin");
        currentCheckpoint.close();
        checkpoints.add(currentCheckpoint);
        endCheckpointConsumer.accept(currentCheckpoint);
        Checkpoint result = currentCheckpoint;
        currentCheckpoint = null;
        return result;
    }

    public List<Checkpoint> checkpoints() {
        return new ArrayList<>(checkpoints);
    }

    public boolean state() {
        return currentCheckpoint != null;
    }

    public static TimeMeter createSlf4j() {
        return create(
                c -> log.debug("<g>*** BEGIN " + c.getName() + "<>"),
                c -> log.debug("<y>*** END " + c.getName() + " " + c.formattedTime() + "<>")
        );
    }

    public static TimeMeter createSlf4jColorized() {
        return create(
                c -> log.debug("<g>" + "*** BEGIN " + c.getName() + "<>"),
                c -> log.debug("<y>" + "*** END " + c.getName() + " " + c.formattedTime() + "<>")
        );
    }

    public static TimeMeter createSilent() {
        return create(c -> {}, c -> {});
    }

    public static TimeMeter createStdout() {
        return create(
                c -> System.out.println("*** BEGIN " + c.getName()),
                c -> System.out.println("*** END " + c.getName() + " " + c.formattedTime())
        );
    }

    public static TimeMeter createStdoutColorized() {
        return create(
                c -> System.out.println(TraceUtils.colorize("<g>*** BEGIN " + c.getName() + "<>")),
                c -> System.out.println(TraceUtils.colorize("<y>*** END " + c.getName() + " " + c.formattedTime() + "<>"))
        );
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
    public static class Checkpoint {

        private final String name;
        private final long beginTime;
        private long endTime;

        public long difference() {
            return endTime - beginTime;
        }

        public void close() {
            this.endTime = System.currentTimeMillis();
        }

        private boolean isClosed() {
            return endTime != 0L;
        }

        public String formattedTime() {
            long diff = difference();

            return String.format(
                    "%02d:%02d:%d.%d",
                    TimeUnit.MILLISECONDS.toHours(diff),
                    TimeUnit.MILLISECONDS.toMinutes(diff),
                    TimeUnit.MILLISECONDS.toSeconds(diff) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)),
                    diff -
                            TimeUnit.MILLISECONDS.toSeconds(diff) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff)) -
                            TimeUnit.MILLISECONDS.toSeconds(diff) * 1000
            );
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "[" + name + ", " + formattedTime() + "]";
        }

        public String getName() {
            return name;
        }
    }

}

