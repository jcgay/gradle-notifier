package fr.jcgay.gradle.notifier.extension

class Growl {

    String host
    Integer port
    String password

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.growl")
    }
}
