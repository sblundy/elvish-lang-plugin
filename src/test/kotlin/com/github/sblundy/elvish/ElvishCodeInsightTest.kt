package com.github.sblundy.elvish

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.testFramework.*
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import org.junit.Assert
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.regex.Pattern

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("\$CONTENT_ROOT/src/test/resources/")
class ElvishCodeInsightTest {
    private var myFullDataPath: String = "com/github/sblundy/elvish/"

    private lateinit var myFixture: CodeInsightTestFixture

    private lateinit var myModule: Module

    private lateinit var myProject: Project

    @BeforeAll
    fun setup() {
        val factory = IdeaTestFixtureFactory.getFixtureFactory()
        val descriptor = LightProjectDescriptor()
        val fixtureBuilder = factory.createLightFixtureBuilder(descriptor)
        val fixture = fixtureBuilder.fixture
        myFixture = IdeaTestFixtureFactory.getFixtureFactory()
            .createCodeInsightFixture(fixture, LightTempDirTestFixtureImpl(true))
        myFixture.testDataPath = "src/test/resources/"
        myFixture.setCaresAboutInjection(false)

        myFixture.setUp()

        myProject = myFixture.project
        myModule = myFixture.module
    }

    @AfterAll
    fun teardown() {
        myFixture.tearDown()
    }

    @Suppress("unused")
    fun completionFileLister(): List<String> =
        FileUtil.findFilesByMask(Pattern.compile("ElvishCodeInsightTest-completion-.*\\.elv"), File(myFixture.testDataPath, myFullDataPath))
            .map { it.nameWithoutExtension.substring(33) }
            .filterNot { it == "for-invalid" }.sorted()

    @ParameterizedTest
    @MethodSource("completionFileLister")
    fun testCompletion(basename: String) {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-$basename.elv", myFullDataPath + "yy.elv", myFullDataPath + "yy/xx.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()
            UsefulTestCase.assertSameLinesWithFile(myFixture.testDataPath + myFullDataPath + "ElvishCodeInsightTest-completion-$basename.txt", lookupStrings.sorted().joinToString("\n"))
        }
    }
}
