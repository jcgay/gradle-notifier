package fr.jcgay.gradle.notifier.extension


class Slack {

    String token
    String channel

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.slack")
    }
}
