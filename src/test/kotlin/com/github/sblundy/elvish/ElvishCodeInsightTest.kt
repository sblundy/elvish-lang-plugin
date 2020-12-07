package com.github.sblundy.elvish

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.testFramework.*
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import org.junit.Assert
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

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

    @Test
    fun testCompleteVariable() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-variable.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("x"))
        }
    }

    @Test
    fun testCompleteFunction() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-function.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("laa"))
        }
    }

    @Test
    fun testCompleteBuiltinCommand() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-builtin-command.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("each"))
        }
    }

    @Test
    fun testCompleteBuiltinVarRef() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-builtin-var-ref.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("paths"))
            Assert.assertTrue(lookupStrings.contains("peach~"))
        }
    }

    @Test
    fun testCompleteNamespaceVariable() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-ns-var-ref.elv", myFullDataPath + "yy.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("xx"))
            Assert.assertTrue(lookupStrings.contains("yy~"))
        }
    }

    @Test
    fun testCompleteNamespaceCommand() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-ns-command.elv", myFullDataPath + "yy.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("yy"))
        }
    }

    @Test
    fun testCompleteLocalVariable() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-local-ns-var-ref.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("x"))
            Assert.assertTrue(lookupStrings.contains("yy~"))
        }
    }

    @Test
    fun testCompleteLocalCommand() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-local-ns-command.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("x"))
        }
    }

    @Test
    fun testCompleteUpVariableRef() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-up-ns-var-ref.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("x"))
            Assert.assertTrue(lookupStrings.contains("yy~"))
        }
    }

    @Test
    fun testCompleteUpCommand() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-up-ns-command.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("x"))
        }
    }

    @Test
    fun testCompleteEditVariableRef() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-edit-ns-var-ref.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("prompt"))
            Assert.assertTrue(lookupStrings.contains("complex-candidate~"))
            Assert.assertTrue(lookupStrings.contains("completion:"))
        }
    }

    @Test
    fun testCompleteEditCommand() {
        runTest {
            myFixture.configureByFiles(myFullDataPath + "ElvishCodeInsightTest-completion-edit-ns-command.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()

            Assert.assertTrue(lookupStrings.contains("complex-candidate"))
            Assert.assertTrue(lookupStrings.contains("completion:"))
        }
    }
}
