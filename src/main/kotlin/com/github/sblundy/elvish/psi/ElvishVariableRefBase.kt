package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishVariableRefBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishVariableRef {
    override fun getReference(): PsiReference? = if (namespaceName != null) {
        ElvishNamespaceVariableReference(this, variableName.textRangeInParent)
    } else {
        ElvishVariableReference(this, variableName.textRangeInParent)
    }
}