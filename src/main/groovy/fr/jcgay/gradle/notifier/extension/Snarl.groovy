package fr.jcgay.gradle.notifier.extension

class Snarl {

    String host
    Integer port

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.snarl")
    }
}
