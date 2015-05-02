package fr.jcgay.gradle.notifier.extension

class AnyBar {

    String host
    Integer port

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.anybar")
    }

}
