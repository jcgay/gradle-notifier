package fr.jcgay.gradle.notifier.extension


class NotifySend {

    String path
    Long timeout

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.notify-send")
    }
}
