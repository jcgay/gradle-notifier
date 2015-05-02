package fr.jcgay.gradle.notifier.extension

class Pushbullet {

    String apikey
    String device

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.pushbullet")
    }

}
