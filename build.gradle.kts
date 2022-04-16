import org.jetbrains.intellij.tasks.RunIdeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

fun properties(key: String) = project.findProperty(key).toString()

plugins {
    id("org.jetbrains.intellij") version "1.4.0"
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.6.10"
    id("org.jetbrains.qodana") version "0.1.13"
    kotlin("plugin.serialization") version "1.6.10"
    id("org.jetbrains.grammarkit") version "2021.2.2"
}

group = properties("pluginGroup")
version = properties("pluginVersion")

// Configure project's dependencies
repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-jvm:1.3.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.2")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.2")
    testImplementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7") // https://intellij-support.jetbrains.com/hc/en-us/community/posts/360006967739-Simple-test-case-runs-container-resolve-issues
}

// Configure Gradle IntelliJ Plugin - read more: https://github.com/JetBrains/gradle-intellij-plugin
intellij {
    pluginName.set(properties("pluginName"))
    version.set(properties("platformVersion"))
    type.set(properties("platformType"))

    // Plugin Dependencies. Uses `platformPlugins` property from the gradle.properties file.
    plugins.set(properties("platformPlugins").split(',').map(String::trim).filter(String::isNotEmpty))
}

// Configure Gradle Qodana Plugin - read more: https://github.com/JetBrains/gradle-qodana-plugin
qodana {
    cachePath.set(projectDir.resolve(".qodana").canonicalPath)
    reportPath.set(projectDir.resolve("build/reports/inspections").canonicalPath)
    saveReport.set(true)
    showReport.set(System.getenv("QODANA_SHOW_REPORT")?.toBoolean() ?: false)
}

sourceSets {
    get("main").java {
        srcDir("src/gen")
    }
}

tasks {
    patchPluginXml {
        version.set(properties("pluginVersion"))
        sinceBuild.set(properties("pluginSinceBuild"))
        untilBuild.set(properties("pluginUntilBuild"))
    }
    // Set the JVM compatibility versions
    properties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it
        }
        withType<KotlinCompile> {
            kotlinOptions.jvmTarget = it
        }
    }

    publishPlugin {
        if (version.toString().endsWith("SNAPSHOT")) {
            enabled = false
        } else if (version.toString().contains("BETA", true)) {
            channels.add("beta")
        }
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    generateLexer {
        source.set("src/main/grammars/Elvish.flex")
        targetDir.set("src/gen/com/github/sblundy/elvish/lang")
        targetClass.set("_ElvishLexer")
        purgeOldFiles.set(true)
    }

    generateParser {
        source.set("src/main/grammars/Elvish.bnf")
        targetRoot.set("src/gen")
        pathToParser.set("/com/github/sblundy/elvish/lang/ElvishParser.java")
        pathToPsiRoot.set("/com/github/sblundy/elvish/psi")
        purgeOldFiles.set(true)
    }

    wrapper {
        gradleVersion = properties("gradleVersion")
    }

    // Configure UI tests plugin
    // Read more: https://github.com/JetBrains/intellij-ui-test-robot
    runIdeForUiTests {
        systemProperty("robot-server.port", "8082")
        systemProperty("ide.mac.message.dialogs.as.sheets", "false")
        systemProperty("jb.privacy.policy.text", "<!--999.999-->")
        systemProperty("jb.consents.confirmation.enabled", "false")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    withType<KotlinCompile> {
        dependsOn("generateLexer", "generateParser")
    }

    buildSearchableOptions {
        enabled = false
    }

    prepareSandbox {
        from("src/main/resources/versions") {
            into("$pluginName/versions")
        }
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    prepareTestingSandbox {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }

    withType<Test> {
        useJUnitPlatform()
        testLogging {
            events("passed", "skipped", "failed")
        }
    }

    withType<RunIdeTask> {
        autoReloadPlugins.set(true)
        systemProperty("ide.plugins.snapshot.on.unload.fail", "true")
    }
}
