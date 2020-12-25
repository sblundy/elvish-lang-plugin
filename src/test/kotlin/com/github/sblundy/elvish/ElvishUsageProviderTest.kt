package com.github.sblundy.elvish

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
import java.lang.reflect.InvocationTargetException

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestDataPath("\$CONTENT_ROOT/src/test/resources/")
class ElvishUsageProviderTest {
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
    fun testFindUsagesLocalVariable() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-local-var.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesBuiltin() {
        runTest {
            var usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-builtin.elv")

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
            var usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-builtin-ns.elv")

            Assert.assertNotNull(usages)
            usages = usages.filter { it.file?.name == "ElvishUsageProviderTest-builtin-ns.elv" }//HACK usages from other files are being returned
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(0, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(20, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesParameter() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-lambda-parameter.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(18, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsages1stParameter() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-lambda-parameter-1st.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(21, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsages2ndParameter() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-lambda-parameter-2nd.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(21, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesScopingLocalVariable() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-local-var-scope.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesScopingParameter() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-lambda-parameter-scope.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
        }
    }

    @Test
    fun testFindUsagesNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-ns-var.elv", myFullDataPath + "yy.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(2, usages.size)
        }
    }

    @Test
    fun testFindUsagesLocalNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-local-scope.elv", myFullDataPath + "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-local-scope.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(41, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesLocalNamespacedVarRef() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-local-scope-ref.elv", myFullDataPath + "yy.elv").filter { it.file?.name == "ElvishUsageProviderTest-local-scope-ref.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(usages.mapNotNull { it.element?.text + "<${it.navigationOffset}>" }.joinToString(","), 1, usages.size)
            Assert.assertEquals(41, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesUpNamespacedVar() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-up-scope.elv").filter { it.file?.name == "ElvishUsageProviderTest-up-scope.elv" }

            Assert.assertNotNull(usages)
            Assert.assertEquals(usages.mapNotNull { it.element?.text }.joinToString(","), 1, usages.size)
            Assert.assertEquals(47, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesFnCommand() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-fn.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(26, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesMuseliGit() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-museli-git.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
            Assert.assertEquals(127, usages.firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesEditVarRef() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-edit-command.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(usages.mapNotNull { it.element?.text }.joinToString(","), 2, usages.size)
            Assert.assertEquals(5, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(46, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }

    @Test
    fun testFindUsagesEditCommand() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-edit-var-ref.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(2, usages.size)
            Assert.assertEquals(11, usages.firstOrNull()?.navigationOffset)
            Assert.assertEquals(42, usages.drop(1).firstOrNull()?.navigationOffset)
        }
    }
}

fun runTest(t : () -> Unit) {
    runInEdtAndWait {
        val throwables = arrayOfNulls<Throwable>(1)

        try {
            TestLoggerFactory.onTestStarted()
            t()
            TestLoggerFactory.onTestFinished(true)
        } catch (e: InvocationTargetException) {
            TestLoggerFactory.onTestFinished(false)
            e.fillInStackTrace()
            throwables[0] = e.targetException
        } catch (e: IllegalAccessException) {
            TestLoggerFactory.onTestFinished(false)
            e.fillInStackTrace()
            throwables[0] = e
        } catch (e: Throwable) {
            TestLoggerFactory.onTestFinished(false)
            throwables[0] = e
        }

        throwables[0]?.let {
            throw it
        }
    }
}