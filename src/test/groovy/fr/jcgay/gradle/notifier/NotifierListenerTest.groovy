package fr.jcgay.gradle.notifier
import fr.jcgay.gradle.notifier.extension.TimeThreshold
import fr.jcgay.gradle.notifier.time.Stopwatch
import fr.jcgay.notification.Notification
import fr.jcgay.notification.Notifier
import org.gradle.BuildResult
import org.gradle.api.Project
import org.gradle.api.invocation.Gradle
import spock.lang.Specification

import static fr.jcgay.gradle.notifier.KnownElapsedTimeTicker.aStartedStopwatchWithElapsedTime
import static fr.jcgay.gradle.notifier.Status.FAILURE
import static fr.jcgay.gradle.notifier.Status.SUCCESS
import static fr.jcgay.notification.Notification.Level.ERROR
import static fr.jcgay.notification.Notification.Level.INFO
import static java.util.concurrent.TimeUnit.SECONDS

class NotifierListenerTest extends Specification {

    Notifier notifier = Mock(Notifier)

    NotifierListener listener

    def setup() {
        listener = new NotifierListener(notifier, aStartedStopwatchWithElapsedTime(SECONDS.toNanos(5)), new TimeThreshold())
    }

    def 'should init notifier when instantiating NotifierListener'() {
        given:
        def notifier = Mock(Notifier)

        when:
        new NotifierListener(notifier, anyStopwatch(), new TimeThreshold())

        then:
        1 * notifier.init()
    }

    def 'should send a successful notification when build ends'() {
        when:
        listener.buildFinished(successBuild('project'))

        then:
        1 * notifier.send(
            Notification.builder('project', 'Done in: 5 second(s).', SUCCESS.icon)
                .withSubtitle('Success')
                .withLevel(INFO)
                .build()
        )
        1 * notifier.close()
    }

    def 'should send a failure notification when build ends'() {
        when:
        listener.buildFinished(failBuild('project fail', 'exception message'))

        then:
        1 * notifier.send(
            Notification.builder('project fail', 'exception message', FAILURE.icon)
                .withSubtitle('Failure')
                .withLevel(ERROR)
                .build()
        )
        1 * notifier.close()
    }

    def 'should not send a notification when build exceed threshold'() {
        given:
        def listener = new NotifierListener(
            notifier,
            aStartedStopwatchWithElapsedTime(SECONDS.toNanos(5)),
            new TimeThreshold(time: 10L, unit: SECONDS)
        )

        when:
        listener.buildFinished(successBuild('project'))

        then:
        0 * notifier.send(_)
    }

    private static Stopwatch anyStopwatch() {
        aStartedStopwatchWithElapsedTime(SECONDS.toNanos(5))
    }

    private BuildResult successBuild(String name) {
        new BuildResult(gradle(name), null)
    }

    private BuildResult failBuild(String name, String failureMessage) {
        new BuildResult(gradle(name), new NullPointerException(failureMessage))
    }

    private Gradle gradle(String name) {
        def gradle = Stub(Gradle) {
            getRootProject() >> Stub(Project) {
                getName() >> name
            }
        }
        gradle
    }
}
