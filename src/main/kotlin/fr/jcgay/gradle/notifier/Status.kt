package fr.jcgay.gradle.notifier

import fr.jcgay.notification.Icon
import fr.jcgay.notification.Notification


enum class Status(val icon: Icon, val message: String, val level: Notification.Level) {

    SUCCESS(Icon.create(object {}.javaClass.getResource("/dialog-clean.png"), "success"),
        "Success", Notification.Level.INFO),
    FAILURE(Icon.create(object {}.javaClass.getResource("/dialog-error-5.png"), "failure"),
        "Failure", Notification.Level.ERROR)
}
