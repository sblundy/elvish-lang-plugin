package com.github.sblundy.elvish

import com.intellij.testFramework.TestDataPath
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("src/test/resources/com/github/sblundy/elvish/")
class ElvishRefactoringTest: LightProjectTestBase() {
    @Test
    fun testRenameVar() {
        runTest {
            myFixture.configureByFiles("ElvishRefactoringTest-rename-var.elv")
            myFixture.renameElementAtCaret("zz")
            myFixture.checkResultByFile("ElvishRefactoringTest-rename-var.elv", "ElvishRefactoringTest-rename-var-after.elv", false)
        }
    }

    @Test
    fun testRenameNamespacedVar() {
        runTest {
            myFixture.configureByFiles("ElvishRefactoringTest-rename-ns-var.elv", "yy.elv")
            myFixture.renameElementAtCaret("zz")
            myFixture.checkResultByFile("ElvishRefactoringTest-rename-ns-var.elv", "ElvishRefactoringTest-rename-ns-var-after.elv", false)
        }
    }

    @Test
    fun testRenameFnCommand() {
        runTest {
            myFixture.configureByFiles("ElvishRefactoringTest-rename-fn.elv")
            myFixture.renameElementAtCaret("zz")
            myFixture.checkResultByFile("ElvishRefactoringTest-rename-fn.elv", "ElvishRefactoringTest-rename-fn-after.elv", false)
        }
    }
}
