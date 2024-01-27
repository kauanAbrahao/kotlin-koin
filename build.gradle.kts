plugins {
    kotlin("jvm") version "1.6.10"
    application
    java
}

group = "me.kauan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("MainKt")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("stdlib"))
    implementation("io.insert-koin:koin-core:3.2.0")
}

sourceSets {
    main {
        java {
            srcDirs(
                "src/main/kotlin/"
            )
        }
    }
}

tasks.test {
    environment("environment", "unit")
    useJUnit()
}

tasks.register("runDev", JavaExec::class) {
    classpath = sourceSets.main.get().runtimeClasspath
    main = "MainKt"
    environment("environment", "dev")
}

tasks.register("runPrd", JavaExec::class) {
    classpath = sourceSets.main.get().runtimeClasspath
    main = "MainKt"
    environment("environment", "prd")
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "MainKt"
        )
    }
    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from ({
        configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) }
    })
}

