package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.ElvishModule
import com.github.sblundy.elvish.lang.ModuleManager
import com.intellij.psi.PsiElement

const val editNSName = "edit"
const val builtinNSName = "builtin"

fun ElvishUseCommand.matches(ns: ElvishNamespaceName): Boolean {
    return moduleAlias?.matches(ns)?:when (val spec = moduleSpec) {
        is ElvishLibModuleSpec -> spec.matches(ns)
        is ElvishRelativeModuleSpec -> spec.matches(ns)
        else -> throw UnsupportedOperationException("Spec type not recognized:"+spec.javaClass.name)
    }
}

fun ElvishLibModuleSpec.matches(ns: ElvishNamespaceName): Boolean {
    return variableNameList.size == ns.variableNameList.size && variableNameList.zip(ns.variableNameList).all { (s, n) -> s.textMatches(n)}
}

fun ElvishRelativeModuleSpec.matches(ns: ElvishNamespaceName): Boolean {
    return variableNameList.size == ns.variableNameList.size && variableNameList.zip(ns.variableNameList).all { (s, n) -> s.textMatches(n)}
}

fun ElvishModuleAlias.matches(ns: ElvishNamespaceName): Boolean {
    return ns.variableNameList.size == 1 && textMatches(ns.variableNameList[0])
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

fun ElvishBuiltinNamespace.isInScope(): Boolean = true
fun ElvishEnvVarNamespace.isInScope(): Boolean = true
fun ElvishExternalsNamespace.isInScope(): Boolean = true
fun ElvishLocalNamespace.isInScope(): Boolean = true
fun ElvishUpNamespace.isInScope(): Boolean = true
fun ElvishNamespaceName.isInScope(): Boolean = (variableNameList.isNotEmpty() && variableNameList[0].textMatches(editNSName)) || this.resolveUseCommand() != null

fun ElvishNamespaceName.resolveUseCommand(): ElvishUseCommand? {
    val ns = this
    val useCommandAccum = object: ElvishBlockClimber() {
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

    useCommandAccum.climb(this.parent as ElvishPsiElement)
    return useCommandAccum.useCommand
}

fun ElvishBuiltinNamespace.resolveModule(): ElvishModule? = this.resolveBuiltinScope()

internal fun ElvishBuiltinNamespace.resolveBuiltinScope(): BuiltinScope? {
    return this.project.getBuiltinScope()
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