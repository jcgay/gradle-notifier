package fr.jcgay.gradle.notifier
import fr.jcgay.gradle.notifier.extension.TimeThreshold
import fr.jcgay.notification.Notification
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotificationException
import org.gradle.BuildResult
import org.gradle.api.Project
import org.gradle.api.invocation.Gradle
import spock.lang.Specification

import static org.gradle.util.KnownElapsedTimeClock.elapsedTimeClock
import static fr.jcgay.gradle.notifier.Status.FAILURE
import static fr.jcgay.gradle.notifier.Status.SUCCESS
import static fr.jcgay.notification.Notification.Level.ERROR
import static fr.jcgay.notification.Notification.Level.INFO
import static java.util.concurrent.TimeUnit.SECONDS

class NotifierListenerTest extends Specification {

    Notifier notifier = Mock(Notifier)

    NotifierListener listener

    def setup() {
        listener = new NotifierListener(notifier, elapsedTimeClock(SECONDS.toMillis(5)), new TimeThreshold())
    }

    def 'should send a successful notification when build ends'() {
        when:
        listener.buildFinished(successBuild('project'))

        then:
        1 * notifier.send(
            Notification.builder()
                .title('project')
                .message('Done in: 5.0 secs.')
                .icon(SUCCESS.icon)
                .subtitle('Success')
                .level(INFO)
                .build()
        )
        1 * notifier.close()
    }

    def 'should send a failure notification when build ends'() {
        when:
        listener.buildFinished(failBuild('project fail', 'exception message'))

        then:
        1 * notifier.send(
            Notification.builder()
                .title('project fail')
                .message('exception message')
                .icon(FAILURE.icon)
                .subtitle('Failure')
                .level(ERROR)
                .build()
        )
        1 * notifier.close()
    }

    def "should send a failure notification with a default message when failure has no message"() {
        when:
        listener.buildFinished(failBuild('project fail', null))

        then:
        1 * notifier.send(
            Notification.builder()
                .title('project fail')
                .message('Build Failed.')
                .icon(FAILURE.icon)
                .subtitle('Failure')
                .level(ERROR)
                .build()
        )
        1 * notifier.close()
    }

    def 'should not send a notification when build exceed threshold'() {
        given:
        def listener = new NotifierListener(
            notifier,
            elapsedTimeClock(SECONDS.toMillis(5)),
            new TimeThreshold(time: 10L, unit: SECONDS)
        )

        when:
        listener.buildFinished(successBuild('project'))

        then:
        0 * notifier.send(_)
    }

    def 'should always send a notification when notifier is persistent'() {
        given:
        def listener = new NotifierListener(
            notifier,
            elapsedTimeClock(SECONDS.toMillis(5)),
            new TimeThreshold(time: 10L, unit: SECONDS)
        )

        when:
        listener.buildFinished(successBuild('project'))

        then:
        1 * notifier.isPersistent() >> true
        1 * notifier.send(_)
    }

    def "should fail silently when sending notification fails"() {
        when:
        listener.buildFinished(successBuild('success'))

        then:
        1 * notifier.send(_) >> { throw new SendNotificationException('fail') }
        noExceptionThrown()
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
