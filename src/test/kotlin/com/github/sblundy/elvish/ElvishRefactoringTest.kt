package com.github.sblundy.elvish

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("\$CONTENT_ROOT/src/test/resources/")
class ElvishRefactoringTest {
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

    @Test
    fun testRenameVar() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishRefactoringTest-rename-var.elv")
            myFixture.renameElementAtCaret("zz")
            myFixture.checkResultByFile(myFullDataPath + "ElvishRefactoringTest-rename-var.elv", myFullDataPath + "ElvishRefactoringTest-rename-var-after.elv", false)
        }
    }

    @Test
    fun testRenameNamespacedVar() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishRefactoringTest-rename-ns-var.elv")
            myFixture.renameElementAtCaret("zz")
            myFixture.checkResultByFile(myFullDataPath + "ElvishRefactoringTest-rename-ns-var.elv", myFullDataPath + "ElvishRefactoringTest-rename-ns-var-after.elv", false)
        }
    }
}
