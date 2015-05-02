package fr.jcgay.gradle.notifier.extension


class Notifu {

    String path

    Properties asProperties(String parentKey) {
        if (path) {
            return [("${parentKey}.notifu.path".toString()): path]
        }
        new Properties()
    }
}
