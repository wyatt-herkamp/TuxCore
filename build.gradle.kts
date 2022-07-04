/*
 * This file was generated by the Gradle "init" task.
 */

plugins {
    id("java")
    id("maven-publish")
    id("com.github.johnrengelman.shadow") version "7.1.2"
    `java-library`
    `maven-publish`
    signing
}
java {
    withJavadocJar()
    withSourcesJar()
    targetCompatibility = org.gradle.api.JavaVersion.VERSION_17

}
repositories {
    mavenLocal()
    maven("https://oss.sonatype.org/content/groups/public/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
    maven("https://repo.kingtux.me/storages/maven/kingtux-repo")
    maven("https://repo.kingtux.me/storages/maven/tuxjsql")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven {
        url = uri("https://repo.purpurmc.org/snapshots")
    }
    maven("https://repo.maven.apache.org/maven2")
    mavenCentral()
    maven("https://m2.dv8tion.net/releases");


}

dependencies {
    implementation("me.kingtux:tuxorm:1.5-SNAPSHOT")
    implementation("net.dv8tion:JDA:4.4.0_352")
    implementation("dev.nitrocommand:core:1.0-SNAPSHOT")
    implementation("dev.nitrocommand:jda4:1.0-SNAPSHOT")
    implementation("dev.nitrocommand:bukkit:1.0-SNAPSHOT")
    implementation("me.kingtux:tuxjsql:2.2.0-SNAPSHOT")
    implementation("me.kingtux.tuxjsql:sqlite:2.2.0-SNAPSHOT")
    implementation("com.google.guava:guava:31.0.1-jre")// https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation(group = "info.ronjenkins", name = "slf4bukkit", version = "1.1.0")

    implementation(group = "me.kingtux", name = "enumconfig", version = "1.0")
    implementation("org.purpurmc.purpur:purpur-api:1.18.1-R0.1-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.2")
}

group = "me.kingtux"
version = "1.0-SNAPSHOT"
description = "TuxCore"
val artifactName = "tuxcore"
if (hasProperty("buildNumber")) {
    version = "1.0-" + properties.get("buildNumber") + "-SNAPSHOT"
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifactId = artifactName
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }
            pom {
                name.set(artifactName)
            }
        }
    }
    repositories {
        maven {

            val releasesRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            val snapshotsRepoUrl = uri("https://repo.kingtux.me/storages/maven/kingtux-repo")
            url = if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl
            credentials(PasswordCredentials::class)

        }
        mavenLocal()
    }
}
tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}
tasks {
    "jar"{
        dependsOn(project.tasks.getByPath("shadowJar"))
    }
}
tasks.processResources {
    expand("version" to project.version)
}