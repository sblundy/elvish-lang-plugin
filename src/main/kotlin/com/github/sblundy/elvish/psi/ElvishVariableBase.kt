package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException
import javax.swing.Icon

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
    abstract val namespaceName: ElvishNamespaceName?

    override fun getIcon(flags: Int): Icon? = AllIcons.Nodes.Variable

    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(variableName.text, AllIcons.Nodes.Variable)
}