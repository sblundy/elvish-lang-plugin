package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.*
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiManager

class ModuleManager(val project: Project) {
    companion object {
        @JvmStatic
        fun getInstance(project: Project): ModuleManager {
            return ServiceManager.getService(project, ModuleManager::class.java)
        }
    }

    fun findModule(file: ElvishFile, ns: ElvishRelativeModuleSpec): ElvishModule? {
        val rawFile = ns.text + ".elv"
        val dir = file.containingDirectory?:file.originalFile.containingDirectory
        val (base, path) = if (rawFile.startsWith("../")) {
            Pair(dir?.parentDirectory, rawFile.substring(3))
        } else {
            Pair(dir, rawFile.substring(2))
        }
        if (base == null) {
            return null
        }

        val vf = VfsUtil.findRelativeFile(base.virtualFile, *path.split(":").toTypedArray()) ?: return null
        return PsiManager.getInstance(project).findFile(vf) as? ElvishFile
    }

    fun findModule(ns: ElvishLibModuleSpec): ElvishModule? {
        val rawPath = ns.text.split(":")

        val vf = ElvishGlobalLibraryManager.getInstance().libraryFile(rawPath) ?: return findBuiltinModule(ns)
        return PsiManager.getInstance(project).findFile(vf) as? ElvishFile
    }

    fun findBuiltinModule(ns: ElvishLibModuleSpec): ElvishModule? {
        return ns.project.getBuiltinScope()?.findModule(ns.variableNameList)
    }
}