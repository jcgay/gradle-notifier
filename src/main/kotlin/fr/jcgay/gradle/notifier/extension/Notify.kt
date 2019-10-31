package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Notify: NotifierConfiguration {
    var position: String? = null
    var darkstyle: Boolean? = null

    override fun asProperties(): Properties {
        val prefix = "notifier.notify"
        val result = Properties()
        position?.let { result["${prefix}.position"] = it }
        darkstyle?.let { result["${prefix}.darkstyle"] = it }
        return result
    }
}
