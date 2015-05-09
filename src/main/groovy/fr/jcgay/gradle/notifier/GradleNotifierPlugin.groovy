package fr.jcgay.gradle.notifier
import fr.jcgay.gradle.notifier.extension.Configuration
import fr.jcgay.gradle.notifier.time.Stopwatch
import fr.jcgay.notification.Application
import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotification
import groovy.transform.PackageScope
import org.gradle.api.Plugin
import org.gradle.api.Project

class GradleNotifierPlugin implements Plugin<Project> {

    private static final Icon GRADLE_ICON = Icon.create(SendNotification.getResource("/GradleLogoReg.png"), 'gradle')
    private static final Application APPLICATION = Application.builder('application/x-vnd-gradle-inc.gradle', 'Gradle', GRADLE_ICON).build()

    private final Stopwatch stopwatch

    GradleNotifierPlugin() {
        this.stopwatch = Stopwatch.createStarted()
    }

    @Override
    void apply(Project project) {
        project.extensions.create('notifier', Configuration)

        project.afterEvaluate {
            Notifier notifier = new SendNotification()
                .setApplication(APPLICATION)
                .addConfigurationProperties(mergeProperties(project.notifier))
                .chooseNotifier()

            project.gradle.addBuildListener(new NotifierListener(notifier, stopwatch, project.notifier.threshold))
        }

    }

    @PackageScope
    static Properties mergeProperties(Configuration userConfig) {
        Properties result = [:]
        result << userConfig.asProperties()
        result << System.getProperties().findAll { (it.key as String).startsWith('notifier') }
        result
    }
}

