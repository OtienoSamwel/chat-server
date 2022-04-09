val ktor_version: String by project
val kotlin_version: String by project

plugins {
    application
    kotlin("jvm") version "1.6.10"
}

group = "com.otienosamwel"
version = "0.0.1"
application {
    mainClass.set("com.otienosamwel.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://maven.pkg.jetbrains.space/public/p/ktor/eap")
        name = "ktor-eap"
    }
}

dependencies {
    implementation("ch.qos.logback:logback-classic:1.2.11")
    implementation("io.ktor:ktor-server-call-logging:2.0.0-eap-256")
    implementation("io.ktor:ktor-server-core-jvm:2.0.0-eap-256")
    implementation("io.ktor:ktor-server-websockets-jvm:2.0.0-eap-256")
    implementation("io.ktor:ktor-server-netty-jvm:2.0.0-eap-256")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    testImplementation("io.ktor:ktor-server-tests-jvm:2.0.0-eap-256")
}