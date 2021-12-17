import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.ir.backend.js.compile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    id("com.github.johnrengelman.shadow") version "7.0.0"
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.kiara"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    implementation("org.jetbrains.kotlinx.spark:kotlin-spark-api-3.0:1.0.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    compileOnly("org.apache.spark:spark-sql_2.13:3.2.0")

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

application {
    mainClass.set("kiarash.MainKt")
}

tasks.withType<ShadowJar> {
    archiveFileName.set("app.jar")
    exclude("org.apache.spark", "org.scala-lang")
}