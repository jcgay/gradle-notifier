package fr.jcgay.gradle.notifier.extension

import org.gradle.api.Action
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

    fun growl(closure: Action<Growl>) {
        closure.execute(growl)
    }
    fun snarl(closure: Action<Snarl>) {
        closure.execute(snarl)
    }
    fun pushbullet(closure: Action<Pushbullet>) {
        closure.execute(pushbullet)
    }
    fun notifySend(closure: Action<NotifySend>) {
        closure.execute(notifySend)
    }
    fun notificationcenter(closure: Action<NotificationCenter>) {
        closure.execute(notificationCenter)
    }
    fun systemtray(closure: Action<SystemTray>) {
        closure.execute(systemTray)
    }
    fun notifu(closure: Action<Notifu>) {
        closure.execute(notifu)
    }
    fun kdialog(closure: Action<Kdialog>) {
        closure.execute(kdialog)
    }
    fun anybar(closure: Action<AnyBar>) {
        closure.execute(anyBar)
    }
    fun toaster(closure: Action<Toaster>) {
        closure.execute(toaster)
    }
    fun notify(closure: Action<Notify>) {
        closure.execute(notify)
    }
    fun burnttoast(closure: Action<BurntToast>) {
        closure.execute(burnttoast)
    }
    fun slack(closure: Action<Slack>) {
        closure.execute(slack)
    }

    fun threshold(closure: Action<TimeThreshold>) {
        closure.execute(threshold)
    }
    fun timeout(closure: Action<Timeout>) {
        closure.execute(timeout)
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
