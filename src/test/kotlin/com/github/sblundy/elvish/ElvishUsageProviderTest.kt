package com.github.sblundy.elvish

import com.intellij.testFramework.TestDataPath
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("src/test/resources/com/github/sblundy/elvish/")
class ElvishUsageProviderTest: LightProjectTestBase() {
    @Test
    fun testFindUsagesLocalVariable() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-var.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesBuiltin() {
        runTest {
            var usages = myFixture.testFindUsages("ElvishUsageProviderTest-builtin.elv")

            Assertions.assertNotNull(usages)
            usages = usages.filter { it.file?.name == "ElvishUsageProviderTest-builtin.elv" }//HACK usages from other files are being returned
            Assertions.assertEquals(2, usages.size)
            Assertions.assertEquals(0, usages.firstOrNull()?.navigationOffset)
            Assertions.assertEquals(12, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesBuiltinNS() {
        runTest {
            var usages = myFixture.testFindUsages("ElvishUsageProviderTest-builtin-ns.elv")

            Assertions.assertNotNull(usages)
            usages = usages.filter { it.file?.name == "ElvishUsageProviderTest-builtin-ns.elv" }//HACK usages from other files are being returned
            Assertions.assertEquals(2, usages.size)
            Assertions.assertEquals(0, usages.firstOrNull()?.navigationOffset)
            Assertions.assertEquals(20, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
            Assertions.assertEquals(18, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsages1stParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter-1st.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
            Assertions.assertEquals(21, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsages2ndParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter-2nd.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
            Assertions.assertEquals(21, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesScopingLocalVariable() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-var-scope.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesScopingParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter-scope.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-ns-var.elv", "yy.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(2, usages.size)
        }
    }

    @Test
    fun testFindUsagesLocalNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-scope.elv", "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-local-scope.elv" }

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
            Assertions.assertEquals(41, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesLocalNamespacedVarRef() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-scope-ref.elv", "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-local-scope-ref.elv" }

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size, usages.mapNotNull { it.element?.text + "<${it.navigationOffset}>" }.joinToString(","))
            Assertions.assertEquals(41, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesUpNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-up-scope.elv").filter { it.file?.name == "ElvishUsageProviderTest-up-scope.elv" }

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size, usages.mapNotNull { it.element?.text }.joinToString(","))
            Assertions.assertEquals(47, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesFnCommand() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-fn.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
            Assertions.assertEquals(26, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesMuseliGit() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-museli-git.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(1, usages.size)
            Assertions.assertEquals(127, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesEditVarRef() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-edit-command.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(2, usages.size, usages.mapNotNull { it.element?.text }.joinToString(","))
            Assertions.assertEquals(5, usages.firstOrNull()?.navigationOffset)
            Assertions.assertEquals(46, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesEditCommand() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-edit-var-ref.elv")

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(2, usages.size)
            Assertions.assertEquals(11, usages.firstOrNull()?.navigationOffset)
            Assertions.assertEquals(42, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesUseInFnBody() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-use-in-fn-body.elv", "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-use-in-fn-body.elv" }

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(2, usages.size)
            Assertions.assertEquals(29, usages.firstOrNull()?.navigationOffset)
            Assertions.assertEquals(49, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesBundled() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-bundled.elv").filter { it.file?.name == "ElvishUsageProviderTest-bundled.elv" }

            Assertions.assertNotNull(usages)
            Assertions.assertEquals(2, usages.size)
            Assertions.assertEquals(21, usages.firstOrNull()?.navigationOffset)
            Assertions.assertEquals(39, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }
}
