plugins {
    kotlin("jvm") version "2.1.0"
}

group = "com.github.kshekhovtsova.xenialehtmlbuilder"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-html:0.12.0")
}

kotlin {
    jvmToolchain(11)
}