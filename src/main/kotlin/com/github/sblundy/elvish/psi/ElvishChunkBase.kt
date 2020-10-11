package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.findFnCommandInParentScope
import com.github.sblundy.elvish.psi.ElvishPsiUtils.findVariableInParentScope
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ElvishChunkBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishChunk, ElvishDeclarationScope {
    override fun findVariables(name: String, ns: List<String>): Collection<ElvishVariableDeclaration> {
        return assignmentList.flatMap { it.variableList }
            .filter { it.nameMatches(name, ns) } +
                findVariableInParentScope(name, ns, this)
    }

    override fun findFnCommands(name: String, ns: MutableList<String>): Collection<ElvishFunctionDeclaration> {
        return fnCommandList.filter { it.nameMatches(name, ns) } + findFnCommandInParentScope(name, ns, this)
    }
}