package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.version.ElvishBundledService
import com.github.sblundy.elvish.lang.version.ElvishLanguageVersion
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.FakePsiElement

private val scopeKey = Key.create<BuiltinScope>("ElvishPsiUtils.BuiltinScope")

internal class BuiltinScope(val version: ElvishLanguageVersion, manager: PsiManager) : FakePsiElement(),
    ElvishLexicalScope {
    private val values = version.builtinValues.map { ElvishPsiBuiltinValue(it, manager) }
    private val variables = version.builtinVariables.map { ElvishPsiBuiltinVariable(it, manager) }
    private val functions = version.builtinFunctions.map { ElvishPsiBuiltinCommand(it, manager) }

    fun findVariables(name: PsiElement?): Collection<ElvishVariableDeclaration> = name?.let {
        (values + variables).filter { it.textMatches(name) }
    } ?: (values + variables)

    fun findVariables(ns: ElvishNamespaceName, name: PsiElement?): Collection<ElvishVariableDeclaration> =
        if (!(ns.variableNameList.size == 1 && ns.variableNameList[0].textMatches("builtin"))) {
            emptyList()
        } else {
            findVariables(name)
        }

    fun findFnCommands(name: PsiElement?): Collection<ElvishFunctionDeclaration> =
        name?.let {functions.filter { it.textMatches(name) } }?: functions

    fun findFnCommands(ns: ElvishNamespaceName, name: PsiElement?): Collection<ElvishFunctionDeclaration> =
        if (!(ns.variableNameList.size == 1 && ns.variableNameList[0].textMatches("builtin"))) {
            emptyList()
        } else {
            findFnCommands(name)
        }

    override fun getParent(): PsiElement? = null
}

internal fun Project.getBuiltinScope(): BuiltinScope? {
    var scope = getUserData(scopeKey)
    val projectVersion = ElvishBundledService.getInstance().currentVersion(this)
    return if (scope != null && projectVersion == scope.version) {
        scope
    } else if (projectVersion != null){
        scope = BuiltinScope(projectVersion, PsiManager.getInstance(this))
        putUserData(scopeKey, scope)
        scope
    } else {
        null
    }
}
