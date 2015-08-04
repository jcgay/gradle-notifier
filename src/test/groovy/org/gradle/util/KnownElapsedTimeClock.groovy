package org.gradle.util
import groovy.transform.CompileStatic

@CompileStatic
class KnownElapsedTimeClock {

    static Clock elapsedTimeClock(long elapsedTimeMs) {
        new Clock({ elapsedTimeMs }, 0)
    }
}
