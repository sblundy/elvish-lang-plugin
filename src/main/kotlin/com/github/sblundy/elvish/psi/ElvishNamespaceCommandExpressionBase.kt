package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishNamespaceCommandExpressionBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishNamespaceCommandExpression {
    override fun getReference(): PsiReference? =
        ElvishNamespaceCommandReference(this, commandName.textRangeInParent)
}