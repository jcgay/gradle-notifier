package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Notifu: NotifierConfiguration {
    var path: String? = null

    override fun asProperties(): Properties {
        val result = Properties()
        path?.let { result["notifier.notifu.path"] = it }
        return result
    }
}
