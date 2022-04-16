package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishUpScopeVariableAssignmentMixin(node: ASTNode?) : ElvishVariableAssignmentMixin(node),
    ElvishUpScopeVariableAssignment {
    override fun getName(): String = variableName.text

    override fun getReference(): PsiReference? = if (namespaceIdentifier is ElvishLocalNamespace) {
        null
    } else {
        object :
            ElvishSpecialScopeVariableReference<ElvishUpScopeVariableAssignment>(this, variableName.textRangeInParent) {
            override fun getVariableName(): ElvishVariableName = element.variableName

            override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier = element.namespaceIdentifier
        }
    }
}