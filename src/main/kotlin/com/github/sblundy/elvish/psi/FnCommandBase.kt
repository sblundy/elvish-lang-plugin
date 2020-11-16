package com.github.sblundy.elvish.psi

import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.util.IncorrectOperationException
import javax.swing.Icon

abstract class FnCommandBase(node: ASTNode) : ElvishLambdaBase(node), ElvishFunctionDeclaration, PsiNameIdentifierOwner {
    override fun nameMatches(ref: ReferenceWithNamespacePsiElement): Boolean =
        !ref.hasNamespace && getCommandName().textMatches(ref.targetElement)

    override fun getNameIdentifier(): PsiElement? = getCommandName()

    override fun getName(): String? {
        return getCommandName().text
    }

    @Throws(IncorrectOperationException::class)
    override fun setName(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, project)
        getCommandName().replace(ne)
        return this
    }

    override fun getTextOffset(): Int {
        return getCommandName().textOffset
    }

    override fun getIcon(flags: Int): Icon? = AllIcons.Nodes.Function

    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(getCommandName().text, AllIcons.Nodes.Function)
}