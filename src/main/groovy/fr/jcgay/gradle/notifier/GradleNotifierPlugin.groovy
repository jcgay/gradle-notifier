package fr.jcgay.gradle.notifier
import fr.jcgay.gradle.notifier.extension.Configuration
import fr.jcgay.notification.Application
import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotification
import fr.jcgay.notification.SendNotificationException
import fr.jcgay.notification.notifier.DoNothingNotifier
import groovy.transform.PackageScope
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.initialization.BuildRequestMetaData
import org.gradle.util.Clock
import org.gradle.util.GradleVersion

import javax.inject.Inject

class GradleNotifierPlugin implements Plugin<Project> {

    private static final Logger LOGGER = Logging.getLogger(GradleNotifierPlugin)

    private static final Icon GRADLE_ICON = Icon.create(SendNotification.getResource("/GradleLogoReg.png"), 'gradle')
    private static final Application APPLICATION = Application.builder('application/x-vnd-gradle-inc.gradle', 'Gradle', GRADLE_ICON).build()

    private final SendNotification sendNotification

    @Inject
    GradleNotifierPlugin() {
        this(new SendNotification())
    }

    GradleNotifierPlugin(SendNotification sendNotification) {
        this.sendNotification = sendNotification
    }

    @Override
    void apply(Project project) {
        project.extensions.create('notifier', Configuration)

        project.afterEvaluate {
            if (gradleVersion(project) < GradleVersion.version('2.5') || hasContinuousNotification(project)) {
                Notifier notifier = createNotifier(project.notifier)
                project.gradle.addBuildListener(new NotifierListener(notifier, clock(project), project.notifier.threshold))
            }
        }
    }

    @PackageScope
    Notifier createNotifier(Configuration configuration) {
        try {
            return sendNotification
                .setApplication(APPLICATION)
                .addConfigurationProperties(mergeProperties(configuration))
                .initNotifier()
        } catch (SendNotificationException e) {
            LOGGER.warn("Cannot initialize a notifier.", e)
            return DoNothingNotifier.doNothing()
        }
    }

    @PackageScope
    static Properties mergeProperties(Configuration userConfig) {
        Properties result = [:]
        result << userConfig.asProperties()
        result << System.getProperties().findAll { (it.key as String).startsWith('notifier') }
        result
    }

    private static Clock clock(Project project) {
        project.gradle.services.get(BuildRequestMetaData).buildTimeClock
    }

    private static GradleVersion gradleVersion(Project project) {
        GradleVersion.version(project.gradle.gradleVersion)
    }

    private static boolean hasContinuousNotification(Project project) {
        if (project.gradle.startParameter.isContinuous()) {
            return project.notifier.continuousNotify
        }
        true
    }
}

