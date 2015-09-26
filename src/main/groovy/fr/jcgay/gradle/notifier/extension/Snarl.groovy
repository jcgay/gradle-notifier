package fr.jcgay.gradle.notifier.extension

class Snarl {

    String host
    Integer port
    String appPassword

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.snarl")
    }
}
