import org.jetbrains.kotlin.fir.analysis.checkers.declaration.Checks.ValueParametersCount.single
import org.jetbrains.kotlin.ir.backend.js.compile

//import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// ================== USANDO GRADLE.PROPERTIES
//plugins {
//    kotlin("jvm") version "1.6.10"
//    id("net.saliman.properties") version "1.5.2"
//    application
//}
//
//group = "me.kauan"
//version = "1.0-SNAPSHOT"
//
//repositories {
//    mavenCentral()
//}
//
//dependencies {
//    testImplementation(kotlin("test"))
//    implementation(kotlin("stdlib"))
//}
//
//application {
//    mainClass.set("MainKt")
//}
//
//
//tasks.test {
//    useJUnit()
//}
//
//tasks.withType<KotlinCompile>() {
//    kotlinOptions.jvmTarget = "11"
//}
//
//tasks.named<JavaExec>("run") {
//    systemProperty("URL", findProperty("URL") ?: "")
//}


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

