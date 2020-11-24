package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.BaseScopeHolder
import com.github.sblundy.elvish.lang.version.ElvishLanguageVersion
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.FakePsiElement

internal class BuiltinScope(val version: ElvishLanguageVersion, manager: PsiManager) : FakePsiElement(),
    ElvishLexicalScope {
    private val values = version.builtinValues.map { ElvishPsiBuiltinValue(it, manager, this) }
    private val variables = version.builtinVariables.map { ElvishPsiBuiltinVariable(it, manager, this) }
    private val functions = version.builtinFunctions.map { ElvishPsiBuiltinCommand(it, manager, this) }

    fun findVariables(name: PsiElement?): Collection<ElvishVariableDeclaration> = name?.let {
        (values + variables).filter { it.textMatches(name) }
    } ?: (values + variables)

    fun findVariables(ns: ElvishNamespaceIdentifier, name: PsiElement?): Collection<ElvishVariableDeclaration> =
        if (ns !is ElvishBuiltinNamespace) {
            emptyList()
        } else {
            findVariables(name)
        }

    fun findFnCommands(name: PsiElement?): Collection<ElvishFunctionDeclaration> =
        name?.let {functions.filter { it.textMatches(name) } }?: functions

    fun findFnCommands(ns: ElvishNamespaceIdentifier, name: PsiElement?): Collection<ElvishFunctionDeclaration> =
        if (ns !is ElvishBuiltinNamespace) {
            emptyList()
        } else {
            findFnCommands(name)
        }

    override fun getParent(): PsiElement? = null
    override fun getScope(): ElvishLexicalScope? = null
}

internal fun Project.getBuiltinScope(): BuiltinScope? {
    return BaseScopeHolder.getInstance(this).builtinScope()
}
