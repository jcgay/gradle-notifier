package fr.jcgay.gradle.notifier.extension


class Toaster {

    String path

    Properties asProperties(String parentKey) {
        if (path) {
            return [("${parentKey}.toaster.bin".toString()): path]
        }
        new Properties()
    }
}
