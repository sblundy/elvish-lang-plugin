package com.github.sblundy.elvish.highlighting

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import com.intellij.testFramework.runInEdtAndWait
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.regex.Pattern

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("\$CONTENT_ROOT/src/test/resources/")
class HighlightingTest {
    private var myFullDataPath: String = "com/github/sblundy/elvish/highlighting/"

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
    fun highlightingFileLister(): List<String> = //listOf("compound-expression-with-slash", "assignment-chained-range")
        FileUtil.findFilesByMask(Pattern.compile(".*\\.elv"), File(myFixture.testDataPath, myFullDataPath)).map { it.nameWithoutExtension }
            .filterNot { it == "for-invalid" }.sorted()

    @ParameterizedTest
    @MethodSource("highlightingFileLister")
    fun highlightingTest(baseName: String) {
        runInEdtAndWait {
            myFixture.testHighlighting(false, true, false, "$myFullDataPath/$baseName.elv")
        }
    }
}