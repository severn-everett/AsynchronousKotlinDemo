buildscript {
    repositories {
        mavenCentral()
    }
}

apply(plugin = "kotlinx-atomicfu")

group = "com.severett.akd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val coroutinesVersion: String by project
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")
}
