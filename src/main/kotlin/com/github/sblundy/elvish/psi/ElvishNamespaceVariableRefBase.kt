package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishNamespaceVariableRefBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishNamespaceVariableRef {
    override fun getReference(): PsiReference? =
        ElvishNamespaceVariableReference(this, variableName.textRangeInParent)
}