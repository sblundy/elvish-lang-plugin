package com.github.sblundy.elvish

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.PlatformTestUtil
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
class ElvishStructureViewFactoryTest {
    private var myFullDataPath: String = "com/github/sblundy/elvish"

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
    fun structuralViewLister(): List<String> {
        val dir = File(myFixture.testDataPath, myFullDataPath)
        val files = FileUtil.findFilesByMask(Pattern.compile("ElvishStructureViewFactoryTest-.*\\.elv"), dir).map { it.nameWithoutExtension }
            .filterNot { it == "for-invalid" }
        return files
    }

    @ParameterizedTest
    @MethodSource("structuralViewLister")
    fun structuralViewTest(baseName: String) {
        myFixture.configureByFile("$myFullDataPath/$baseName.elv")
        val expected = File(myFixture.testDataPath, "$myFullDataPath/$baseName.expected.txt").readText()

        runInEdtAndWait {
            myFixture.testStructureView { svc ->
                PlatformTestUtil.expandAll(svc.tree)
                PlatformTestUtil.assertTreeEqual(svc.tree, expected)
            }
        }
    }
}