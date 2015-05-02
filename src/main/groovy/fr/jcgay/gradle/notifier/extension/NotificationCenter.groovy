package fr.jcgay.gradle.notifier.extension


class NotificationCenter {

    String path
    String activate
    String sound

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.notification-center")
    }
}
