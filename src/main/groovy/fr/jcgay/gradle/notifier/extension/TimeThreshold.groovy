package fr.jcgay.gradle.notifier.extension

import fr.jcgay.gradle.notifier.time.Stopwatch
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.util.concurrent.TimeUnit

import static java.util.concurrent.TimeUnit.NANOSECONDS

@CompileStatic
@EqualsAndHashCode
@ToString
class TimeThreshold implements Comparable<TimeThreshold> {

    Long time = -1
    TimeUnit unit = NANOSECONDS

    @Override
    int compareTo(TimeThreshold o) {
        if (this.equals(o)) {
            return 0
        }
        if (!o) {
            return -1
        }
        if (unit == o.unit) {
            return time <=> o.time
        }
        return unit.toNanos(time) <=> o.unit.toNanos(o.time)
    }

    static TimeThreshold of(Stopwatch stopwatch) {
        new TimeThreshold(time: stopwatch.elapsed(NANOSECONDS), unit: NANOSECONDS)
    }
}
