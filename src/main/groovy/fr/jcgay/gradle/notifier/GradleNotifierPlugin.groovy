package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.extension.Configuration
import fr.jcgay.gradle.notifier.time.Stopwatch
import fr.jcgay.notification.Application
import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotification
import org.gradle.api.Plugin
import org.gradle.api.Project

import javax.inject.Inject

class GradleNotifierPlugin implements Plugin<Project> {

    private static final Icon GRADLE_ICON = Icon.create(SendNotification.getResource("/GradleLogoReg.png"), 'gradle')
    private static final Application APPLICATION = Application.builder('application/x-vnd-gradle-inc.gradle', 'Gradle', GRADLE_ICON).build()

    private final Stopwatch stopwatch
    private final SendNotification factory

    @Inject
    GradleNotifierPlugin() {
        this(new SendNotification())
    }

    GradleNotifierPlugin(SendNotification factory) {
        this.stopwatch = Stopwatch.createStarted()
        this.factory = factory
    }

    @Override
    void apply(Project project) {
        project.extensions.create('notifier', Configuration)

        project.afterEvaluate {
            Notifier notifier = factory
                .setApplication(APPLICATION)
                .addConfigurationProperties(project.notifier.asProperties())
                .chooseNotifier()

            project.gradle.addBuildListener(new NotifierListener(notifier, stopwatch))
        }

    }
}

