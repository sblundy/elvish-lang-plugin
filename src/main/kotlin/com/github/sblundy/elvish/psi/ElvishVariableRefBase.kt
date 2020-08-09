package com.github.sblundy.elvish.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishVariableRefBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishVariableRef {
    override fun getReference(): PsiReference? = ElvishVariableReference(this, variableName.textRangeInParent)
}