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
    fun testFindUsagesParameter() {
        runTest {
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-lambda-parameter.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
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
            val usages = myFixture.testFindUsages(myFullDataPath + "ElvishUsageProviderTest-ns-var.elv")

            Assert.assertNotNull(usages)
            Assert.assertEquals(1, usages.size)
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