/*
 * Copyright (C) 2008 The Guava Authors
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
package fr.jcgay.gradle.notifier.time

import groovy.transform.CompileStatic

import java.util.concurrent.TimeUnit

@CompileStatic
final class Stopwatch {
    private final Ticker ticker
    private boolean isRunning
    private long elapsedNanos
    private long startTick

    static Stopwatch createStarted() {
        (new Stopwatch()).start()
    }

    static Stopwatch createStarted(Ticker ticker) {
        (new Stopwatch(ticker)).start()
    }

    private Stopwatch() {
        this(Ticker.systemTicker())
    }

    private Stopwatch(Ticker ticker) {
        this.ticker = ticker
    }

    Stopwatch start() {
        if (isRunning) {
            throw new IllegalArgumentException('This stopwatch is already running.')
        }
        isRunning = true
        startTick = ticker.read()
        this
    }

    long elapsed(TimeUnit desiredUnit) {
        desiredUnit.convert(elapsedNanos(), TimeUnit.NANOSECONDS)
    }

    private long elapsedNanos() {
        return isRunning ? ticker.read() - startTick + elapsedNanos : elapsedNanos
    }
}
