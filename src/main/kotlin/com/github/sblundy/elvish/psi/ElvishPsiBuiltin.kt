package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.FakePsiElement
import icons.ElvishIcons
import javax.swing.Icon

sealed class ElvishPsiBuiltin(private val manager: PsiManager) : FakePsiElement() {
    abstract val builtin: String
    override fun getLanguage() = ElvishLanguage.INSTANCE
    override fun getParent(): PsiElement? = null
    override fun getProject() = manager.project
    override fun getManager() = manager
    override fun canNavigate() = false
    override fun getContainingFile(): PsiFile? = null
    override fun isValid() = true
    override fun getText() = builtin
    override fun getTextLength() = builtin.length
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.FILE_ICON
    override fun getName() = builtin
}

class ElvishPsiBuiltinCommand(override val builtin: String, manager: PsiManager) : ElvishPsiBuiltin(manager),
    ElvishFunctionDeclaration {
    override fun nameMatches(name: String, ns: List<String>) = ns.isEmpty() && name == builtin
}

class ElvishPsiBuiltinVariable(override val builtin: String, manager: PsiManager) : ElvishPsiBuiltin(manager),
    ElvishVariableDeclaration {
    override fun nameMatches(name: String, ns: List<String>) = ns.isEmpty() && name == builtin
}