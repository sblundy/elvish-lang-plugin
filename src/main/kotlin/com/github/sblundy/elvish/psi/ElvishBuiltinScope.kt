package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.BaseScopeHolder
import com.github.sblundy.elvish.lang.ElvishModule
import com.github.sblundy.elvish.lang.version.ElvishLanguageVersion
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.AtomicNotNullLazyValue
import com.intellij.openapi.util.NotNullLazyValue
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.FakePsiElement

internal class BuiltinScope(val version: ElvishLanguageVersion, mgr: PsiManager) : FakePsiElement(),
    ElvishLexicalScope, ElvishModule {
    private val values = version.builtinValues.map { ElvishPsiBuiltinValue(it, mgr, this) }
    private val variables = version.builtinVariables.map { ElvishPsiBuiltinVariable(it, mgr, this) }
    private val functions = version.builtinFunctions.map { ElvishPsiBuiltinCommand(it, mgr, this) }

    private val modules: Map<List<String>, NotNullLazyValue<BuiltinModule>> = buildLazyMap(version.builtin, mgr).filter { it.first.isNotEmpty() }.toMap()
    private val baseModuleNames = modules.keys.fold(mutableSetOf<String>()) {acc, k -> acc.add(k.first()); acc}

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

    fun findModule(ns: ElvishNamespaceName): ElvishModule? = findModule(ns.variableNameList)

    fun findModule(ns: List<ElvishVariableName>): ElvishModule? {
        val base = ns.first().text
        if (base !in baseModuleNames) {
            return null
        }

        val key = listOf(base) + ns.drop(1).map { it.text }

        return modules[key]?.value
    }

    override fun getParent(): PsiElement? = null
    override fun getScope(): ElvishLexicalScope? = null
    override fun exportedVariables(): Collection<ElvishVariableDeclaration> = (values + variables)
    override fun exportedFunctions(): Collection<ElvishFunctionDeclaration> = functions

    internal class BuiltinModule(m: com.github.sblundy.elvish.lang.version.ElvishModule, mgr: PsiManager) : FakePsiElement(),
        ElvishModule,
        ElvishLexicalScope {
        private val values = m.values.keys.map { ElvishPsiBuiltinValue(it, mgr, this) }
        private val variables = m.variables.keys.map { ElvishPsiBuiltinVariable(it, mgr, this) }
        private val functions = m.functions.keys.map { ElvishPsiBuiltinCommand(it, mgr, this) }
        private val childNames = m.modules.keys
        override fun exportedVariables(): Collection<ElvishVariableDeclaration> {
            return variables + values
        }

        override fun exportedFunctions(): Collection<ElvishFunctionDeclaration> {
            return functions
        }

        override fun childModuleNames(): Collection<String> = childNames

        override fun getParent(): PsiElement? = null

        override fun getScope(): ElvishLexicalScope? = null
    }

}

internal fun Project.getBuiltinScope(): BuiltinScope? {
    return BaseScopeHolder.getInstance(this).builtinScope()
}

private fun buildLazyMap(base: com.github.sblundy.elvish.lang.version.ElvishModule, mgr: PsiManager): List<Pair<List<String>, NotNullLazyValue<BuiltinScope.BuiltinModule>>> {
    val children = base.modules.flatMap {(folder:String, m) ->
        buildLazyMap(m, mgr).map { (path:List<String>, b:NotNullLazyValue<BuiltinScope.BuiltinModule>) ->
            Pair(listOf(folder) + path, b)
        }
    }
    return (children + Pair(listOf(), AtomicNotNullLazyValue.createValue { BuiltinScope.BuiltinModule(base, mgr) }))
}