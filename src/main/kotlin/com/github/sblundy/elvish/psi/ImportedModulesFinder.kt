package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiElement
import java.lang.UnsupportedOperationException

fun allNamespaceModuleFinder(): ImportedModulesFinder {
    return object : ImportedModulesFinder() {
        override fun toLookupElement(alias: ElvishModuleAlias): LookupElement = toNSLookupElement(alias.variableName.text)
        override fun toLookupElement(spec: ElvishLibModuleSpec): LookupElement = spec.toLookupElement()
        override fun toLookupElement(spec: ElvishRelativeModuleSpec): LookupElement = spec.toLookupElement()
    }
}

fun namespaceModuleFinder(prefix: ElvishNamespaceIdentifier?): ImportedModulesFinder {
    return SubImportedModulesFinder(prefix)
}

abstract class ImportedModulesFinder() : ElvishBlockClimber() {
    val log = logger<ElvishNamespaceVariableReference>()
    val modules = mutableSetOf<LookupElement>()
    override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
        modules += s.topLevelUseCommands().mapNotNull { toLookupElement(it) }
        return false
    }

    override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
        modules += s.chunk.useCommandList.mapNotNull { toLookupElement(it) }
        return true
    }

    override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
        modules += s.chunk.useCommandList.mapNotNull { toLookupElement(it) }
        return true
    }

    private fun toLookupElement(it: ElvishUseCommand): LookupElement? {
        return it.moduleAlias?.let { toNSLookupElement(it.variableName.text) } ?: when (val spec = it.moduleSpec) {
            is ElvishLibModuleSpec -> toLookupElement(spec)
            is ElvishRelativeModuleSpec -> toLookupElement(spec)
            else -> {
                log.warn("unknown type: ${spec.javaClass.name}")
                null
            }
        }
    }

    protected abstract fun toLookupElement(alias: ElvishModuleAlias): LookupElement?
    protected abstract fun toLookupElement(spec: ElvishLibModuleSpec): LookupElement?
    protected abstract fun toLookupElement(spec: ElvishRelativeModuleSpec): LookupElement?
}

private class SubImportedModulesFinder(val prefix: ElvishNamespaceIdentifier?): ImportedModulesFinder() {
    private val prefixMatches: (ns: List<ElvishVariableName>) -> Boolean = when (prefix) {
        null -> { _ -> true }
        is ElvishBuiltinNamespace -> { ns -> filter(ns) }
        is ElvishNamespaceName -> { ns -> filter(prefix, ns) }
        else -> throw UnsupportedOperationException("")
    }

    private val prefixLen = when (prefix) {
        null -> 0
        is ElvishBuiltinNamespace -> 1
        is ElvishNamespaceName -> prefix.variableNameList.size
        else -> throw UnsupportedOperationException("")
    }


    fun filter(ns: List<ElvishVariableName>): Boolean {
        return ns.size > 1 && ns[0].textMatches("builtin")
    }

    fun filter(spec: ElvishNamespaceName, ns: List<ElvishVariableName>): Boolean {
        return ns.size > spec.variableNameList.size &&
                ns.zip(spec.variableNameList.take(ns.size)).all { (a, b) ->
                    a.textMatches(b)
                }
    }

    override fun toLookupElement(alias: ElvishModuleAlias): LookupElement? = null

    override fun toLookupElement(spec: ElvishLibModuleSpec): LookupElement? = if (prefixMatches(spec.variableNameList)) {
        spec.toLookupElement(prefixLen)
    } else {
        null
    }

    override fun toLookupElement(spec: ElvishRelativeModuleSpec): LookupElement? = if (prefixMatches(spec.variableNameList)) {
        spec.toLookupElement(prefixLen)
    } else {
        null
    }
}
