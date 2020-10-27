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

    fun nameMatches(ref: ReferenceWithNamespacePsiElement): Boolean = !ref.hasNamespace && compound.textMatches(ref.targetElement)

    abstract val compound: ElvishCompound

    override fun getIcon(flags: Int): Icon? = AllIcons.Nodes.Parameter

    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(compound.text, AllIcons.Nodes.Parameter)
}