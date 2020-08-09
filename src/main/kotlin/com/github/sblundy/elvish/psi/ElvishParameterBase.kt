package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException

abstract class ElvishParameterBase(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement? = compound

    override fun getName(): String? = compound.text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        //TODO is actually a variable name?
        val ne = newNameElement(name, project)
        compound.replace(ne)
        return this
    }

    fun nameMatches(name: String, ns: List<String?>): Boolean = ns.isEmpty() && compound.textMatches(name)

    abstract val compound: ElvishCompound
}