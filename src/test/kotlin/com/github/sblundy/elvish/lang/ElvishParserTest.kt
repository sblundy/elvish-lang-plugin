package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.mock.MockPsiManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.vfs.CharsetToolkit
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.PsiFileFactoryImpl
import com.intellij.testFramework.*
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import org.jetbrains.annotations.NonNls
import org.junit.Assert
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.io.File
import java.util.regex.Pattern

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("\$CONTENT_ROOT/src/test/resources/")
class ElvishParserTest {
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
        myFixture = IdeaTestFixtureFactory.getFixtureFactory()
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

    @Suppress("unused")
    fun tokensFileLister(): List<String> =
        FileUtil.findFilesByMask(Pattern.compile(".*\\.tokens.txt"), File(myFullDataPath)).map { it.name.removeSuffix(".tokens.txt") }

    @Suppress("unused")
    fun fileLister(): List<String> =
        FileUtil.findFilesByMask(Pattern.compile(".*\\.elv"), File(myFullDataPath)).map { it.nameWithoutExtension }
            .filterNot { it == "for-invalid" }


    @ParameterizedTest
    @MethodSource("tokensFileLister")
    fun lexerTest(baseName: String) {
        val text = loadFile("$baseName.elv")

        checkLexer(baseName, text)
    }

    @ParameterizedTest
    @MethodSource("fileLister")
    fun parseTest(baseName: String) {
        val text = loadFile("$baseName.elv")
        val output = createFile("$baseName.elv", text)

        Assert.assertNotNull(output)
        output!!.accept(PsiElementVisitor.EMPTY_VISITOR)
        Assert.assertEquals(text, output.text)

        runInEdtAndWait {
            ParsingTestCase.doCheckResult(myFullDataPath, output, true, baseName, false, false)
        }
    }

    private fun checkLexer(baseName: String, text: String) {
        val lexer = ElvishLexerAdapter()
        val actual = LexerTestCase.printTokens(text, 0, lexer)
        UsefulTestCase.assertSameLinesWithFile("$myFullDataPath/$baseName.tokens.txt", actual)
    }

    private fun createFile(name: String, text: String): PsiFile? {
        val virtualFile = LightVirtualFile(name, ElvishLanguage.INSTANCE, text)
        virtualFile.charset = CharsetToolkit.UTF8_CHARSET
        return createFile(virtualFile)
    }

    private fun createFile(virtualFile: LightVirtualFile): PsiFile? {
        return myFileFactory.trySetupPsiForFile(
            virtualFile,
            ElvishLanguage.INSTANCE, true, false
        )
    }

    private fun loadFile(@NonNls @TestDataFile name: String): String {
        return loadFileDefault(myFullDataPath, name)
    }

    private fun loadFileDefault(dir: String, name: String): String {
        val file = File(dir, name)
        return FileUtil.loadFile(file, CharsetToolkit.UTF8, true).trim { it <= ' ' }
    }
}