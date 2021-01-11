import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.PrepareSandboxTask
import org.jetbrains.intellij.tasks.PublishTask
import org.jetbrains.intellij.tasks.RunIdeTask

plugins {
    id("org.jetbrains.intellij") version "0.6.4"
    java
    kotlin("jvm") version "1.4.20"
    kotlin("plugin.serialization") version "1.4.10"
    id("org.jetbrains.grammarkit") version "2020.2.1"
}

group = "com.github.sblundy"
version = "1.2.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.0.0-RC2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.0.0-RC2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.3.2")
}

// See https://github.com/JetBrains/gradle-intellij-plugin/
intellij {
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}

sourceSets {
    get("main").java {
        srcDir("src/gen")
    }
}

tasks.getByName<PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("")
    sinceBuild("201")
    untilBuild("203.*")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.getByName<PublishTask>("publishPlugin") {
    if (version.toString().endsWith("SNAPSHOT")) {
        enabled = false
    } else if (version.toString().contains("BETA", true)) {
        channels("beta")
    }
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
    doFirst {
        val f = project.files(sourceSets["main"].runtimeClasspath)
        classpath += f
    }
    doLast {
        println(classpath.files)
    }
}

tasks.withType<KotlinCompile> {
    dependsOn(
        generateLexer, generateParser
    )
}

tasks.getByName("buildSearchableOptions") {
    enabled = false
}

tasks.getByName<PrepareSandboxTask>("prepareSandbox") {
    from("src/main/resources/versions") {
        into("$pluginName/versions")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<RunIdeTask> {
    autoReloadPlugins = true
    systemProperty("ide.plugins.snapshot.on.unload.fail", "true")
}