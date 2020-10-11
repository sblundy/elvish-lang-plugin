package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import org.jetbrains.annotations.NotNull

object ElvishPsiUtils {
    fun findVariableInParentScope(name: @NotNull String, ns: @NotNull List<String>, e: @NotNull PsiElement): @NotNull Collection<ElvishVariableDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findVariables(name, ns)
    }

    fun findFnCommandInParentScope(name: @NotNull String, ns: @NotNull List<String>, e: @NotNull PsiElement): @NotNull Collection<ElvishFunctionDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findFnCommands(name, ns)
    }

    fun findParentScope(e: PsiElement): ElvishDeclarationScope? {
        var parent: PsiElement? = e.parent
        while (parent != null) {
            when (parent) {
                is ElvishFile -> {
                    break
                }
                is ElvishDeclarationScope -> return parent
                is ElvishVariableScope -> return ElvishVariableScopeWrapper(parent)
                is ElvishFunctionScope -> return ElvishFunctionScopeWrapper(parent)
            }
            parent = parent.parent
        }
        return null
    }

    private class ElvishFunctionScopeWrapper(private val inner: ElvishFunctionScope) : ElvishDeclarationScope {
        override fun findVariables(name: String, ns: MutableList<String>): Collection<ElvishVariableDeclaration> = mutableListOf()
        override fun findFnCommands(name: String, ns: MutableList<String>) = inner.findFnCommands(name, ns)
    }

    private class ElvishVariableScopeWrapper(private val inner: ElvishVariableScope) : ElvishDeclarationScope {
        override fun findVariables(name: String, ns: MutableList<String>) = inner.findVariables(name, ns)
        override fun findFnCommands(name: String, ns: MutableList<String>): Collection<ElvishFunctionDeclaration> = mutableListOf()
    }

    fun newNameElement(name: String, myProject: Project): ElvishVariableName {
        val file = createDummyFile("$name = 1", myProject)
        val chunk = file.firstChild as ElvishChunk
        return chunk.assignmentList[0].variableList[0].variableName
    }


    private fun createDummyFile(content: String, myProject: Project): PsiFile {
        val psiFileFactory = PsiFileFactory.getInstance(myProject)
        return psiFileFactory.createFileFromText(
            "dummy." + ElvishFileType.INSTANCE.defaultExtension,
            ElvishFileType.INSTANCE,
            content
        )
    }
}