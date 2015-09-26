# Gradle notifier

Desktop notifications for [Gradle](http://gradle.org).  
A notification is sent when a build ends indicating if the build has failed or succeeded.

## Installation

The plugin can be configured in an [initialization script](http://gradle.org/docs/current/userguide/init_scripts.html) or in the [build script](https://gradle.org/docs/current/userguide/plugins.html).

It is also deployed on [https://plugins.gradle.org](https://plugins.gradle.org/plugin/fr.jcgay.gradle-notifier).

### Initialization script

For example, create (or edit) `$HOME/.gradle/init.gradle` file:

```
initscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath group: 'fr.jcgay', name: 'gradle-notifier', version: '0.3'
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
        classpath group: 'fr.jcgay', name: 'gradle-notifier', version: '0.3'
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
    threshold {
        time = 10
        unit = java.util.concurrent.TimeUnit.SECONDS
    }
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
    toaster {
        path = 'toast'
    }
}
```

`notifier.threshold` allows to bypass notification when the build ends before the configured threshold.

All parameters are configured by default, if you're fine with it, no need to write them down.  
`gradle-notifier` will try to find an available notifier on your system.

Notifier configuration can also be set using system properties. One can use `-Dnotifier.implementation=anybar` or `-Dnotifier.anybar.port=9999` to override parameters for a specific build execution.

#Notifiers

| Notifier | Screenshot |
|:--------:|-----------------|
| **Growl**, for [Windows](http://www.growlforwindows.com/gfw/) and [OS X](http://growl.info/).    | ![Growl](http://jeanchristophegay.com/images/gradle.notifier.growl.thumbnail.png) |
| **[Snarl](http://snarl.fullphat.net/)**, for Windows | ![Snarl](http://jeanchristophegay.com/images/gradle.notifier.snarl.png) |
| **[terminal-notifier](https://github.com/alloy/terminal-notifier)**, OS X | ![terminal-notifier](http://jeanchristophegay.com/images/gradle.notifier.notificationcenter.thumbnail.png) |
| **notification center** OS X (since Mavericks) | ![notification-center](http://jeanchristophegay.com/images/notifier.simplenc.thumbnail.png) |
| **notify-send** for Linux | ![notify-send](http://jeanchristophegay.com/images/gradle.notifier.notifysend.png) |
| **SystemTray** since Java 6 | ![System Tray](http://jeanchristophegay.com/images/notifier.system.tray_.success.png) |
| **[Pushbullet](https://www.pushbullet.com/)** | ![pushbullet](http://jeanchristophegay.com/images/gradle.notifier.pushbullet.thumbnail.png) |
| **Kdialog** for KDE | ![Kdialog](http://jeanchristophegay.com/images/gradle.notifier.kdialog.png) |
| **[notifu](http://www.paralint.com/projects/notifu/index.html)** for Windows | ![notifu](http://jeanchristophegay.com/images/gradle.notifier.notifu.png) |
| **AnyBar** for [OS X](https://github.com/tonsky/AnyBar) and [Linux](https://github.com/limpbrains/somebar) | ![anybar](http://jeanchristophegay.com/images/gradle.notifier.anybar.thumbnail.png) |
| **[Toaster](https://github.com/nels-o/toaster)** for Windows 8 | ![Toaster](http://jeanchristophegay.com/images/gradle.notifier.toaster.png) |

# Build status
[![Build Status](https://travis-ci.org/jcgay/gradle-notifier.svg?branch=master)](https://travis-ci.org/jcgay/gradle-notifier)
[![Coverage Status](https://coveralls.io/repos/jcgay/gradle-notifier/badge.svg?branch=master)](https://coveralls.io/r/jcgay/gradle-notifier?branch=master)

# Set next version

    ./gradlew markNextVersion -Prelease.nextVersion=2.0.0

# Release

    ./gradlew release && ./gradlew clean publish
