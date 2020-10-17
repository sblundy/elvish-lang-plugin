package com.github.sblundy.elvish.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ElvishAssignmentBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishAssignment, ElvishVariableScope {
    override fun findVariables(name: String, ns: List<String>): Collection<ElvishVariableDeclaration> {
        return variableList.filter { declaration -> declaration.nameMatches(name, ns) }
    }

    override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
        variableList.any {
            processor.process(it)
        }
    }
}