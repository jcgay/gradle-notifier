package fr.jcgay.gradle.notifier
import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notification
import groovy.transform.CompileStatic

import static fr.jcgay.notification.Notification.Level.ERROR
import static fr.jcgay.notification.Notification.Level.INFO

@CompileStatic
enum Status {

    SUCCESS(createIcon('/dialog-clean.png', 'success'), 'Success', INFO),
    FAILURE(createIcon('/dialog-error-5.png', 'failure'), 'Failure', ERROR)

    final Icon icon
    final String message
    final Notification.Level level

    Status(Icon icon, String message, Notification.Level level) {
        this.level = level
        this.icon = icon
        this.message = message
    }

    private static Icon createIcon(String resource, String id) {
        Icon.create(Status.getResource(resource), id)
    }
}
