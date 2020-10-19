package com.github.sblundy.elvish.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference

abstract class ElvishVariableRefBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishVariableRef {
    override val targetElement: PsiElement
        get() = variableName
    override val namespacePathElements: List<PsiElement>
        get() = namespaceName?.variableNameList?.map { it } ?: emptyList()
    override val namespaceLength: Int
        get() = namespacePathElements.size
    override val hasNamespace: Boolean
        get() = namespacePathElements.isNotEmpty()

    override fun getReference(): PsiReference? = ElvishVariableReference(this, variableName.textRangeInParent)
}