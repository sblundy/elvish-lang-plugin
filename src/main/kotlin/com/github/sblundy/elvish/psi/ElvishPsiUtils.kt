package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory

object ElvishPsiUtils {
    fun findVariableInParentScope(name: String, ns: List<String>, e: PsiElement): Collection<ElvishVariableDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findVariables(name, ns)
    }

    fun findFnCommandInParentScope(name: String, ns: List<String>, e: PsiElement): Collection<ElvishFunctionDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findFnCommands(name, ns)
    }

    fun processVariablesInParentScope(processor: ElvishVariableScope.VariableProcessor, e: PsiElement) {
        findParentScope(e)?.processVariables(processor)
    }

    fun processFnCommandInParentScope(processor: ElvishFunctionScope.FnCommandProcessor, e: PsiElement) {
        findParentScope(e)?.processFnCommands(processor)
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
        override fun findVariables(name: String, ns: List<String>): Collection<ElvishVariableDeclaration> {
            return (inner as? PsiElement)?.let {
                findParentScope(inner)?.findVariables(name, ns)
            } ?: mutableListOf()
        }
        override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
            (inner as? PsiElement)?.let {
                findParentScope(inner)?.processVariables(processor)
            }
        }

        override fun findFnCommands(name: String, ns: List<String>) = inner.findFnCommands(name, ns)
        override fun processFnCommands(processor: ElvishFunctionScope.FnCommandProcessor) {
            inner.processFnCommands(processor)
        }
    }

    private class ElvishVariableScopeWrapper(private val inner: ElvishVariableScope) : ElvishDeclarationScope {
        override fun findVariables(name: String, ns: List<String>) = inner.findVariables(name, ns)
        override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
            inner.processVariables(processor)
        }
        override fun findFnCommands(name: String, ns: List<String>): Collection<ElvishFunctionDeclaration> {
            return (inner as? PsiElement)?.let {
                findParentScope(inner)?.findFnCommands(name, ns)
            } ?: mutableListOf()
        }

        override fun processFnCommands(processor: ElvishFunctionScope.FnCommandProcessor) {
            (inner as? PsiElement)?.let {
                findParentScope(inner)?.processFnCommands(processor)
            }
        }
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