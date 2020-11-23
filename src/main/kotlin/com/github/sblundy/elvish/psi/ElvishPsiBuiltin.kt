package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.FakePsiElement
import icons.ElvishIcons
import javax.swing.Icon

sealed class ElvishPsiBuiltin(private val manager: PsiManager, private val owner: ElvishLexicalScope) : FakePsiElement(), ElvishPsiElement {
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
    override fun getScope(): ElvishLexicalScope? = owner
}

class ElvishPsiBuiltinCommand(override val builtin: String, manager: PsiManager, owner: ElvishLexicalScope) :
    ElvishPsiBuiltin(manager, owner),
    ElvishFunctionDeclaration {
    private val commandName = FakeVariableName(builtin, manager, owner)
    override fun getCommandName(): ElvishVariableName = commandName
    override fun getScope(): ElvishLexicalScope? {
        TODO("Not yet implemented")
    }

    override val isDoNotUse: Boolean = builtin.startsWith('-') && builtin != "-"
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.BUILTIN_FUNCTION
    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(builtin, ElvishIcons.BUILTIN_FUNCTION)
}

class ElvishPsiBuiltinVariable(override val builtin: String, manager: PsiManager, owner: ElvishLexicalScope) :
    ElvishPsiBuiltin(manager, owner),
    ElvishVariableDeclaration {
    private val variableName = FakeVariableName(builtin, manager, owner)
    override fun getVariableName(): ElvishVariableName = variableName
    override val isDoNotUse: Boolean = builtin.startsWith('-')
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.BUILTIN_VARIABLE
    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(builtin, ElvishIcons.BUILTIN_VARIABLE)
}

class ElvishPsiBuiltinValue(override val builtin: String, manager: PsiManager, owner: ElvishLexicalScope) :
    ElvishPsiBuiltin(manager, owner),
    ElvishVariableDeclaration {
    private val variableName = FakeVariableName(builtin, manager, owner)
    override fun getVariableName(): ElvishVariableName = variableName
    override val isDoNotUse: Boolean = builtin.startsWith('-')
    override fun getIcon(open: Boolean): Icon? = ElvishIcons.BUILTIN_VALUE
    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(builtin, ElvishIcons.BUILTIN_VALUE)
}

private class FakeVariableName(override val builtin: String, manager: PsiManager, owner: ElvishLexicalScope):
    ElvishPsiBuiltin(manager, owner),
    ElvishVariableName {
    override val isDoNotUse: Boolean = false
}