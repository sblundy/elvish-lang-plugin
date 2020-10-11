package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

/**
 * Marker interface for function declarations
 */
interface ElvishFunctionDeclaration : PsiElement {
    fun nameMatches(name: String, ns: List<String>): Boolean
}
