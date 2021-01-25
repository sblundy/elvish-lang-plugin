package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.lang.ElvishModule
import com.github.sblundy.elvish.lang.ModuleManager
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement

class NamespaceModuleFinder(private val ns: ElvishNamespaceName, private val project: Project): ElvishBlockClimber() {
    val declarations = mutableListOf<ElvishModule>()

    override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
        val m = s.matchingUseCommands()
        if (m.isNotEmpty()) {
            val mgr = ModuleManager.getInstance(project)
            m.map { cmd ->
                when (val spec = cmd.moduleSpec) {
                    is ElvishLibModuleSpec -> {
                        mgr.findModule(spec)?.let {declarations += it; return false}
                    }
                    is ElvishRelativeModuleSpec -> {
                        mgr.findModule(s, spec)?.let {declarations += it; return false}
                    }
                    else -> {}
                }
            }
        } else if (ns.variableNameList.isNotEmpty() && ns.variableNameList[0].textMatches("edit")) {
            project.getBuiltinScope()?.findModule(ns)?.let {declarations += it; return false}
        }

        return super.visitElvishFile(s, ctxt)
    }

    override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
        val d = findMod(s.matchingUseCommands(), s.containingFile as ElvishFile).filterNotNull()

        if (d.isNotEmpty()) {
            declarations += d
            return false
        }
        return super.visitLambdaScope(s, ctxt)
    }

    override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
        val file = s.containingFile
        val d = findMod(s.matchingUseCommands(), file as ElvishFile).filterNotNull()
        if (d.isNotEmpty()) {
            declarations += d
            return false
        }
        return super.visitChunkBlock(s, ctxt)
    }

    private fun ElvishFile.matchingUseCommands(): Collection<ElvishUseCommand> {
        return chunk.useCommandList.filter { it.matches(ns) }
    }

    private fun ElvishLambdaScope.matchingUseCommands(): Collection<ElvishUseCommand> {
        return chunk.useCommandList.filter { it.matches(ns) }
    }

    private fun ElvishChunkBlock.matchingUseCommands(): Collection<ElvishUseCommand> {
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
