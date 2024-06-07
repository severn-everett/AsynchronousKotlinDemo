import org.gradle.api.tasks.compile.JavaCompile

plugins {
    kotlin("plugin.allopen") version "2.0.0"
    id("org.jetbrains.kotlinx.benchmark") version "0.4.7"
}

group = "com.severett.akd"
version = "1.0-SNAPSHOT"

apply(plugin = "org.jetbrains.kotlinx.benchmark")

allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

repositories {
    mavenCentral()
}

benchmark {
    targets {
        register("test")
    }
}

tasks {
    val enablePreviewArg = "--enable-preview"
    withType<JavaCompile> {
        options.compilerArgs.add(enablePreviewArg)
    }
    withType<JavaExec> {
        jvmArgs(enablePreviewArg)
    }
}

dependencies {
    val coroutinesVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime-jvm:0.4.7")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
