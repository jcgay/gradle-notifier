package fr.jcgay.gradle.notifier.extension

import spock.lang.Specification

import static java.util.concurrent.TimeUnit.SECONDS
import static org.assertj.core.api.Assertions.assertThat

class ConfigurationTest extends Specification {

    def 'should write configuration as properties'() {
        given:
        def configuration = new Configuration(implementation: 'anybar')
        configuration.growl.host = '192.168.1.30'
        configuration.anyBar.port = 1
        configuration.kdialog.path = 'kkdialog'
        configuration.notificationCenter.activate = 'iTerm2'
        configuration.notifu.path = 'notifu64'
        configuration.notifySend.timeout = 2
        configuration.pushbullet.apikey = 'abcd'
        configuration.snarl.port = 3
        configuration.systemTray.wait = 4
        configuration.toaster.path = 'toaster'
        configuration.notify.darkstyle = true
        configuration.burnttoast.sound = 'Reminder'
        configuration.slack.token = 'secret.token'

        when:
        def result = configuration.asProperties()

        then:
        assertThat(result)
            .containsEntry('notifier.implementation', 'anybar')
            .containsEntry("notifier.growl.host", '192.168.1.30')
            .containsEntry('notifier.anybar.port', 1)
            .containsEntry('notifier.kdialog.path', 'kkdialog')
            .containsEntry('notifier.notification-center.activate', 'iTerm2')
            .containsEntry('notifier.notifu.path', 'notifu64')
            .containsEntry('notifier.notify-send.timeout', 2L)
            .containsEntry('notifier.pushbullet.apikey', 'abcd')
            .containsEntry('notifier.snarl.port', 3)
            .containsEntry('notifier.system-tray.wait', 4)
            .containsEntry('notifier.toaster.bin', 'toaster')
            .containsEntry('notifier.notify.darkstyle', true)
            .containsEntry('notifier.burnttoast.sound', 'Reminder')
            .containsEntry('notifier.slack.token', 'secret.token')

        result.keySet().findAll { it.endsWith('.class') }.isEmpty()
    }

    def 'should not fail when configuration is not set'() {
        when:
        def result = new Configuration().asProperties()

        then:
        result != null
    }

    def 'should set threshold'() {
        given:
        def configuration = new Configuration()

        when:
        configuration.threshold {
            time = 3
            unit = SECONDS
        }

        then:
        configuration.threshold == new TimeThreshold(time: 3, unit: SECONDS)
    }
}
