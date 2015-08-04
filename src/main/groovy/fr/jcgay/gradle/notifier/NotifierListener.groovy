package fr.jcgay.gradle.notifier
import fr.jcgay.gradle.notifier.extension.TimeThreshold
import fr.jcgay.notification.Notification
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotificationException
import groovy.transform.CompileStatic
import org.gradle.BuildAdapter
import org.gradle.BuildResult
import org.gradle.api.logging.Logger
import org.gradle.api.logging.Logging
import org.gradle.util.Clock

import static fr.jcgay.gradle.notifier.Status.FAILURE
import static fr.jcgay.gradle.notifier.Status.SUCCESS

@CompileStatic
class NotifierListener extends BuildAdapter {

    private static final Logger LOGGER = Logging.getLogger(NotifierListener)

    private final Notifier notifier
    private final Clock timer
    private final TimeThreshold threshold

    NotifierListener(Notifier notifier, Clock timer, TimeThreshold threshold) {
        this.threshold = threshold
        this.timer = timer
        this.notifier = notifier
        LOGGER.debug("Will send notification with: {} if execution last more than {}", notifier, threshold)
    }

    @Override
    void buildFinished(BuildResult result) {
        try {
            if (TimeThreshold.of(timer) >= threshold || notifier.isPersistent()) {
                def status = status(result)
                def notification = Notification.builder()
                    .title(result.gradle.rootProject.name)
                    .message(message(result))
                    .icon(status.icon)
                    .subtitle(status.message)
                    .level(status.level)
                    .build()
                LOGGER.debug("Sending notification: {}", notification)
                notifier.send(notification)
            }
        } catch (SendNotificationException e) {
            LOGGER.warn("Error while sending notification.", e)
        } finally {
            notifier.close()
        }
    }


    private String message(BuildResult result) {
        hasSucceeded(result) ? "Done in: ${timer.time}."
            : result.failure.message?:'Build Failed.'
    }

    private static boolean hasSucceeded(BuildResult result) {
        result.failure == null
    }

    private static Status status(BuildResult result) {
        hasSucceeded(result) ? SUCCESS : FAILURE
    }
}
