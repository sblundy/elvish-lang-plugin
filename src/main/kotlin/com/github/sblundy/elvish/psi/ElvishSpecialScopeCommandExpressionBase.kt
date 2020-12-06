package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishSpecialScopeCommandExpressionBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishSpecialScopeCommandExpression {
    override fun getReference(): PsiReference? =
        ElvishSpecialScopeCommandReference(this, commandName.textRangeInParent)
}