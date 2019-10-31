package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class Toaster: NotifierConfiguration {
    var path: String? = null

    override fun asProperties(): Properties {
        val result = Properties()
        path?.let { result["notifier.toaster.bin"] = it }
        return result
    }
}
