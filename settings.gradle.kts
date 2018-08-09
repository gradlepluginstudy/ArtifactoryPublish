pluginManagement {
    repositories {
        gradlePluginPortal()
    }
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "com.novoda.bintray-release") {
                useModule("com.novoda:bintray-release:${requested.version}")
            }
            if (requested.id.id == "org.junit.platform.gradle.plugin") {
                useModule("org.junit.platform:junit-platform-gradle-plugin:$requested.version")
            }
        }
    }
}

rootProject.name = "artifactorypublish"
