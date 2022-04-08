/*
 *   Ancevt Commons Library
 *   Copyright (C) 2022 Ancevt (me@ancevt.com)
 *
 *   This program is free software: you can redistribute it and/or modify
 *   it under the terms of the GNU General Public License as published by
 *   the Free Software Foundation, either version 3 of the License, or
 *   (at your option) any later version.
 *
 *   This program is distributed in the hope that it will be useful,
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *   GNU General Public License for more details.
 *
 *   You should have received a copy of the GNU General Public License
 *   along with this program.  If not, see <https://www.gnu.org/licenses/>.
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
