package com.github.sblundy.elvish

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.UsefulTestCase
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("src/test/resources/com/github/sblundy/elvish/")
class ElvishCodeInsightTest: LightProjectTestBase() {
    @ParameterizedTest
    @ArgumentsSource(FileBasenameProvider::class)
    @FilePattern(filePattern = "ElvishCodeInsightTest-completion-.*\\.elv", trimPrefix = "ElvishCodeInsightTest-completion-")
    fun testCompletion(basename: String) {
        runTest {
            myFixture.configureByFiles("ElvishCodeInsightTest-completion-$basename.elv", "yy.elv", "yy/xx.elv")
            myFixture.complete(CompletionType.BASIC, 1)

            val lookupStrings = myFixture.lookupElementStrings?: listOf()
            UsefulTestCase.assertSameLinesWithFile(myFixture.testDataPath + "ElvishCodeInsightTest-completion-$basename.txt", lookupStrings.sorted().joinToString("\n"))
        }
    }
}
