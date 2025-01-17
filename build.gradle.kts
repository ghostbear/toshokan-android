
// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  extra.apply {
    set("compose_version", "1.2.0-beta02")
    set("kotlin_version", "1.6.21")
    set("coroutines_version", "1.6.1")
    set("hilt_version", "2.42")
    set("sqldelight_version", "1.5.3")
  }

  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath("com.android.tools.build:gradle:7.2.0")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${rootProject.extra["kotlin_version"]}")
    classpath("com.google.dagger:hilt-android-gradle-plugin:${rootProject.extra["hilt_version"]}")
    classpath("com.squareup.sqldelight:gradle-plugin:${rootProject.extra["sqldelight_version"]}")
  }
}

tasks.register<Delete>("clean") {
  delete(rootProject.buildDir)
}
