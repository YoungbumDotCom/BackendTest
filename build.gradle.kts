plugins {
    kotlin("jvm") version "1.9.22"
    id("org.springframework.boot") version "3.2.1"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jetbrains.dokka") version "1.9.10"
    kotlin("plugin.serialization") version "1.9.22"
    application
}

group = "com.suyeon"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.2.1")
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")
    implementation("commons-codec:commons-codec:1.15")
    implementation("org.springframework.security:spring-security-crypto:5.7.1")
    // https://mvnrepository.com/artifact/mysql/mysql-connector-java
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_17
}
tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "17"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "17"
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