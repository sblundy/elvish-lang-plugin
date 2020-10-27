package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.FakePsiElement
import icons.ElvishIcons
import javax.swing.Icon

sealed class ElvishPsiBuiltin(private val manager: PsiManager) : FakePsiElement() {
    abstract val builtin: String
    abstract val isDoNotUse: Boolean
    override fun getLanguage() = ElvishLanguage.INSTANCE
    override fun getParent(): PsiElement? = null
    override fun getProject() = manager.project
    override fun getManager() = manager
    override fun canNavigateToSource() = false
    override fun getContainingFile(): PsiFile? = null
    override fun isValid() = true
    override fun getText() = builtin
    override fun getTextLength() = builtin.length
    override fun getName() = builtin
}

class ElvishPsiBuiltinCommand(override val builtin: String, manager: PsiManager) : ElvishPsiBuiltin(manager),
    ElvishFunctionDeclaration {
    override fun nameMatches(ref: ReferenceWithNamespacePsiElement) = !ref.hasNamespace && ref.targetElement.textMatches(builtin)
    override val isDoNotUse: Boolean = builtin.startsWith('-') && builtin != "-"
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.BUILTIN_FUNCTION
    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(builtin, ElvishIcons.BUILTIN_FUNCTION)
}

class ElvishPsiBuiltinVariable(override val builtin: String, manager: PsiManager) : ElvishPsiBuiltin(manager),
    ElvishVariableDeclaration {
    override fun nameMatches(ref: ReferenceWithNamespacePsiElement) = !ref.hasNamespace && ref.targetElement.textMatches(builtin)
    override val isDoNotUse: Boolean = builtin.startsWith('-')
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.BUILTIN_VARIABLE
    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(builtin, ElvishIcons.BUILTIN_VARIABLE)
}

class ElvishPsiBuiltinValue(override val builtin: String, manager: PsiManager) : ElvishPsiBuiltin(manager),
    ElvishVariableDeclaration {
    override fun nameMatches(ref: ReferenceWithNamespacePsiElement) = !ref.hasNamespace && ref.targetElement.textMatches(builtin)
    override val isDoNotUse: Boolean = builtin.startsWith('-')
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.BUILTIN_VALUE
    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(builtin, ElvishIcons.BUILTIN_VALUE)
}