package fr.jcgay.gradle.notifier

import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification
import spock.lang.Unroll

import static org.gradle.testkit.runner.TaskOutcome.*

class GradleNotifierPluginIntegrationTest extends Specification {

    static def versions = ['4.2.1', '4.3', '4.5.1', '4.10.2', '5.3.1', '5.6.3', '6.8.3']

    @Rule
    TemporaryFolder testProjectDir = new TemporaryFolder()

    File settingsFile
    File buildFile

    def setup() {
        settingsFile = testProjectDir.newFile('settings.gradle')
        buildFile = testProjectDir.newFile('build.gradle')
    }

    @Unroll
    def "should send notification when build ends with Gradle #version"() {
        given:
        settingsFile << "rootProject.name = 'test-$version'"
        buildFile << """
            plugins {
                id 'fr.jcgay.gradle-notifier'
            }

            notifier {
                implementation = 'unknown'
            }
        """.stripIndent()

        when:
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments("tasks", "--debug")
            .withGradleVersion(version)
            .withPluginClasspath()
            .build()

        then:
        result.output.contains('Sending notification:')
        result.task(":tasks").outcome == SUCCESS

        where:
        version << versions
    }

    @Unroll
    def "should not send notification when build is using continuous feature with Gradle #version"() {
        given:
        settingsFile << "rootProject.name = 'test-continuous-$version'"
        buildFile << '''
            plugins {
                id 'fr.jcgay.gradle-notifier'
            }

            notifier {
                implementation = 'unknown'
            }
        '''.stripIndent()

        when:
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments("tasks", '--continuous', "--debug")
            .withGradleVersion(version)
            .withPluginClasspath()
            .build()

        then:
        !result.output.contains('Sending notification:')
        result.task(":tasks").outcome == SUCCESS

        where:
        version << versions
    }

    @Unroll
    def "should not send notification when building buildSrc with Gradle #version"() {
        given:
        buildFile << '''
            plugins {
                id 'fr.jcgay.gradle-notifier'
            }

            notifier {
                implementation = 'unknown'
            }
        '''.stripIndent()

        settingsFile << '''
            rootProject.name = 'buildSrc'
        '''.stripIndent()

        when:
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments("tasks", "--debug")
            .withGradleVersion(version)
            .withPluginClasspath()
            .build()

        then:
        !result.output.contains('Sending notification:')
        result.task(":tasks").outcome == SUCCESS

        where:
        version << versions
    }

    @Unroll
    def "should send notification when build is using continuous feature with parameter and Gradle #version"() {
        given:
        buildFile << '''
            plugins {
                id 'fr.jcgay.gradle-notifier'
            }

            notifier {
                implementation = 'unknown'
                continuousNotify = true
            }
        '''.stripIndent()

        when:
        def result = GradleRunner.create()
            .withProjectDir(testProjectDir.root)
            .withArguments("tasks", '--continuous', "--debug")
            .withGradleVersion(version)
            .withPluginClasspath()
            .build()

        then:
        result.output.contains('Sending notification:')
        result.task(":tasks").outcome == SUCCESS

        where:
        version << versions
    }
}
