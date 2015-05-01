package fr.jcgay.gradle.notifier

import fr.jcgay.notification.Application
import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotification
import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.gradle.api.invocation.Gradle
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

import static org.mockito.Matchers.isA
import static org.mockito.Mockito.*

@CompileStatic
class GradleNotifierPluginTest {

    @Rule
    public MockitoRule mockito = MockitoJUnit.rule()

    @Mock
    Gradle gradle

    @Mock
    SendNotification sendNotification

    @InjectMocks
    GradleNotifierPlugin plugin

    @Test
    void 'should add listener'() {
        when(sendNotification.setApplication(isA(Application))).thenReturn(sendNotification)
        when(sendNotification.chooseNotifier()).thenReturn(mock(Notifier))

        plugin.apply(project(gradle))

        verify(sendNotification).setApplication(
            Application.builder(
                'application/x-vnd-gradle-inc.gradle',
                'Gradle',
                Icon.create(SendNotification.getResource("/GradleLogoReg.png"), 'gradle')
            ).build()
        )
        verify(gradle).addBuildListener(isA(NotifierListener))
    }

    private static Project project(Gradle gradle) {
        def project = mock(Project)
        when(project.gradle).thenReturn(gradle)
        project
    }
}
