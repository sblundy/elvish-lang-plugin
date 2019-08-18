import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.intellij") version "0.4.9"
    java
    kotlin("jvm") version "1.3.41"
    id("org.jetbrains.grammarkit") version "2019.2"
}

group = "com.github.sblundy"
version = "1.0-beta-5"

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
    version = "2019.2"
}
configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

sourceSets {
    get("main").java {
        srcDir("src/gen")
    }
}

tasks.getByName<org.jetbrains.intellij.tasks.PatchPluginXmlTask>("patchPluginXml") {
    changeNotes("""
      Add change notes here.<br>
      <em>most HTML tags may be used</em>""")
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<org.jetbrains.intellij.tasks.PatchPluginXmlTask> {
    changeNotes("")
}

tasks.getByName<org.jetbrains.intellij.tasks.PublishTask>("publishPlugin") {
    channels("beta")
    if (project.hasProperty("publishToken")) {
        token(project.ext["publishToken"])
    }
}

val generateLexer = task<org.jetbrains.grammarkit.tasks.GenerateLexer>("generateLexer") {
    source = "src/main/grammars/Elvish.flex"
    targetDir = "src/gen/com/github/sblundy/elvish/lang"
    targetClass = "_ElvishLexer"
    purgeOldFiles = true
}

val generateParser = task<org.jetbrains.grammarkit.tasks.GenerateParser>("generateParser") {
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