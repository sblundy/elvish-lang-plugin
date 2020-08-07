import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.PublishTask

plugins {
    id("org.jetbrains.intellij") version "0.4.21"
    java
    kotlin("jvm") version "1.3.72"
    id("org.jetbrains.grammarkit") version "2020.2.1"
}

group = "com.github.sblundy"
version = "1.0.3"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testCompile("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    get("main").java {
        srcDir("src/gen")
    }
}

tasks.getByName<PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("")
    sinceBuild("201")
    untilBuild("202.*")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.getByName<PublishTask>("publishPlugin") {
    if (project.hasProperty("publishToken")) {
        token(project.ext["publishToken"])
    }
}

val generateLexer = task<GenerateLexer>("generateLexer") {
    source = "src/main/grammars/Elvish.flex"
    targetDir = "src/gen/com/github/sblundy/elvish/lang"
    targetClass = "_ElvishLexer"
    purgeOldFiles = true
}

val generateParser = task<GenerateParser>("generateParser") {
    source = "src/main/grammars/Elvish.bnf"
    targetRoot = "src/gen"
    pathToParser = "/com/github/sblundy/elvish/lang/ElvishParser.java"
    pathToPsiRoot = "/com/github/sblundy/elvish/psi"
    purgeOldFiles = true
}

tasks.withType<KotlinCompile> {
    dependsOn(
        generateLexer, generateParser
    )
}

tasks.getByName("buildSearchableOptions") {
    enabled = false
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}