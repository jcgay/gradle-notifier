package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.Status.FAILURE
import fr.jcgay.gradle.notifier.Status.SUCCESS
import fr.jcgay.gradle.notifier.extension.TimeThreshold
import fr.jcgay.notification.Notification
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotificationException
import org.gradle.BuildAdapter
import org.gradle.BuildResult
import org.slf4j.LoggerFactory


class NotifierListener(val notifier: Notifier, private val timer: Clock, private val threshold: TimeThreshold): BuildAdapter() {

    private val logger = LoggerFactory.getLogger(NotifierListener::class.java)

    init {
        logger.debug("Will send notification with: {} if execution last more than {}", notifier, threshold)
    }

    override fun buildFinished(result: BuildResult) {
        try {
            if (TimeThreshold(timer) >= threshold || notifier.isPersistent) {
                val status = status(result)
                val notification = Notification.builder()
                    .title(result.gradle?.rootProject?.name)
                    .message(message(result))
                    .icon(status.icon)
                    .subtitle(status.message)
                    .level(status.level)
                    .build()
                logger.debug("Sending notification: {}", notification)
                notifier.send(notification)
            }
        } catch (e: SendNotificationException) {
            logger.warn("Error while sending notification.", e)
        } finally {
            notifier.close()
        }
    }

    private fun message(result: BuildResult): String? {
        return if (hasSucceeded(result)) {
            "Done in: ${timer.getTime()}."
        } else {
            result.failure?.message ?: "Build Failed."
        }
    }

    private fun status(result: BuildResult): Status = if (hasSucceeded(result)) SUCCESS else FAILURE

    private fun hasSucceeded(result: BuildResult): Boolean = result.failure == null
}
