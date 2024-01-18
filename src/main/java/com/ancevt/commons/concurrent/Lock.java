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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Lock {

    private CountDownLatch countDownLatch;
    private State state;

    public Lock() {
        state = State.NOT_LOCKED;
    }

    public boolean lock() {
        return lock(Long.MAX_VALUE, TimeUnit.DAYS);
    }

    public boolean lock(long timeout, TimeUnit timeUnit) {
        if (state == State.LOCKED) {
            throw new IllegalStateException("Lock is already locked");
        }

        try {
            countDownLatch = new CountDownLatch(1);
            state = State.LOCKED;
            return countDownLatch.await(timeout, timeUnit);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

    public void unlock() {
        if (state == State.NOT_LOCKED) {
            throw new IllegalStateException("Lock is not locked yet");
        } else if (state == State.UNLOCKED) {
            throw new IllegalStateException("Lock is already unlocked");
        }

        state = State.UNLOCKED;
        countDownLatch.countDown();
    }

    public void unlockIfLocked() {
        if(isLocked()) unlock();
    }

    public State getState() {
        return state;
    }

    public boolean isLocked() {
        return state == State.LOCKED;
    }

    public enum State {
        NOT_LOCKED,
        LOCKED,
        UNLOCKED
    }
}
