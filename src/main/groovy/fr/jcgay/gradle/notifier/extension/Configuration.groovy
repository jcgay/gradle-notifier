package fr.jcgay.gradle.notifier.extension

import static org.gradle.util.ConfigureUtil.configure

class Configuration {

    String implementation

    Growl growl = new Growl()
    Snarl snarl = new Snarl()
    Pushbullet pushbullet = new Pushbullet()
    NotifySend notifySend = new NotifySend()
    NotificationCenter notificationCenter = new NotificationCenter()
    SystemTray systemTray = new SystemTray()
    Notifu notifu = new Notifu()
    Kdialog kdialog = new Kdialog()
    AnyBar anyBar = new AnyBar()
    TimeThreshold threshold = new TimeThreshold()

    static Properties createProperties(Map properties, String parentKey) {
        def result = new Properties()
        properties.collect {
            if (it.value) {
                result << [("${parentKey}.${it.key}".toString()): it.value]
            }
        }
        result
    }

    void growl(Closure closure) {
        configure(closure, growl)
    }

    void snarl(Closure closure) {
        configure(closure, snarl)
    }

    void pushbullet(Closure closure) {
        configure(closure, pushbullet)
    }

    void notifysend(Closure closure) {
        configure(closure, notifySend)
    }

    void notificationcenter(Closure closure) {
        configure(closure, notificationCenter)
    }

    void systemtray(Closure closure) {
        configure(closure, systemTray)
    }

    void notifu(Closure closure) {
        configure(closure, notifu)
    }

    void kdialog(Closure closure) {
        configure(closure, kdialog)
    }

    void anybar(Closure closure) {
        configure(closure, anyBar)
    }

    void threshold(Closure closure) {
        configure(closure, threshold)
    }

    Properties asProperties() {
        def result = new Properties()
        if (implementation) {
            result << ['notifier.implementation':implementation]
        }

        [growl, snarl, pushbullet, notifySend, notificationCenter, systemTray, notifu, kdialog, anyBar].each {
            result << it.asProperties('notifier')
        }

        result
    }
}
