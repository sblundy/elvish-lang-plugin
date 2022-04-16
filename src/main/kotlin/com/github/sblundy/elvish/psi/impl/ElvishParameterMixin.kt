package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishBasicItemPresentation
import com.github.sblundy.elvish.psi.ElvishParameter
import com.github.sblundy.elvish.psi.ElvishPsiUtils
import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.util.IncorrectOperationException
import javax.swing.Icon

abstract class ElvishParameterMixin(node: ASTNode) : ElvishVariableDeclarationImpl(node), ElvishParameter {
    override fun getNameIdentifier(): PsiElement = variableName

    override fun getName(): String = variableName.text

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = newNameElement(name, project)
        variableName.replace(ne)
        return this
    }

    override fun getIcon(flags: Int): Icon = AllIcons.Nodes.Parameter

    override fun getPresentation(): ItemPresentation =
        ElvishBasicItemPresentation(variableName.text, AllIcons.Nodes.Parameter)

}