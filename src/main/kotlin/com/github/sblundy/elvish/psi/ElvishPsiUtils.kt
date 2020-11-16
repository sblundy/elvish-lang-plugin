package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.github.sblundy.elvish.lang.version.ElvishBundledService
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory

object ElvishPsiUtils {
    fun findVariableInParentScope(ref: ReferenceWithNamespacePsiElement, e: PsiElement): Collection<ElvishVariableDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findVariables(ref)
    }

    fun findFnCommandInParentScope(ref: ReferenceWithNamespacePsiElement, e: PsiElement): Collection<ElvishFunctionDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findFnCommands(ref)
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
                is ElvishFile -> return projectBuiltinScope(e)
                is ElvishDeclarationScope -> return parent
                is ElvishVariableScope -> return ElvishVariableScopeWrapper(parent)
                is ElvishFunctionScope -> return ElvishFunctionScopeWrapper(parent)
            }
            parent = parent.parent
        }
        return null
    }

    private fun projectBuiltinScope(e: PsiElement): BuiltinScope? {
        e.project.getBuiltinScope()?.let { return it }

        return ElvishBundledService.getInstance().currentVersion(e.project)?.let {
            val scope = BuiltinScope(it, e.manager)
            e.project.putBuiltinScope(scope)
            scope
        }
    }

    private class ElvishFunctionScopeWrapper(private val inner: ElvishFunctionScope) : ElvishDeclarationScope {
        override fun findVariables(ref: ReferenceWithNamespacePsiElement): Collection<ElvishVariableDeclaration> {
            return (inner as? PsiElement)?.let {
                findParentScope(inner)?.findVariables(ref)
            } ?: mutableListOf()
        }

        override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
            (inner as? PsiElement)?.let {
                findParentScope(inner)?.processVariables(processor)
            }
        }

        override fun findFnCommands(ref: ReferenceWithNamespacePsiElement) = inner.findFnCommands(ref)
        override fun processFnCommands(processor: ElvishFunctionScope.FnCommandProcessor) {
            inner.processFnCommands(processor)
        }
    }

    private class ElvishVariableScopeWrapper(private val inner: ElvishVariableScope) : ElvishDeclarationScope {
        override fun findVariables(ref: ReferenceWithNamespacePsiElement) = inner.findVariables(ref)
        override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
            inner.processVariables(processor)
        }

        override fun findFnCommands(ref: ReferenceWithNamespacePsiElement): Collection<ElvishFunctionDeclaration> {
            return (inner as? PsiElement)?.let {
                findParentScope(inner)?.findFnCommands(ref)
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
        return chunk.assignmentList[0].variableList[0].getVariableName()
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