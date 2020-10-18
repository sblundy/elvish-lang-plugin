package com.github.sblundy.elvish.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

abstract class ElvishCommandExpressionBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishCommandExpression {
    override val targetElement: PsiElement
        get() = commandBareword
    override val namespacePathElements: List<PsiElement>
        get() = namespaceNameList.map { it }
    override val namespaceLength: Int
        get() = namespacePathElements.size
    override val hasNamespace: Boolean
        get() = namespacePathElements.isNotEmpty()

    override fun getReference(): PsiReference? = ElvishCommandReference(this, commandBareword.textRangeInParent)
}