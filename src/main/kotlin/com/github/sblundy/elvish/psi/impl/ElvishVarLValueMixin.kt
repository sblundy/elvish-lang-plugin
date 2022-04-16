package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishLValue
import com.github.sblundy.elvish.psi.ElvishNamespaceIdentifier
import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException

abstract class ElvishVarLValueMixin(node: ASTNode?) : ElvishVariableAssignmentMixin(node), ElvishLValue, PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement = variableName

    override fun getName(): String = variableName.text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = newNameElement(name, project)
        variableName.replace(ne)
        return this
    }

    override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier? = null
}