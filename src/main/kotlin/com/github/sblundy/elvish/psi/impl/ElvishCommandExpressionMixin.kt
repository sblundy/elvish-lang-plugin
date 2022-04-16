package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishCommandExpression
import com.github.sblundy.elvish.psi.ElvishCommandReference
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishCommandExpressionMixin(node: ASTNode?) : ElvishCommandImpl(node), ElvishCommandExpression {
    override fun getReference(): PsiReference = ElvishCommandReference(this, commandName.textRangeInParent)
}