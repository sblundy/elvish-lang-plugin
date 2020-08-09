package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException

abstract class ElvishVariableBase(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement? = variableName

    override fun getName(): String? = variableName.text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = newNameElement(name, project)
        variableName.replace(ne)
        return this
    }

    override fun getTextOffset(): Int = variableName.textOffset

    abstract val variableName: ElvishVariableName
    abstract val namespaceNameList: List<ElvishNamespaceName>

    fun nameMatches(name: String, ns: List<String>): Boolean {
        return variableName.textMatches(name) &&
                ns.size == namespaceNameList.size &&
                ns.zip(namespaceNameList).all { (a, b) -> a == b.text }
    }
}