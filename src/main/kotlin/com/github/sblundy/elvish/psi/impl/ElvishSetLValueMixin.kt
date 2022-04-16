package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.*
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishSetLValueMixin(node: ASTNode?) : ElvishVariableAssignmentMixin(node), ElvishSetLValue {
    override fun getName(): String = variableName.text

    override fun isWritable(): Boolean =
        parent.isWritable && (namespaceIdentifier is ElvishNamespaceName || namespaceIdentifier is ElvishLocalNamespace || namespaceIdentifier is ElvishUpNamespace)

    override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier? = null

    override fun getReference(): PsiReference = ElvishSetLValueReference(this, variableName.textRangeInParent)
}