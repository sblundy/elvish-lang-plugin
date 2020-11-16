package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

/**
 * Marker interface for function declarations
 */
interface ElvishFunctionDeclaration : PsiElement {
    fun nameMatches(ref: ReferenceWithNamespacePsiElement): Boolean
    fun getCommandName(): ElvishVariableName
}
