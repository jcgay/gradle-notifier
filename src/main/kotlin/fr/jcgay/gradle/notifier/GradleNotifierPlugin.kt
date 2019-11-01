package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.extension.Configuration
import fr.jcgay.notification.Application
import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotification
import fr.jcgay.notification.SendNotificationException
import fr.jcgay.notification.notifier.DoNothingNotifier
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.slf4j.LoggerFactory
import java.util.Date
import java.util.Properties
import java.util.concurrent.TimeUnit.MILLISECONDS
import javax.inject.Inject


class GradleNotifierPlugin constructor(private val sendNotification: SendNotification): Plugin<Project> {

    private val logger = LoggerFactory.getLogger(this.javaClass)
    private val startTime = Clock(Date().time)

    @Inject
    constructor() : this(SendNotification())

    override fun apply(project: Project) {
        project.extensions.create("notifier", Configuration::class.java)

        project.afterEvaluate {
            if (shouldNotifiy(this)) {
                val config = this.extensions.getByType(Configuration::class.java)
                val notifier: Notifier = createNotifier(config)
                this.gradle.addBuildListener(NotifierListener(notifier, startTime, config.threshold))
            }
        }
    }

    fun createNotifier(config: Configuration): Notifier {
        val application = Application.builder("application/x-vnd-gradle-inc.gradle", "Gradle", GRADLE_ICON)
        val userTimeout = config.timeout
        userTimeout.time?.let {time ->
            userTimeout.unit?.let { unit ->
                application.timeout(MILLISECONDS.convert(time, unit))
            }
        }
        return try {
            sendNotification.setApplication(application.build())
                .addConfigurationProperties(mergeProperties(config))
                .initNotifier()
        } catch (e: SendNotificationException) {
            logger.warn("Cannot initialize a notifier.", e)
            DoNothingNotifier.doNothing()
        }
    }

    private fun shouldNotifiy(project: Project): Boolean = when {
        project.gradle.rootProject.name == "buildSrc" -> false
        project.gradle.startParameter.isContinuous -> project.extensions.getByType(Configuration::class.java).continuousNotify
        else -> true
    }

    companion object {
        val GRADLE_ICON: Icon = Icon.create(GradleNotifierPlugin::class.java.getResource("/GradleLogoReg.png"), "gradle")

        @JvmStatic
        fun mergeProperties(config: Configuration): Properties {
            val result = Properties(config.asProperties())
            result.putAll(System.getProperties().filter { it.key.toString().startsWith("notifier") })
            return result
        }
    }
}
