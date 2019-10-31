package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Growl: NotifierConfiguration {
    var host: String? = null
    var port: Int? = null
    var password: String? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.growl"
        val result = Properties()
        host?.let { result["${prefix}.host"] = it }
        port?.let { result["${prefix}.port"] = it }
        password?.let { result["${prefix}.password"] = it }
        return result
    }
}
