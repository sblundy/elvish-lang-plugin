package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishFunctionDeclaration
import com.github.sblundy.elvish.psi.ElvishVariableDeclaration
import com.intellij.psi.PsiElement

interface ElvishModule {
    fun exportedVariables(): Collection<ElvishVariableDeclaration>
    fun exportedVariable(name: PsiElement): ElvishVariableDeclaration? = exportedVariables().find { it.getVariableName().textMatches(name) }
    fun exportedFunctions(): Collection<ElvishFunctionDeclaration>
    fun exportedFunction(name: PsiElement): ElvishFunctionDeclaration? = exportedFunctions().find { it.getCommandName().textMatches(name) }
    fun childModuleNames(): Collection<String> = emptyList()
}