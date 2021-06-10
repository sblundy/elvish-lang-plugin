import org.jetbrains.grammarkit.tasks.GenerateLexer
import org.jetbrains.grammarkit.tasks.GenerateParser
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.intellij.tasks.PatchPluginXmlTask
import org.jetbrains.intellij.tasks.PrepareSandboxTask
import org.jetbrains.intellij.tasks.PublishPluginTask
import org.jetbrains.intellij.tasks.RunIdeTask

plugins {
    id("org.jetbrains.intellij") version "1.0"
    java
    kotlin("jvm") version "1.5.0"
    kotlin("plugin.serialization") version "1.5.0"
    id("org.jetbrains.grammarkit") version "2021.1.3"
}

group = "com.github.sblundy"
version = "1.3.0-BETA2"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.2.1")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.2.1")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.7.2")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7") // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360006967739-Simple-test-case-runs-container-resolve-issues
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
    changeNotes.set("")
    sinceBuild.set("201")
    untilBuild.set("212.*")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
}

tasks.getByName<PublishPluginTask>("publishPlugin") {
    if (version.toString().endsWith("SNAPSHOT")) {
        enabled = false
    } else if (version.toString().contains("BETA", true)) {
        channels.add("beta")
    }
    if (project.hasProperty("publishToken")) {
        token.set(project.ext["publishToken"] as String)
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
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.getByName<PrepareSandboxTask>("prepareTestingSandbox") {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
}

tasks.withType<Test> {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<RunIdeTask> {
    autoReloadPlugins.set(true)
    systemProperty("ide.plugins.snapshot.on.unload.fail", "true")
}