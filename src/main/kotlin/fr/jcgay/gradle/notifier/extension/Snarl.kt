package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Snarl: NotifierConfiguration {
    var host: String? = null
    var port: Int? = null
    var appPassword: String? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.snarl"
        val result = Properties()
        host?.let { result["${prefix}.host"] = it }
        port?.let { result["${prefix}.port"] = it }
        appPassword?.let { result["${prefix}.appPassword"] = it }
        return result
    }
}
