package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.version.ElvishBundledService
import com.github.sblundy.elvish.lang.version.ElvishLanguageVersion
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.psi.PsiManager

private val scopeKey = Key.create<BuiltinScope>("ElvishPsiUtils.BuiltinScope")

internal class BuiltinScope(val version: ElvishLanguageVersion, manager: PsiManager) : ElvishDeclarationScope {
    private val variables = version.builtinVariables.map { ElvishPsiBuiltinVariable(it, manager) }
    private val functions = version.builtinFunctions.map { ElvishPsiBuiltinCommand(it, manager) }

    override fun findVariables(name: String, ns: List<String>): Collection<ElvishVariableDeclaration> =
        variables.filter { it.nameMatches(name, ns) }

    override fun processVariables(processor: ElvishVariableScope.VariableProcessor) =
        variables.forEach { processor.process(it) }

    override fun findFnCommands(name: String, ns: List<String>): Collection<ElvishFunctionDeclaration> =
        functions.filter { it.nameMatches(name, ns) }

    override fun processFnCommands(processor: ElvishFunctionScope.FnCommandProcessor) =
        functions.forEach { processor.process(it) }
}

internal fun Project.getBuiltinScope(): BuiltinScope? {
    val scope = getUserData(scopeKey)
    val projectVersion = ElvishBundledService.getInstance().currentVersion(this)
    return if (scope != null && projectVersion == scope.version) { scope } else { null }
}

internal fun Project.putBuiltinScope(scope: BuiltinScope) {
    putUserData(scopeKey, scope)
}