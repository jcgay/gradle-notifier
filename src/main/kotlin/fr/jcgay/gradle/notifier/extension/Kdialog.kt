package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Kdialog: NotifierConfiguration {
    var path: String? = null

    override fun asProperties(): Properties {
        val result = Properties()
        path?.let { result["notifier.kdialog.path"] = it }
        return result
    }
}
