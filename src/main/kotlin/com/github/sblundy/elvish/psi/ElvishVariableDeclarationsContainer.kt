package com.github.sblundy.elvish.psi

interface ElvishVariableDeclarationsContainer: ElvishPsiElement {
    val variableDeclarations: List<ElvishVariableDeclaration>
}