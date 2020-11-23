package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishCommandExpressionBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishCommandExpression {
    override fun getReference(): PsiReference? = ElvishCommandReference(this, commandName.textRangeInParent)
}