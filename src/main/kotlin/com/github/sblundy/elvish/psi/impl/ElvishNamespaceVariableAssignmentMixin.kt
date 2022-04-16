package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishNamespaceVariableAssignmentMixin(node: ASTNode?) : ElvishVariableAssignmentMixin(node), ElvishNamespaceVariableAssignment {
    fun getName(e: ElvishLValueVariable): String = e.variableName.text

    override fun isWritable(): Boolean =
        parent.isWritable && (namespaceIdentifier is ElvishNamespaceName || namespaceIdentifier is ElvishLocalNamespace || namespaceIdentifier is ElvishUpNamespace)

    override fun getReference(): PsiReference = ElvishNamespaceVariableReference(this, variableName.textRangeInParent)
}