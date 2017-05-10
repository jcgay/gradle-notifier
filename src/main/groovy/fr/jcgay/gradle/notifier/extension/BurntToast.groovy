package fr.jcgay.gradle.notifier.extension


class BurntToast {

    String sound

    Properties asProperties(String parentKey) {
        Configuration.createProperties(properties, "${parentKey}.burnttoast")
    }
}
