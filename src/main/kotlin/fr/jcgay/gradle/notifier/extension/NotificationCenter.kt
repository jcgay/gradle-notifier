package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class NotificationCenter: NotifierConfiguration {
    var path: String? = null
    var activate: String? = null
    var sound: String? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.notification-center"
        val result = Properties()
        path?.let { result["${prefix}.path"] = it }
        activate?.let { result["${prefix}.activate"] = it }
        sound?.let { result["${prefix}.sound"] = it }
        return result
    }
}
