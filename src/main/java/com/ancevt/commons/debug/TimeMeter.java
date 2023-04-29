package com.ancevt.commons.debug;

import com.ancevt.commons.concurrent.Async;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

@RequiredArgsConstructor(staticName = "create")
public class TimeMeter {

    public static final TimeMeter DEFAULT = TimeMeter.create(System.out::println);

    private static final String EMPTY_STRING = "";

    private final List<Checkpoint> checkpoints = new ArrayList<>();

    private long time;

    private Checkpoint currentCheckpoint;

    private final Consumer<Checkpoint> checkpointConsumer;

    public Optional<Checkpoint> checkpoint() {
        return checkpoint(EMPTY_STRING);
    }

    public Optional<Checkpoint> checkpoint(String name) {
        if (!state()) {
            begin(name);
            return Optional.empty();
        } else {
            Optional<Checkpoint> result = Optional.of(end());
            begin();
            return result;
        }
    }

    public void begin() {
        begin(EMPTY_STRING);
    }

    public boolean state() {
        return currentCheckpoint != null;
    }

    public Checkpoint begin(String name) {
        if (currentCheckpoint != null) throw new IllegalStateException("Previous checkpoint is not ended");
        time = System.currentTimeMillis();
        currentCheckpoint = Checkpoint.of(name, time);
        return currentCheckpoint;
    }

    public Checkpoint end() {
        if (currentCheckpoint == null) throw new IllegalStateException("End before begin");
        currentCheckpoint.close();
        checkpoints.add(currentCheckpoint);
        checkpointConsumer.accept(currentCheckpoint);
        Checkpoint result = currentCheckpoint;
        currentCheckpoint = null;
        return result;
    }

    public List<Checkpoint> checkpoints() {
        return List.copyOf(checkpoints);
    }

    public static TimeMeter create() {
        return create(System.out::println);
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

        public String formattedDifference() {
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
            return getClass().getSimpleName() + "[" + name + ", " + formattedDifference() + "]";
        }
    }


    public static void main(String[] args) {
        TimeMeter.DEFAULT.checkpoint();

        Async.wait(1111, TimeUnit.MILLISECONDS);

        TimeMeter.DEFAULT.checkpoint();

        Async.wait(1111, TimeUnit.MILLISECONDS);

        TimeMeter.DEFAULT.checkpoint();

        Async.wait(1111, TimeUnit.MILLISECONDS);

        TimeMeter.DEFAULT.checkpoint();

        Async.wait(1111, TimeUnit.MILLISECONDS);
    }
}
