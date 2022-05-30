import org.jetbrains.compose.compose
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    id("io.gitlab.arturbosch.detekt") version "1.19.0"
    id("org.jetbrains.compose") version "1.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.junit.jupiter:junit-jupiter:5.8.1")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.20.0")
    testImplementation(platform("org.junit:junit-bom:5.8.2"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(kotlin("test"))
    implementation("com.github.ajalt.clikt:clikt:3.4.2")
    implementation("org.jetbrains.lets-plot:lets-plot-kotlin-jvm:3.2.0")
    implementation("org.jetbrains.lets-plot:lets-plot-common:2.3.0")
    implementation("org.jetbrains.lets-plot:lets-plot-image-export:2.3.0")
    implementation("org.slf4j:slf4j-nop:1.7.36")
    implementation(compose.desktop.currentOs)
    implementation("org.jsoup:jsoup:1.14.3")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}