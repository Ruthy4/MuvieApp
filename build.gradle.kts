// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript{
    repositories{
        google()
    }
    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:${Versions.spotless_version}")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}")

    }
}

plugins {
    id ("com.android.application") version "7.4.0" apply false
    id ("com.android.library") version "7.4.0" apply false
    id ("org.jetbrains.kotlin.android") version "1.7.21" apply false
    id("com.diffplug.spotless") version "6.12.0"
}


subprojects {
    apply(plugin = "com.diffplug.spotless")

    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")

            ktlint(Versions.ktlintVersion).userData(mapOf("disabled_rules" to "no-wildcard-imports"))
        }
    }
}




