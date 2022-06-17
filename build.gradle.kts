plugins {
    kotlin("jvm") version "1.6.21"
    id("org.springframework.boot") version "2.7.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("org.jetbrains.dokka") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
    application
}

group = "com.suyeon"
version = "1.0"

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")
    implementation("org.xerial:sqlite-jdbc:3.36.0.3")
    implementation("commons-codec:commons-codec:1.15")
    implementation("com.beust:klaxon:5.6")
    implementation("org.springframework.security:spring-security-crypto:5.7.1")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.okio:okio:3.1.0")
    implementation("org.litote.kmongo:kmongo:4.6.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "11"
    }
    dokkaHtml.configure {
        outputDirectory.set(buildDir.resolve("docs"))
        /*dokkaSourceSets {
            configureEach {
                suppress.set(true)
            }
        }*/
    }
}
task("stage") {
    dependsOn.add("build")
}

springBoot {
    mainClass.set("emiyaj.ApplicationKt")
}

configurations.forEach {
    if (it.name.contains("productionRuntimeClasspath")) {
        it.attributes.attribute(Usage.USAGE_ATTRIBUTE, objects.named(Usage::class.java, "java-runtime"))
    }
}