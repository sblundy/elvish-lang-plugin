package com.github.sblundy.elvish.lang

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile

class ElvishGlobalLibraryManager {
    private val userLibDir = VfsUtil.getUserHomeDir()?.findFileByRelativePath(".elvish/lib")

    companion object {
        @JvmStatic
        fun getInstance(): ElvishGlobalLibraryManager {
            return ApplicationManager.getApplication().getService(ElvishGlobalLibraryManager::class.java)
        }
    }

    val rootLibraries: Collection<AvailableLib>
        get() = availableLibs()?: emptyList()

    val rootFolders: Collection<String>
        get() = userLibDir?.let { userLibDir ->
            VfsUtil.getChildren(userLibDir) {
                it.isDirectory
            }.map {
                it.name
            }
        } ?: emptyList()

    fun availableLibs(vararg path: String):Collection<AvailableLib>? {
        return userLibDir?.let { userLibDir ->
            val dir = path.fold(userLibDir) {acc:VirtualFile?, folder:String -> acc?.findChild(folder) }?:return null
            if (!dir.isDirectory) {
                return null
            }
            VfsUtil.getChildren(dir) {
                !it.isDirectory && it.extension == "elv"
            }.map {
                AvailableLib(it.nameWithoutExtension, path.toList())
            }
        }
    }

    fun childFolders(vararg path: String):Collection<String>? {
        val dir = path.fold(userLibDir) {acc:VirtualFile?, folder:String -> acc?.findChild(folder) }?:return null
        if (!dir.isDirectory) {
            return null
        }
        return VfsUtil.getChildren(dir) {
            it.isDirectory
        }.map {
            it.name
        }
    }

    fun libraryFile(lib: AvailableLib): VirtualFile? {
        return userLibDir?.let { userLibDir ->VfsUtil.findRelativeFile(userLibDir, *lib.fileName().toTypedArray()) }
    }

    fun libraryFile(path: List<String>): VirtualFile? {
        if (path.isEmpty()) {
            throw IllegalArgumentException("library path is empty")
        }

        val folders = path.dropLast(1)
        val filename = path.last() + ".elv"
        val file = (folders + filename).flatMap { it.split("/") }
        return userLibDir?.let { userLibDir ->VfsUtil.findRelativeFile(userLibDir, *file.toTypedArray()) }
    }

    data class AvailableLib(val name: String, val path: List<String>) {
        fun fileName(): List<String> {
            return path + "$name.elv"
        }
    }
}