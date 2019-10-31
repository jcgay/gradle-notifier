package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class BurntToast: NotifierConfiguration {
    var sound: String? = null

    override fun asProperties(): Properties {
        val result = Properties()
        sound?.let { result["notifier.burnttoast.sound"] = it }
        return result
    }
}
