package fr.jcgay.gradle.notifier.extension

import org.junit.Test

import static fr.jcgay.gradle.notifier.KnownElapsedTimeTicker.aStopWatchWithElapsedTime
import static java.util.concurrent.TimeUnit.MILLISECONDS
import static java.util.concurrent.TimeUnit.NANOSECONDS
import static java.util.concurrent.TimeUnit.SECONDS
import static org.assertj.core.api.Assertions.assertThat

class TimeThresholdTest {

    @Test
    void 'should be equals'() {
        assertThat(new TimeThreshold(time: 1, unit: SECONDS)).isEqualTo(new TimeThreshold(time: 1, unit: SECONDS))
    }

    @Test
    public void 'should be greater'() {
        assertThat(new TimeThreshold(time: 1, unit: SECONDS)).isGreaterThan(new TimeThreshold(time: 1, unit: MILLISECONDS))
        assertThat(new TimeThreshold(time: 10, unit: SECONDS)).isGreaterThan(new TimeThreshold(time: 1, unit: SECONDS))
    }

    @Test
    void 'should be less'() {
        assertThat(new TimeThreshold(time: 1, unit: MILLISECONDS)).isLessThan(new TimeThreshold(time: 1, unit: SECONDS))
        assertThat(new TimeThreshold(time: 1, unit: SECONDS)).isLessThan(new TimeThreshold(time: 10, unit: SECONDS))
    }

    @Test
    void 'should create from stopwath'() {
        def result = TimeThreshold.of(aStopWatchWithElapsedTime(5))

        assertThat(result).isEqualTo(new TimeThreshold(time: 5, unit: NANOSECONDS))
    }
}
