package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.ElvishModule
import com.github.sblundy.elvish.lang.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

class NamespaceModuleFinder(private val ns: ElvishNamespaceName, private val project: Project): ElvishScopeClimber() {
    val declarations = mutableListOf<ElvishModule?>()

    override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
        val d = when (s) {
            is ElvishFile -> {
                val m = s.matchingUseCommands()
                if (m.isNotEmpty()) {
                    val mgr = ModuleManager.getInstance(project)
                    m.map {
                        when (val spec = it.moduleSpec) {
                            is ElvishLibModuleSpec -> mgr.findModule(spec)
                            is ElvishRelativeModuleSpec -> mgr.findModule(s, spec)
                            else -> null
                        }
                    }
                } else if (ns.variableNameList.isNotEmpty() && ns.variableNameList[0].textMatches("edit")) {
                    project.getBuiltinScope()?.findModule(ns)?.let {listOf(it)}?: emptyList()
                } else {
                    emptyList()
                }
            }
            is ElvishFnCommand -> {
                findMod(s.matchingUseCommands(), s.containingFile as ElvishFile)
            }
            is ElvishLambdaBlock -> {
                val file = (s as ElvishLambdaBlock).containingFile
                findMod(s.matchingUseCommands(), file as ElvishFile)
            }
            else -> emptyList()
        }
        if (d.isNotEmpty()) {
            declarations += d
            return false
        }
        return true
    }

    private fun ElvishFile.matchingUseCommands(): Collection<ElvishUseCommand> {
        return topLevelUseCommands().filter { it.matches(ns) }
    }

    private fun ElvishFnCommand.matchingUseCommands(): Collection<ElvishUseCommand> {
        return chunk.useCommandList.filter { it.matches(ns) }
    }

    private fun ElvishLambdaBlock.matchingUseCommands(): Collection<ElvishUseCommand> {
        return chunk.useCommandList.filter { it.matches(ns) }
    }

    private fun findMod(m :Collection<ElvishUseCommand>, file: ElvishFile): List<ElvishModule?> {
        return if (m.isNotEmpty()) {
            val mgr = ModuleManager.getInstance(project)
            m.map {
                when(val spec = it.moduleSpec) {
                    is ElvishLibModuleSpec -> mgr.findModule(spec)
                    is ElvishRelativeModuleSpec -> mgr.findModule(file, spec)
                    else -> null
                }
            }
        } else {
            emptyList()
        }
    }
}

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
