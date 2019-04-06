package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.extension.Configuration
import fr.jcgay.notification.Notifier
import fr.jcgay.notification.SendNotification
import fr.jcgay.notification.SendNotificationException
import fr.jcgay.notification.notifier.DoNothingNotifier
import org.junit.Rule
import org.junit.contrib.java.lang.system.ProvideSystemProperty
import spock.lang.Specification

class GradleNotifierPluginTest extends Specification {

    @Rule
    ProvideSystemProperty system = new ProvideSystemProperty('notifier.anybar.port', '9999')
        .and('notifier.implementation', 'anybar')

    def "should merge user configuration and system properties"() {
        given:
        def userConfig = new Configuration()
        userConfig.anyBar.port = 1739

        when:
        def result = GradleNotifierPlugin.mergeProperties(userConfig)

        then:
        result['notifier.implementation'] == 'anybar'
        result['notifier.anybar.port'] == '9999'
    }

    def "should fail silently when notifier initialization fails and return a silent notifier"() {
        given:
        def sendNotification = Mock(SendNotification)
        sendNotification./setApplication|addConfigurationProperties/(*_) >> sendNotification
        sendNotification.initNotifier() >> { throw new SendNotificationException('fail') }

        and:
        def plugin = new GradleNotifierPlugin(sendNotification)

        when:
        def result = plugin.createNotifier(new Configuration())

        then:
        noExceptionThrown()

        and:
        result == DoNothingNotifier.doNothing()
    }

    def "should create notifier"() {
        given:
        def notifier = Stub(Notifier)
        def sendNotification = Mock(SendNotification)
        sendNotification./setApplication|addConfigurationProperties/(*_) >> sendNotification
        sendNotification.initNotifier() >> notifier

        and:
        def plugin = new GradleNotifierPlugin(sendNotification)

        when:
        def result = plugin.createNotifier(new Configuration())

        then:
        result == notifier
    }
}
