package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.ElvishModule
import com.github.sblundy.elvish.lang.ModuleManager
import com.github.sblundy.elvish.lang.currentVersionParseFlags
import com.github.sblundy.elvish.lang.version.LanguageParseFlag
import com.intellij.psi.PsiElement

const val editNSName = "edit"
const val builtinNSName = "builtin"

fun ElvishUseCommand.matches(ns: ElvishNamespaceIdentifier): Boolean {
    return moduleAlias?.matches(ns)?:when (val spec = moduleSpec) {
        is ElvishLibModuleSpec -> spec.matches(ns)
        is ElvishRelativeModuleSpec -> spec.matches(ns)
        else -> throw UnsupportedOperationException("Spec type not recognized:"+spec.javaClass.name)
    }
}

fun ElvishLibModuleSpec.matches(ns: ElvishNamespaceIdentifier): Boolean {
    return when (ns) {
        is ElvishNamespaceName -> variableNameList.size == ns.variableNameList.size && variableNameList.zip(ns.variableNameList).all { (s, n) -> s.textMatches(n)}
        is ElvishBuiltinNamespace -> variableNameList.size == 1 && variableNameList.firstOrNull()?.textMatches(builtinNSName)?:false
        else -> false
    }
}

fun ElvishRelativeModuleSpec.matches(ns: ElvishNamespaceIdentifier): Boolean {
    return when (ns) {
        is ElvishNamespaceName -> variableNameList.size == ns.variableNameList.size && variableNameList.zip(ns.variableNameList).all { (s, n) -> s.textMatches(n)}
        else -> false
    }
}

fun ElvishModuleAlias.matches(ns: ElvishNamespaceIdentifier): Boolean {
    return when (ns) {
        is ElvishNamespaceName -> ns.variableNameList.size == 1 && textMatches(ns.variableNameList[0])
        else -> false
    }
}

fun ElvishNamespaceIdentifier.isInScope(): Boolean {
    return when(this) {
        is ElvishBuiltinNamespace -> this.isInScope()
        is ElvishEnvVarNamespace -> this.isInScope()
        is ElvishExternalsNamespace -> this.isInScope()
        is ElvishLocalNamespace -> this.isInScope()
        is ElvishUpNamespace -> this.isInScope()
        is ElvishNamespaceName -> this.isInScope()
        else -> false
    }
}

fun ElvishBuiltinNamespace.isInScope(): Boolean =
    !project.currentVersionParseFlags().contains(LanguageParseFlag.BuiltinRequiresExplicitUse) or
            (this.resolveUseCommand() != null)

fun ElvishEnvVarNamespace.isInScope(): Boolean = true
fun ElvishExternalsNamespace.isInScope(): Boolean = true
fun ElvishLocalNamespace.isInScope(): Boolean = true
fun ElvishUpNamespace.isInScope(): Boolean = true
fun ElvishNamespaceName.isInScope(): Boolean = (variableNameList.isNotEmpty() && variableNameList[0].textMatches(editNSName)) || this.resolveUseCommand() != null

fun ElvishNamespaceName.resolveUseCommand(): ElvishUseCommand? {
    val useCommandAccum = UseCommandFinder(this)
    useCommandAccum.climb(this.parent as ElvishPsiElement)
    return useCommandAccum.useCommand
}

fun ElvishBuiltinNamespace.resolveUseCommand(): ElvishUseCommand? {
    val useCommandAccum = UseCommandFinder(this)
    useCommandAccum.climb(this.parent as ElvishPsiElement)
    return useCommandAccum.useCommand
}

internal class UseCommandFinder(private val ns: ElvishNamespaceIdentifier) : ElvishBlockClimber() {
    var useCommand: ElvishUseCommand? = null
    override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
        useCommand = s.chunk.useCommandList.find { it.matches(ns) }
        return useCommand == null
    }

    override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
        useCommand = s.chunk.useCommandList.find { it.matches(ns) }
        return useCommand == null
    }

    override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
        useCommand = s.chunk.useCommandList.find { it.matches(ns) }
        return useCommand == null
    }
}

fun ElvishBuiltinNamespace.resolveModule(): ElvishModule? = this.resolveBuiltinScope()

internal fun ElvishBuiltinNamespace.resolveBuiltinScope(): BuiltinScope? {
    return if (this.isInScope()) { this.project.getBuiltinScope() } else { null }
}

fun ElvishNamespaceName.resolveModule(): ElvishModule? {
    if (variableNameList.isNotEmpty() && variableNameList[0].textMatches(editNSName)) {
        return project.getBuiltinScope()?.findModule(this)
    }
    val cmd = resolveUseCommand()
    return cmd?.let {
        val mgr = ModuleManager.getInstance(project)
        when (val spec = cmd.moduleSpec) {
            is ElvishLibModuleSpec -> mgr.findModule(spec)
            is ElvishRelativeModuleSpec -> mgr.findModule(this.containingFile as ElvishFile, spec)
            else -> null
        }
    }
}