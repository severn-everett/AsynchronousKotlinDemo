import org.jetbrains.kotlin.gradle.dsl.JvmTarget.Companion.fromTarget;
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile;

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:0.20.0")
    }
}

plugins {
    kotlin("jvm") version "1.8.10"
}

group = "com.severett.akd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

val jvmVersion = "19";
kotlin {
    jvmToolchain(jvmVersion.toInt())
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    tasks.withType<KotlinCompile> {
        compilerOptions {
            jvmTarget.set(fromTarget(jvmVersion))
        }
    }
}
