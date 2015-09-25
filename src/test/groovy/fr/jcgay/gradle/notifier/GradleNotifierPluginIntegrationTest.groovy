package fr.jcgay.gradle.notifier
import nebula.test.IntegrationSpec
import org.gradle.api.logging.LogLevel
import spock.lang.Unroll

class GradleNotifierPluginIntegrationTest extends IntegrationSpec {

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
        version << ['2.0', '2.1', '2.2', '2.3', '2.4', '2.5', '2.6', '2.7']
    }
}
