package fr.jcgay.gradle.notifier.extension

import java.util.Properties


interface NotifierConfiguration {

    fun asProperties(): Properties
}
