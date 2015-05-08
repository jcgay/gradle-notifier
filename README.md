# Gradle notifier

Desktop notifications for [Gradle](http://gradle.org).  
A notification is sent when a build ends indicating if the build has failed or succeeded.

## Installation

The plugin can be configured in an [initialization script](http://gradle.org/docs/current/userguide/init_scripts.html) or in the [build script](https://gradle.org/docs/current/userguide/plugins.html).

### Initialization script

For example, create (or edit) `$HOME/.gradle/init.gradle` file:

```
initscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath group: 'fr.jcgay', name: 'gradle-notifier', version: '0.1'
    }
}

rootProject {
    apply plugin: fr.jcgay.gradle.notifier.GradleNotifierPlugin
}
```

### Build script

In `build.gradle`, add:

```
buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath group: 'fr.jcgay', name: 'gradle-notifier', version: '0.1'
    }
}

apply plugin: 'fr.jcgay.gradle-notifier'
```

## Configuration

The plugin is based on [send-notification](https://github.com/jcgay/send-notification). Available notifiers and customization are described at this [wiki](https://github.com/jcgay/send-notification/wiki).  

You can rely on a property file located at `$HOME/.send-notification` to configure your environment or use gradle to pass properties to the plugin.

```
notifier {
    implementation = 'notificationcenter'
    growl {
        port = 23053
        host = 'localhost'
        password = 'azerty123'
    }
    snarl {
        host = 'localhost'
        port = 9887
    }
    pushbullet {
    	apikey = 'key123'
    	device = 'abcdef'
    }
    notifysend {
        timeout = 7
        path = 'notify-send'
    }
    notificationcenter {
    	path = 'terminal-notifier'
    	activate = 'com.apple.Terminal'
    	sound = 'default'
    }
    systemtray {
    	wait = 2
    }
    notifu {
    	path = 'notifu64'
    }
    kdialog {
    	path = 'kdialog'
    }
    anybar {
    	host = 'localhost'
    	port = 1738
    }
}
```

All parameters are configured by default, if you're fine with it, no need to write them down.  
`Growl` is chosen for `OS X` and `Windows`, `notify-send` for `Linux`. 