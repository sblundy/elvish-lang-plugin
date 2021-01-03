package com.github.sblundy.elvish

import com.intellij.testFramework.PlatformTestUtil
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.runInEdtAndWait
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource
import java.io.File

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("src/test/resources/com/github/sblundy/elvish/")
class ElvishStructureViewFactoryTest: LightProjectTestBase() {
    @ParameterizedTest
    @ArgumentsSource(FileBasenameProvider::class)
    @FilePattern(filePattern = "ElvishStructureViewFactoryTest-.*\\.elv", trimPrefix = "ElvishStructureViewFactoryTest-")
    fun structuralViewTest(baseName: String) {
        myFixture.configureByFile("ElvishStructureViewFactoryTest-$baseName.elv")
        val expected = File(myFixture.testDataPath, "ElvishStructureViewFactoryTest-$baseName.expected.txt").readText()

        runInEdtAndWait {
            myFixture.testStructureView { svc ->
                PlatformTestUtil.expandAll(svc.tree)
                PlatformTestUtil.assertTreeEqual(svc.tree, expected)
            }
        }
    }
}