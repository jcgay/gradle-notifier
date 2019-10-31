package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Pushbullet: NotifierConfiguration {
    var apikey: String? = null
    var device: String? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.pushbullet"
        val result = Properties()
        apikey?.let { result["${prefix}.apikey"] = it }
        device?.let { result["${prefix}.device"] = it }
        return result
    }
}
