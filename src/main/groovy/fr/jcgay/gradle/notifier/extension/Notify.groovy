package fr.jcgay.gradle.notifier.extension


class Notify {

    String position
    boolean darkstyle

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.notify")
    }
}
