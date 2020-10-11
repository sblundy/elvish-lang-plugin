package com.github.sblundy.elvish.lang.version

import com.intellij.openapi.application.PluginPathManager
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.diagnostic.logger
import com.intellij.util.PathUtil
import com.intellij.util.io.readText
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.File
import java.net.URI
import java.nio.file.*

private val log = logger<VersionsService>()

data class ElvishLanguageVersion(val name: String, val builtin: ElvishModule, val release: Boolean = true) {
    val builtinFunctions: Set<String>
        get() = builtin.functions
    val builtinVariables: Set<String>
        get() = builtin.variables
}

data class ElvishModule(val variables: Set<String>, val functions: Set<String>)

class VersionsService {
    companion object {
        @JvmStatic
        fun getInstance(): VersionsService {
            return ServiceManager.getService(VersionsService::class.java)
        }
    }

    private val versions: List<ElvishLanguageVersion> by lazy { loadVersions() }

    internal val latestRelease: ElvishLanguageVersion?
        get() = versions.find { it.release }

    val versionNames = versions.map { it.name }

    fun getVersion(name: String): ElvishLanguageVersion? = versions.find { it.name == name }
}

private fun loadVersions(): List<ElvishLanguageVersion> {
    log.info("in loadVersions")
    val versions = versionDefs()?.let { convertDefsToVersions(it) } ?: emptyList()
    log.info("end loadVersions: $versions")
    return versions
}

private fun convertDefsToVersions(defs: Sequence<VersionDef>): List<ElvishLanguageVersion> {
    val vd = orderVersionDefs(defs)
    //TODO make this more resistant to non-linear version trees
    var versions = listOf<ElvishLanguageVersion>()
    var previous = ElvishLanguageVersion("", ElvishModule(emptySet(), emptySet()), false)
    for (def in vd.reversed()) {
        val builtinVariables = (previous.builtinVariables + def.builtin.variables.added) - def.builtin.variables.removed
        val builtinFunctions = (previous.builtinFunctions + def.builtin.functions.added) - def.builtin.functions.removed
        val version = ElvishLanguageVersion(def.name, ElvishModule(builtinVariables, builtinFunctions), def.release)
        versions = listOf(version) + versions
        previous = version
    }

    return versions
}

private fun orderVersionDefs(it: Sequence<VersionDef>): List<VersionDef> {
    val v = it.toMutableList()
    log.info("in orderedVersionDefs: ${v.size}")
    var p: String? = null

    for (index in v.size - 1 downTo 1) {
        for (i in 0 until index) {
            if (v[i].previous == p) {
                val tmp = v[i]
                v[i] = v[index]
                v[index] = tmp
                p = tmp.name
                break
            }
        }
    }

    return v.toList()
}

private fun versionDefs(): Sequence<VersionDef>? {
    val defs = versionsDefsFromPluginDevDir() ?: versionsDefsFromPluginDir() ?: versionsDefsFromPluginJar()
    return defs?.map {
        log.debug("decoding $it")
        Json.decodeFromString(it)
    }
}

private const val pluginDirName = "elvish-lang-plugin"
private fun versionsDefsFromPluginDevDir(): Sequence<String>? {
    val extraDir = System.getProperty("idea.external.build.development.plugins.dir")
    return extraDir?.let {
        val extraDirFile = File(extraDir, pluginDirName)
        if (extraDirFile.isDirectory) {
            extraDirFile.listFiles()?.asSequence()?.map { it.readText() }
        } else {
            null
        }
    }
}

private fun versionsDefsFromPluginDir(): Sequence<String>? {
    val jarPath = PathUtil.getJarPathForClass(VersionsService::class.java)
    val pluginBaseDir = if (jarPath.endsWith(".jar")) {
        File(jarPath).parentFile.parentFile
    } else {
        PluginPathManager.getPluginHome(pluginDirName)
    }
    val versions = File(pluginBaseDir, "versions")
    if (versions.exists()) {
        log.debug("versions=${versions.absolutePath}")
        return versions.listFiles()?.asSequence()?.map { it.readText() }
    }

    return null
}

private fun versionsDefsFromPluginJar(): Sequence<String>? {
    val resources = VersionsService::class.java.classLoader.getResources("versions")
    return resources?.asSequence()?.flatMap { extractChildren(it.toURI()) }
}

private fun extractChildren(it: URI): List<String> =
    FileSystems.newFileSystem(it, emptyMap<String, Any>(), null).use { fs ->
        val p = fs.getPath("versions")
        Files.newDirectoryStream(p).map { it.readText() }
    }

@Serializable
private data class VersionDef(
    val name: String,
    val previous: String? = null,
    val release: Boolean = true,
    val builtin: ModuleDef = ModuleDef()
)
@Serializable
private data class ModuleDef(val variables: VersionChanges = VersionChanges(), val functions: VersionChanges = VersionChanges())

@Serializable
private data class VersionChanges(val added: List<String> = emptyList(), val removed: List<String> = emptyList())
