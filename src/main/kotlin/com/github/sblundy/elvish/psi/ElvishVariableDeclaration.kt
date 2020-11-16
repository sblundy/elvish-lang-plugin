package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

/**
 * Marker interface for variable declarations
 */
interface ElvishVariableDeclaration : PsiElement {
    fun nameMatches(ref:ReferenceWithNamespacePsiElement): Boolean
    fun getVariableName(): ElvishVariableName
}
