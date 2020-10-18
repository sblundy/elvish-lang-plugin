package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.findFnCommandInParentScope
import com.github.sblundy.elvish.psi.ElvishPsiUtils.findVariableInParentScope
import com.github.sblundy.elvish.psi.ElvishPsiUtils.processFnCommandInParentScope
import com.github.sblundy.elvish.psi.ElvishPsiUtils.processVariablesInParentScope
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ElvishChunkBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishChunk, ElvishDeclarationScope {
    override fun findVariables(ref: ReferenceWithNamespacePsiElement): Collection<ElvishVariableDeclaration> {
        return assignmentList.flatMap { it.variableList }
            .filter { it.nameMatches(ref) } +
                findVariableInParentScope(ref, this)
    }

    override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
        for (v in assignmentList.flatMap { it.variableList }) {
            if (!processor.process(v)) {
                return
            }
        }

        processVariablesInParentScope(processor, this)
    }

    override fun findFnCommands(ref: ReferenceWithNamespacePsiElement): Collection<ElvishFunctionDeclaration> {
        return fnCommandList.filter { it.nameMatches(ref) } + findFnCommandInParentScope(ref, this)
    }

    override fun processFnCommands(processor: ElvishFunctionScope.FnCommandProcessor) {
        for (v in fnCommandList) {
            if (!processor.process(v)) {
                return
            }
        }

        processFnCommandInParentScope(processor, this)
    }
}