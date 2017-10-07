package fr.jcgay.gradle.notifier

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@CompileStatic
@EqualsAndHashCode
@ToString
class Clock {

    private static final long MS_PER_MINUTE = 60000
    private static final long MS_PER_HOUR = MS_PER_MINUTE * 60

    long startTime

    long getTimeInMs() {
        new Date().getTime() - startTime
    }

    String getTime() {
        long timeInMs = getTimeInMs()

        StringBuilder result = new StringBuilder()
        if (timeInMs > MS_PER_HOUR) {
            result.append(timeInMs / MS_PER_HOUR).append(" hrs ")
        }
        if (timeInMs > MS_PER_MINUTE) {
            result.append((timeInMs % MS_PER_HOUR) / MS_PER_MINUTE).append(" mins ")
        }
        result.append((timeInMs % MS_PER_MINUTE) / 1000.0).append(" secs")
        result.toString()
    }
}
