package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishBasicItemPresentation
import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException
import javax.swing.Icon

abstract class ElvishFnCommandMixin(node: ASTNode) : ElvishFunctionDeclarationImpl(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier(): PsiElement = commandName

    override fun getName(): String = commandName.text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = newNameElement(name, project)
        commandName.replace(ne)
        return this
    }

    override fun getTextOffset(): Int = commandName.textOffset

    override fun getIcon(flags: Int): Icon = AllIcons.Nodes.Function

    override fun getPresentation(): ItemPresentation = ElvishBasicItemPresentation(commandName.text, AllIcons.Nodes.Function)
}