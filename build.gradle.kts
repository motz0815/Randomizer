import org.apache.tools.ant.filters.ReplaceTokens

plugins {
    java
    application
    id("io.freefair.lombok") version "6.4.1"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("xyz.jpenilla.run-paper") version "1.0.6"
}

group = "xyz.motz"
version = "3.1.0-SNAPSHOT"
val apiVersion = "1.13"

application {
    mainClass.set("xyz.motz.randomizer.main.Randomizer")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://repo.codemc.org/repository/maven-public")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://papermc.io/repo/repository/maven-public")
}

dependencies {
    // Minecraft specific
    compileOnly("org.spigotmc:spigot-api:1.13.2-R0.1-SNAPSHOT") // SPIGOT API

    // not minecraft specific
    compileOnly("org.projectlombok:lombok:1.18.22") // Lombok
    annotationProcessor("org.projectlombok:lombok:1.18.22") // Lombok
}

tasks.processResources {
    outputs.upToDateWhen { false }
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    from("src/main/resources") {
        filter(ReplaceTokens::class, "tokens" to mapOf("version" to version, "apiVersion" to apiVersion))
    }
}

tasks.compileJava {
    options.encoding = "UTF-8"
}

tasks.runServer {
    minecraftVersion("1.18.2")
}