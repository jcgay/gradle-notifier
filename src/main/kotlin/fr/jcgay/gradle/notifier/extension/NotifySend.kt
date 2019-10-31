package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class NotifySend: NotifierConfiguration {
    var path: String? = null
    var timeout: Long? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.notify-send"
        val result = Properties()
        path?.let { result["${prefix}.path"] = it }
        timeout?.let { result["${prefix}.timeout"] = it }
        return result
    }
}
