package com.github.sblundy.elvish.psi

/**
 * Marker interface for function declarations
 */
interface ElvishFunctionDeclaration : ElvishPsiElement {
    fun getCommandName(): ElvishVariableName
}
