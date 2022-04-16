package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishNamespaceVariableReference
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishNamespaceVariableRefMixin(node: ASTNode?) : ElvishExternalVariableReferenceImpl(node) {
    override fun getReference(): PsiReference = ElvishNamespaceVariableReference(this, variableName.textRangeInParent)
}