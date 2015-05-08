package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.time.Stopwatch
import fr.jcgay.gradle.notifier.time.Ticker
import groovy.transform.CompileStatic


@CompileStatic
class KnownElapsedTimeTicker extends Ticker {

    private long expectedElapsedTime
    private boolean isStartTick = true

    KnownElapsedTimeTicker(long expectedElapsedTime) {
        this.expectedElapsedTime = expectedElapsedTime
    }

    static Stopwatch aStartedStopwatchWithElapsedTime(long elapsedTimeNano) {
        Stopwatch.createStarted(new KnownElapsedTimeTicker(elapsedTimeNano))
    }

    static Stopwatch aStopWatchWithElapsedTime(long elapsedTimeNano) {
        aStartedStopwatchWithElapsedTime(elapsedTimeNano).stop()
    }

    @Override
    long read() {
        if (isStartTick) {
            isStartTick = !isStartTick;
            return 0
        }
        expectedElapsedTime
    }
}
