package fr.jcgay.gradle.notifier.extension

import groovy.lang.Closure
import org.gradle.util.ConfigureUtil.configure
import java.util.Properties


open class Configuration {

    var implementation: String = ""
    var continuousNotify = false

    var growl = Growl()
    var snarl = Snarl()
    var pushbullet = Pushbullet()
    var notifySend = NotifySend()
    var notificationCenter = NotificationCenter()
    var systemTray = SystemTray()
    var notifu = Notifu()
    var kdialog = Kdialog()
    var anyBar = AnyBar()
    var toaster = Toaster()
    var notify = Notify()
    var burnttoast = BurntToast()
    var slack = Slack()

    var threshold = TimeThreshold()
    var timeout = Timeout()

    fun growl(closure: Closure<Any>) {
        configure(closure, growl)
    }
    fun snarl(closure: Closure<Any>) {
        configure(closure, snarl)
    }
    fun pushbullet(closure: Closure<Any>) {
        configure(closure, pushbullet)
    }
    fun notifySend(closure: Closure<Any>) {
        configure(closure, notifySend)
    }
    fun notificationcenter(closure: Closure<Any>) {
        configure(closure, notificationCenter)
    }
    fun systemtray(closure: Closure<Any>) {
        configure(closure, systemTray)
    }
    fun notifu(closure: Closure<Any>) {
        configure(closure, notifu)
    }
    fun kdialog(closure: Closure<Any>) {
        configure(closure, kdialog)
    }
    fun anybar(closure: Closure<Any>) {
        configure(closure, anyBar)
    }
    fun toaster(closure: Closure<Any>) {
        configure(closure, toaster)
    }
    fun notify(closure: Closure<Any>) {
        configure(closure, notify)
    }
    fun burnttoast(closure: Closure<Any>) {
        configure(closure, burnttoast)
    }
    fun slack(closure: Closure<Any>) {
        configure(closure, slack)
    }

    fun threshold(closure: Closure<Any>) {
        configure(closure, threshold)
    }
    fun timeout(closure: Closure<Any>) {
        configure(closure, timeout)
    }

    fun asProperties(): Properties {
        val result = Properties()
        if (implementation.isNotBlank()) {
            result["notifier.implementation"] = implementation
        }
        listOf(growl, snarl, pushbullet, notifySend, notificationCenter, systemTray, notifu, kdialog, anyBar, toaster, notify, burnttoast, slack).forEach {
            result.putAll(it.asProperties())
        }
        return result
    }
}
