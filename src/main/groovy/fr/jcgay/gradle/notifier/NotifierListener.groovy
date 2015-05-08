package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.extension.TimeThreshold
import fr.jcgay.gradle.notifier.time.Stopwatch
import fr.jcgay.notification.Notification
import fr.jcgay.notification.Notifier
import groovy.transform.CompileStatic
import org.gradle.BuildAdapter
import org.gradle.BuildResult

import static fr.jcgay.gradle.notifier.Status.FAILURE
import static fr.jcgay.gradle.notifier.Status.SUCCESS
import static java.util.concurrent.TimeUnit.SECONDS

@CompileStatic
class NotifierListener extends BuildAdapter {

    private final Notifier notifier
    private final Stopwatch timer
    private final TimeThreshold threshold

    NotifierListener(Notifier notifier, Stopwatch timer, TimeThreshold threshold) {
        this.threshold = threshold
        this.timer = timer
        this.notifier = notifier
        notifier.init()
    }

    @Override
    void buildFinished(BuildResult result) {
        try {
            if (TimeThreshold.of(timer) >= threshold) {
                def status = status(result)
                notifier.send(
                    Notification.builder(result.gradle.rootProject.name, message(result), status.icon)
                        .withSubtitle(status.message)
                        .withLevel(status.level)
                        .build()
                )
            }
        } finally {
            notifier.close()
        }
    }

    private String message(BuildResult result) {
        hasSucceeded(result) ? "Done in: ${timer.elapsed(SECONDS)} second(s)."
            : result.failure.message
    }

    private static boolean hasSucceeded(BuildResult result) {
        result.failure == null
    }

    private static Status status(BuildResult result) {
        hasSucceeded(result) ? SUCCESS : FAILURE
    }
}
