package fr.jcgay.gradle.notifier.extension

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import java.util.concurrent.TimeUnit

import static java.util.concurrent.TimeUnit.MILLISECONDS

@CompileStatic
@EqualsAndHashCode
@ToString
class Timeout {
    Long time
    TimeUnit unit = MILLISECONDS
}
