package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException

abstract class FnCommandBase(node: ASTNode) : ElvishLambdaBase(node), ElvishFunctionDeclaration, PsiNameIdentifierOwner {
    override fun nameMatches(name: String, ns: List<String>): Boolean =
        ns.isEmpty() && variableName.textMatches(name)

    abstract val variableName: ElvishVariableName

    override fun getNameIdentifier(): PsiElement? = variableName

    override fun getName(): String? {
        return variableName.text
    }

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, project)
        variableName.replace(ne)
        return this
    }

    override fun getTextOffset(): Int {
        return variableName.textOffset
    }
}