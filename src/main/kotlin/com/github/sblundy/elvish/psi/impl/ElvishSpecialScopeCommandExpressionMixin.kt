package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishSpecialScopeCommandExpression
import com.github.sblundy.elvish.psi.ElvishSpecialScopeCommandReference
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishSpecialScopeCommandExpressionMixin(node: ASTNode?) : ElvishCommandImpl(node) ,ElvishSpecialScopeCommandExpression {
    override fun getReference(): PsiReference = ElvishSpecialScopeCommandReference(this, commandName.textRangeInParent)
}