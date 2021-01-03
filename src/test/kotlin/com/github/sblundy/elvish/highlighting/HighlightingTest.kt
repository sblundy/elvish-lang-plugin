package com.github.sblundy.elvish.highlighting

import com.github.sblundy.elvish.LightProjectTestBase
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.runInEdtAndWait
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ArgumentsSource

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("src/test/resources/com/github/sblundy/elvish/highlighting/")
class HighlightingTest: LightProjectTestBase() {
    @ParameterizedTest
    @ArgumentsSource(FileBasenameProvider::class)
    @FilePattern(filePattern = ".*\\.elv")
    fun highlightingTest(baseName: String) {
        runInEdtAndWait {
            myFixture.testHighlighting(false, true, false, "$baseName.elv")
        }
    }
}