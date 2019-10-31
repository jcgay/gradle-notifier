package fr.jcgay.gradle.notifier.extension

import fr.jcgay.gradle.notifier.KnownElapsedTimeClock
import spock.lang.Specification

import static java.util.concurrent.TimeUnit.MILLISECONDS
import static java.util.concurrent.TimeUnit.SECONDS

class TimeThresholdTest extends Specification {

    def 'should be equals'() {
        expect:
        a == b

        where:
        a = new TimeThreshold(1, SECONDS)
        b = new TimeThreshold(1, SECONDS)
    }

    def 'should be greater'() {
        expect:
        a > b

        where:
        a                                          | b
        new TimeThreshold(1, SECONDS)  | new TimeThreshold(1, MILLISECONDS)
        new TimeThreshold(10, SECONDS) | new TimeThreshold(1, SECONDS)
    }

    def 'should be less'() {
        expect:
        a < b

        where:
        a                                               | b
        new TimeThreshold(1, MILLISECONDS)  | new TimeThreshold(1, SECONDS)
        new TimeThreshold(1, SECONDS)       | new TimeThreshold(10, SECONDS)
    }

    def 'should create from stopwath'() {
        when:
        def result = new TimeThreshold(new KnownElapsedTimeClock(5))

        then:
        result == new TimeThreshold(5, MILLISECONDS)
    }
}
