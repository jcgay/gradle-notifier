
import groovy.lang.Closure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import pl.allegro.tech.build.axion.release.domain.properties.TagProperties
import pl.allegro.tech.build.axion.release.domain.scm.ScmPosition

plugins {
    id("com.github.kt3k.coveralls") version "2.10.2"
    id("com.bmuschko.nexus") version "2.3.1"
    id("pl.allegro.tech.build.axion-release") version "1.10.2"
    id("com.github.ben-manes.versions") version "0.38.0"
    id("io.codearte.nexus-staging") version "0.11.0"
    id("java-gradle-plugin")
    id("groovy")
    id("jacoco")
    id("com.gradle.plugin-publish") version "0.21.0"
    kotlin("jvm") version "1.4.31"
}

group = "fr.jcgay"

scmVersion {
    with(tag) {
        prefix = "v"
        versionSeparator = ""
        deserialize = KotlinClosure3<TagProperties, ScmPosition, String, String>({ _, _, tagName -> if (tagName.count { '.' == it } == 1) "$tagName.0".removePrefix("v") else tagName.removePrefix("v") })
    }
}

project.version = scmVersion.version

val javaVersion = JavaLanguageVersion.of(JavaVersion.VERSION_1_8.majorVersion)
java {
    toolchain {
        languageVersion.set(javaVersion)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(gradleApi())
    implementation(kotlin("stdlib-jdk8"))

    implementation(group = "fr.jcgay.send-notification", name = "send-notification", version = "0.16.0")

    testImplementation(localGroovy())
    testImplementation(gradleTestKit())
    testImplementation(group = "org.assertj", name = "assertj-core", version = "3.19.0")
    testImplementation(group = "org.spockframework", name = "spock-core", version = "1.3-groovy-2.5")
    testImplementation(group = "com.github.stefanbirkner", name = "system-rules", version = "1.19.0")
    testRuntimeOnly(group = "cglib", name = "cglib-nodep", version = "3.3.0")
}

tasks {
    named("release") {
        dependsOn("build")
    }

    val uploadArchives = named("uploadArchives") {
        doFirst {
            if (!project.properties.keys.containsAll(listOf("nexusUsername", "nexusPassword"))) {
                throw StopExecutionException("Nexus authentication is not correctly defined, set 'nexusUsername' and 'nexusPassword' properties")
            }
        }
    }

    publishPlugins {
        doFirst {
            if (!project.properties.keys.containsAll(listOf("gradle.publish.key", "gradle.publish.secret"))) {
                throw StopExecutionException("Gradle plugin portal credentials not correctly defined, set 'gradle.publish.secret' and 'gradle.publish.secret' properties")
            }
        }
        mustRunAfter(uploadArchives)
    }

    register("publish") {
        dependsOn(uploadArchives, closeAndReleaseRepository, publishPlugins)
    }

    closeAndReleaseRepository {
        mustRunAfter(uploadArchives)
    }

    jacocoTestReport {
        reports {
            xml.isEnabled = true
            html.isEnabled = true
        }
    }

    compileTestGroovy {
        dependsOn(compileTestKotlin)
        classpath += files(compileTestKotlin.get().destinationDir)
    }
}

val compiler = javaToolchains.compilerFor {
    languageVersion.set(javaVersion)
}
tasks.withType<KotlinJvmCompile>().configureEach {
    kotlinOptions.jdkHome = compiler.get().metadata.installationPath.asFile.absolutePath
}

gradlePlugin {
    plugins {
        create("gradleNotifierPlugin") {
            id = "fr.jcgay.gradle-notifier"
            displayName = "Gradle Notifier"
            implementationClass = "fr.jcgay.gradle.notifier.GradleNotifierPlugin"
        }
    }
}

pluginBundle {
    website = "https://github.com/jcgay/gradle-notifier"
    vcsUrl = "https://github.com/jcgay/gradle-notifier"
    description = "A plugin to have desktop notification when a build ends"
    tags = listOf("notification", "desktop", "growl", "snarl", "notification center", "anybar", "notify-send", "pushbullet", "toaster")
}

val modifyPom : Closure<MavenPom> by ext
modifyPom(closureOf<MavenPom> {
    project {
        withGroovyBuilder {
            "name"("gradle-notifier")
            "packaging"("jar")
            "description"("A plugin to have desktop notification when a build ends")
            "url"("https://github.com/jcgay/gradle-notifier")

            "scm" {
                "connection"("scm:git:git://github.com/jcgay/gradle-notifier.git")
                "developerConnection"("scm:git:git@github.com:jcgay/gradle-notifier.git")
                "url"("https://github.com/jcgay/gradle-notifier.git")
            }

            "licenses" {
                "license" {
                    "name"("The MIT License")
                    "url"("http://opensource.org/licenses/MIT")
                }
            }

            "developers" {
                "developer" {
                    "id"("jcgay")
                    "name"("Jean-Christophe Gay")
                    "email"("contact@jeanchristophegay.com")
                }
            }
        }
    }
})
