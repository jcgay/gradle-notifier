package fr.jcgay.gradle.notifier
import groovy.transform.CompileStatic

@CompileStatic
class KnownElapsedTimeClock {

    static Clock elapsedTimeClock(long elapsedTimeMs) {
        new Clock() {
            @Override
            long getTimeInMs() {
                return elapsedTimeMs
            }
        }
    }
}
