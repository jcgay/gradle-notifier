package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.extension.TimeThreshold
import fr.jcgay.notification.Notification
import fr.jcgay.notification.Notifier
import groovy.transform.CompileStatic
import org.gradle.BuildResult
import org.gradle.api.invocation.Gradle
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import static fr.jcgay.gradle.notifier.KnownElapsedTimeTicker.aStartedStopwatchWithElapsedTime
import static fr.jcgay.gradle.notifier.Status.FAILURE
import static fr.jcgay.gradle.notifier.Status.SUCCESS
import static fr.jcgay.notification.Notification.Level.ERROR
import static fr.jcgay.notification.Notification.Level.INFO
import static java.util.concurrent.TimeUnit.SECONDS
import static org.assertj.core.api.Assertions.assertThat
import static org.mockito.Answers.RETURNS_DEEP_STUBS
import static org.mockito.Matchers.any
import static org.mockito.Mockito.never
import static org.mockito.Mockito.verify
import static org.mockito.Mockito.when

@CompileStatic
public class NotifierListenerTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule()

    @Mock
    Notifier notifier

    @Mock(answer = RETURNS_DEEP_STUBS)
    Gradle gradle

    @Captor
    ArgumentCaptor<Notification> capturedNotification

    NotifierListener listener

    @Before
    void 'setup'() {
        listener = new NotifierListener(notifier, aStartedStopwatchWithElapsedTime(SECONDS.toNanos(5)), new TimeThreshold())
    }

    @Test
    void 'should init notifier when instantiating NotifierListener'() {
        verify(notifier).init()
    }

    @Test
    void 'should send a successful notification when build ends'() {
        listener.buildFinished(successBuild('project'))

        verify(notifier).send(capturedNotification.capture())
        verify(notifier).close()

        def notification = capturedNotification.getValue()
        assertThat(notification.title()).isEqualTo('project')
        assertThat(notification.subtitle()).isEqualTo('Success')
        assertThat(notification.icon()).isSameAs(SUCCESS.icon)
        assertThat(notification.message()).isEqualTo('Done in: 5 second(s).')
        assertThat(notification.level()).isEqualTo(INFO)
    }

    @Test
    void 'should send a failure notification when build ends'() {
        listener.buildFinished(failBuild('project fail', 'exception message'))

        verify(notifier).send(capturedNotification.capture())
        verify(notifier).close()

        def notification = capturedNotification.getValue()
        assertThat(notification.title()).isEqualTo('project fail')
        assertThat(notification.subtitle()).isEqualTo('Failure')
        assertThat(notification.icon()).isSameAs(FAILURE.icon)
        assertThat(notification.message()).isEqualTo('exception message')
        assertThat(notification.level()).isEqualTo(ERROR)
    }

    @Test
    void 'should not send a notification when build exceed threshold'() {
        def listener = new NotifierListener(
            notifier,
            aStartedStopwatchWithElapsedTime(SECONDS.toNanos(5)),
            new TimeThreshold(time: 10L, unit: SECONDS)
        )

        listener.buildFinished(successBuild('project'))

        verify(notifier, never()).send(any(Notification))
    }

    private BuildResult successBuild(String name) {
        when(gradle.rootProject.name).thenReturn(name)
        new BuildResult(gradle, null)
    }

    private BuildResult failBuild(String name, String failureMessage) {
        when(gradle.rootProject.name).thenReturn(name)
        new BuildResult(gradle, new NullPointerException(failureMessage))
    }
}
