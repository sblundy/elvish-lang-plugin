package com.github.sblundy.elvish

import com.intellij.testFramework.TestDataPath
import org.junit.Assert
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("src/test/resources/com/github/sblundy/elvish/")
class ElvishUsageProviderTest: LightProjectTestBase() {
    @Test
    fun testFindUsagesLocalVariable() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-var.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesLocalVariableMultipleAssignment() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-var-multi-assign.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(16, usages.firstOrNull()?.navigationOffset)
//            Assert.assertEquals(21, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesBuiltin() {
        runTest {
            var usages = myFixture.testFindUsages("ElvishUsageProviderTest-builtin.elv")

            Assert.assertNotNull(usages)
            usages = usages.filter { it.file?.name == "ElvishUsageProviderTest-builtin.elv" }//HACK usages from other files are being returned
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(0, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(12, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesBuiltinNS() {
        runTest {
            var usages = myFixture.testFindUsages("ElvishUsageProviderTest-builtin-ns.elv")

            Assert.assertNotNull(usages)
            usages = usages.filter { it.file?.name == "ElvishUsageProviderTest-builtin-ns.elv" }//HACK usages from other files are being returned
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(13, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(33, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(18, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsages1stParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter-1st.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(21, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsages2ndParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter-2nd.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(21, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesScopingLocalVariable() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-var-scope.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesScopingParameter() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-lambda-parameter-scope.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-ns-var.elv", "yy.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(2, usages.size)
        }
    }

    @Test
    fun testFindUsagesLocalNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-scope.elv", "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-local-scope.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(41, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesLocalNamespacedVarRef() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-local-scope-ref.elv", "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-local-scope-ref.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(usages.mapNotNull { it.element?.text + "<${it.navigationOffset}>" }.joinToString(","), 1, usages.size)
            Assert.assertEquals(41, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesUpNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-up-scope.elv").filter { it.file?.name == "ElvishUsageProviderTest-up-scope.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(usages.mapNotNull { it.element?.text }.joinToString(","), 1, usages.size)
            Assert.assertEquals(47, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesFnCommand() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-fn.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(26, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesMuseliGit() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-museli-git.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(127, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesEditVarRef() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-edit-command.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(usages.mapNotNull { it.element?.text }.joinToString(","), 2, usages.size)
            Assert.assertEquals(5, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(46, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesEditCommand() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-edit-var-ref.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(11, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(42, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesUseInFnBody() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-use-in-fn-body.elv", "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-use-in-fn-body.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(29, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(49, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesBundled() {
        runTest {
            val usages = myFixture.testFindUsages("ElvishUsageProviderTest-bundled.elv").filter { it.file?.name == "ElvishUsageProviderTest-bundled.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(21, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(39, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }
}
