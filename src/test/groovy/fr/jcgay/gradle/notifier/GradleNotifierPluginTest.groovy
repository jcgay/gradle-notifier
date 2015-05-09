package fr.jcgay.gradle.notifier

import fr.jcgay.gradle.notifier.extension.Configuration
import nebula.test.PluginProjectSpec
import org.junit.Rule
import org.junit.contrib.java.lang.system.ProvideSystemProperty

class GradleNotifierPluginTest extends PluginProjectSpec {

    @Rule
    ProvideSystemProperty system = new ProvideSystemProperty('notifier.anybar.port', '9999')
        .and('notifier.implementation', 'anybar')

    @Override
    String getPluginName() {
        'fr.jcgay.gradle-notifier'
    }

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
}
