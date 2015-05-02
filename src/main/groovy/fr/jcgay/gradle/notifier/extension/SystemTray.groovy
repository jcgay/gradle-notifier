package fr.jcgay.gradle.notifier.extension


class SystemTray {

    Integer wait

    Properties asProperties(String parentKey) {
        if (wait) {
            return [("${parentKey}.system-tray.wait".toString()): wait]
        }
        new Properties()
    }
}
