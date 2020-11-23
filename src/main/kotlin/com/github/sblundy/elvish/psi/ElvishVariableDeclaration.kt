package com.github.sblundy.elvish.psi

/**
 * Marker interface for variable declarations
 */
interface ElvishVariableDeclaration : ElvishPsiElement {
    fun getVariableName(): ElvishVariableName
}
