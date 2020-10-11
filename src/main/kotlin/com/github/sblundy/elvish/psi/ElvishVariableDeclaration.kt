package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

/**
 * Marker interface for variable declarations
 */
interface ElvishVariableDeclaration : PsiElement {
    fun nameMatches(name: String, ns: List<String>): Boolean
}
