package com.github.sblundy.elvish.lang

import com.intellij.mock.MockPsiManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.CharsetToolkit
import com.intellij.psi.impl.PsiFileFactoryImpl
import com.intellij.testFramework.*
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.JavaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import org.jetbrains.annotations.NonNls
import org.junit.Assert
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("\$CONTENT_ROOT/src/test/resources/")
class ElvishLexerTest {
    private lateinit var myPsiManager: MockPsiManager
    private lateinit var myFileFactory: PsiFileFactoryImpl
    private var myFullDataPath: String = "src/test/resources/com/github/sblundy/elvish/lang"

    private lateinit var myFixture: IdeaProjectTestFixture

    private lateinit var myModule: Module

    private lateinit var myProject: Project

    @BeforeAll
    fun setup() {
        val factory = IdeaTestFixtureFactory.getFixtureFactory()
        val fixtureBuilder = factory.createLightFixtureBuilder()
        val fixture = fixtureBuilder.fixture
        myFixture = JavaTestFixtureFactory.getFixtureFactory()
            .createCodeInsightFixture(fixture, LightTempDirTestFixtureImpl(true))

        myFixture.setUp()

        myProject = myFixture.project
        myModule = myFixture.module
        myPsiManager = MockPsiManager(myProject)
        myFileFactory = PsiFileFactoryImpl(myPsiManager)
    }

    @AfterAll
    fun teardown() {
        myFixture.tearDown()
    }

    @Test
    fun simpleEcho() {
        doTest("echo")
    }

    @Test
    fun simpleEchoWithArg() {
        doTest("echo-n")
    }

    @Test
    fun comment() {
        doTest("comment")
    }

    @Test
    fun commentAbove() {
        doTest("comment-above")
    }

    @Test
    fun commentInline() {
        doTest("comment-inline")
    }

    @Test
    fun twoCommands() {
        doTest("two-commands")
    }

    @Test
    fun commandWithString() {
        doTest("command-with-string")
    }

    @Test
    fun assignmentSingleQuotedString() {
        doTest("assignment-single-quoted-string")
    }

    @Test
    fun assignmentThenCommand() {
        doTest("assignment-then-command")
    }

    @Test
    fun commandWithContinuation() {
        doTest("command-continuation")
    }

    @Test
    fun commandWithVariable() {
        doTest("command-with-variable")
    }

    private fun doTest(baseName: String) {
        val text = loadFile("$baseName.elv")

        checkLexer(baseName, text)
    }

    private fun loadFile(@NonNls @TestDataFile name: String): String {
        return loadFileDefault(myFullDataPath, name)
    }

    private fun loadFileDefault(dir: String, name: String): String {
        val file = File(dir, name)
        println(file.absolutePath)
        return FileUtil.loadFile(file, CharsetToolkit.UTF8, true).trim { it <= ' ' }
    }

    private fun checkLexer(baseName: String, text: String) {
        val lexer = ElvishLexerAdapter()
        val tokens = loadFile("$baseName.tokens.txt")
        val actual = LexerTestCase.printTokens(text, 0, lexer)
        Assert.assertEquals(tokens.trimEnd(), actual.trimEnd())
    }
}