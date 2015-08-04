package fr.jcgay.gradle.notifier.extension

import spock.lang.Specification

import static org.gradle.util.KnownElapsedTimeClock.elapsedTimeClock
import static java.util.concurrent.TimeUnit.*

class TimeThresholdTest extends Specification {

    def 'should be equals'() {
        expect:
        a == b

        where:
        a = new TimeThreshold(time: 1, unit: SECONDS)
        b = new TimeThreshold(time: 1, unit: SECONDS)
    }

    def 'should be greater'() {
        expect:
        a > b

        where:
        a                                          | b
        new TimeThreshold(time: 1, unit: SECONDS)  | new TimeThreshold(time: 1, unit: MILLISECONDS)
        new TimeThreshold(time: 10, unit: SECONDS) | new TimeThreshold(time: 1, unit: SECONDS)
    }

    def 'should be less'() {
        expect:
        a < b

        where:
        a                                               | b
        new TimeThreshold(time: 1, unit: MILLISECONDS)  | new TimeThreshold(time: 1, unit: SECONDS)
        new TimeThreshold(time: 1, unit: SECONDS)       | new TimeThreshold(time: 10, unit: SECONDS)
    }

    def 'should create from stopwath'() {
        when:
        def result = TimeThreshold.of(elapsedTimeClock(5))

        then:
        result == new TimeThreshold(time: 5, unit: MILLISECONDS)
    }
}
