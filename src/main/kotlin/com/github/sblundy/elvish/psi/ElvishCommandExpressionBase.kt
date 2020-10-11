package com.github.sblundy.elvish.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishCommandExpressionBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishCommandExpression {
    override fun getReference(): PsiReference? = ElvishCommandReference(this, commandBareword.textRangeInParent)
}