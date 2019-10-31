package fr.jcgay.gradle.notifier.extension

import java.util.Properties


class SystemTray: NotifierConfiguration {
    var wait: Int? = null

    override fun asProperties(): Properties {
        val result = Properties()
        wait?.let { result["notifier.system-tray.wait"] = it }
        return result
    }
}
