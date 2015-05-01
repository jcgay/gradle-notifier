package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.time.Stopwatch
import fr.jcgay.gradle.notifier.time.Ticker
import groovy.transform.CompileStatic


@CompileStatic
class KnownElapsedTimeTicker extends Ticker {

    private long expectedElapsedTime
    private boolean firstRead

    KnownElapsedTimeTicker(long expectedElapsedTime) {
        this.expectedElapsedTime = expectedElapsedTime
    }

    static Stopwatch aStartedStopwatchWithElapsedTime(long elapsedTimeNano) {
        Stopwatch.createStarted(new KnownElapsedTimeTicker(elapsedTimeNano))
    }

    @Override
    long read() {
        firstRead = !firstRead;
        firstRead ? 0 : expectedElapsedTime
    }
}
