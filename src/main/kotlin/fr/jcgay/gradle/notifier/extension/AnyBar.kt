package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class AnyBar: NotifierConfiguration {
    var host: String? = null
    var port: Int? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.anybar"
        val result = Properties()
        host?.let { result["${prefix}.host"] = it }
        port?.let { result["${prefix}.port"] = it }
        return result
    }
}
