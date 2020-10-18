package com.github.sblundy.elvish.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ElvishAssignmentBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishAssignment, ElvishVariableScope {
    override fun findVariables(ref: ReferenceWithNamespacePsiElement): Collection<ElvishVariableDeclaration> {
        return variableList.filter { declaration -> declaration.nameMatches(ref) }
    }

    override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
        variableList.any {
            processor.process(it)
        }
    }
}