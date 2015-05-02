package fr.jcgay.gradle.notifier.extension


class Kdialog {

    String path

    Properties asProperties(String parentKey) {
        if (path) {
            return [("${parentKey}.kdialog.path".toString()): path]
        }
        new Properties()
    }
}
