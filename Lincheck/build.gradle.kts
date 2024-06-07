group = "com.severett.akd"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    val junitVersion: String by project
    testImplementation("org.jctools:jctools-core:4.0.1")
    testImplementation("org.jetbrains.kotlinx:lincheck:2.32")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    jvmArgs = listOf(
        // Arguments that are to be uncommented for LockCoarseningTest
        // "-XX:+UnlockDiagnosticVMOptions",
        // "-XX:+StressLCM",
        // "-XX:+StressGCM",
    )
}
