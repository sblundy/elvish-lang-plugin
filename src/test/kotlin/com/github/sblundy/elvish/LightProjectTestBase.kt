package com.github.sblundy.elvish

import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtil
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.TestDataPath
import com.intellij.testFramework.TestLoggerFactory
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl
import com.intellij.testFramework.runInEdtAndWait
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import java.io.File
import java.lang.reflect.InvocationTargetException
import java.util.regex.Pattern
import java.util.stream.Stream

abstract class LightProjectTestBase {
    protected lateinit var myFixture: CodeInsightTestFixture

    protected lateinit var myModule: Module

    protected lateinit var myProject: Project

    @BeforeAll
    fun setup() {
        val factory = IdeaTestFixtureFactory.getFixtureFactory()
        val descriptor = LightProjectDescriptor()
        val fixtureBuilder = factory.createLightFixtureBuilder(descriptor)
        val fixture = fixtureBuilder.fixture
        myFixture = IdeaTestFixtureFactory.getFixtureFactory()
            .createCodeInsightFixture(fixture, LightTempDirTestFixtureImpl(true))
        this.javaClass.getAnnotation(TestDataPath::class.java)?.let {
            myFixture.testDataPath = it.value
        }
        myFixture.setCaresAboutInjection(false)

        myFixture.setUp()

        myProject = myFixture.project
        myModule = myFixture.module
    }

    @AfterAll
    fun teardown() {
        myFixture.tearDown()
    }

    fun runTest(t: () -> Unit) {
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

    @Target(AnnotationTarget.FUNCTION)
    annotation class FilePattern(val filePattern: String, val trimPrefix: String = "", val filter:Array<String> = [])
    class FileBasenameProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext): Stream<out Arguments> {
            val pattern = context.requiredTestMethod.getAnnotation(FilePattern::class.java)
            val testDataDir = context.requiredTestClass.getAnnotation(TestDataPath::class.java)
            val dir = File(testDataDir.value)
            return FileUtil.findFilesByMask(Pattern.compile(pattern.filePattern), dir)
                .map { it.nameWithoutExtension }
                .map { it.substring(pattern.trimPrefix.length) }
                .filterNot { pattern.filter.contains(it) }
                .map { Arguments.of(it) }
                .stream()

        }
    }
}