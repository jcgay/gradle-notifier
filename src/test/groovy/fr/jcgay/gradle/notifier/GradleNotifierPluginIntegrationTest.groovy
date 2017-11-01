package fr.jcgay.gradle.notifier
import nebula.test.IntegrationSpec
import org.gradle.api.logging.LogLevel
import spock.lang.Unroll

class GradleNotifierPluginIntegrationTest extends IntegrationSpec {

    static def versions = ['4.2.1', '4.3']

    void setup() {
        logLevel = LogLevel.DEBUG
    }

    @Unroll
    def "should send notification when build ends with Gradle #version"() {
        given:
        gradleVersion = version

        and:
        buildFile << '''
            apply plugin: 'fr.jcgay.gradle-notifier'

            notifier {
                implementation = 'unknown'
            }
        '''.stripIndent()

        when:
        def result = runTasksSuccessfully('tasks')

        then:
        result.standardOutput.contains('Sending notification:')

        where:
        version << versions
    }

    @Unroll
    def "should not send notification when build is using continuous feature with Gradle #version"() {
        given:
        gradleVersion = version

        and:
        buildFile << '''
            apply plugin: 'fr.jcgay.gradle-notifier'

            notifier {
                implementation = 'unknown'
            }
        '''.stripIndent()

        when:
        def result = runTasksSuccessfully('tasks', '--continuous')

        then:
        !result.standardOutput.contains('Sending notification:')

        where:
        version << versions
    }

    @Unroll
    def "should send notification when build is using continuous feature with parameter and Gradle #version"() {
        given:
        gradleVersion = version

        and:
        buildFile << '''
            apply plugin: 'fr.jcgay.gradle-notifier'

            notifier {
                implementation = 'unknown'
                continuousNotify = true
            }
        '''.stripIndent()

        when:
        def result = runTasksSuccessfully('tasks', '--continuous')

        then:
        result.standardOutput.contains('Sending notification:')

        where:
        version << versions
    }
}
