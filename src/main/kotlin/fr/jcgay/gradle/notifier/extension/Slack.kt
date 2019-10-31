package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Slack: NotifierConfiguration {
    var token: String? = null
    var channel: String? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.slack"
        val result = Properties()
        token?.let { result["${prefix}.token"] = it }
        channel?.let { result["${prefix}.channel"] = it }
        return result
    }
}
