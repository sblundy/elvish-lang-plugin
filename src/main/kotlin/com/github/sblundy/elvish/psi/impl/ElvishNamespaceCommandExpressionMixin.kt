package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishNamespaceCommandExpression
import com.github.sblundy.elvish.psi.ElvishNamespaceCommandReference
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishNamespaceCommandExpressionMixin(node: ASTNode?) : ElvishCommandImpl(node), ElvishNamespaceCommandExpression {
    override fun getReference(): PsiReference = ElvishNamespaceCommandReference(this, commandName.textRangeInParent)
}