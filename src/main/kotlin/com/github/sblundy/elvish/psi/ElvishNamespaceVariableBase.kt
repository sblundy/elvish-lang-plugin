package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException
import javax.swing.Icon

abstract class ElvishNamespaceVariableBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), PsiNameIdentifierOwner, ElvishNamespaceVariable {
    override fun getNameIdentifier(): PsiElement? = getVariableName()

    override fun getName(): String? = getVariableName().text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = newNameElement(name, project)
        getVariableName().replace(ne)
        return this
    }

    override fun isWritable(): Boolean {
        return super.isWritable() && (namespaceIdentifier is ElvishNamespaceName || namespaceIdentifier is ElvishLocalNamespace || namespaceIdentifier is ElvishUpNamespace)
    }

    override fun getTextOffset(): Int = getVariableName().textOffset

    override fun getIcon(flags: Int): Icon? = AllIcons.Nodes.Variable

    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(getVariableName().text, AllIcons.Nodes.Variable)
}