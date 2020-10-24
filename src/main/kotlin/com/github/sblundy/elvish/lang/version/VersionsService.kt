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
import java.util.*

private val log = logger<VersionsService>()
private val emptyLanguageParseFlagSet = EnumSet.noneOf(LanguageParseFlag::class.java)

data class ElvishLanguageVersion(
    val name: String,
    val builtin: ElvishModule,
    val parseFlags: EnumSet<LanguageParseFlag> = emptyLanguageParseFlagSet,
    val release: Boolean = true
) {
    val builtinFunctions: Set<String>
        get() = builtin.functions.keys
    val builtinVariables: Set<String>
        get() = builtin.variables.keys
    val builtinValues: Set<String>
        get() = builtin.values.keys

    fun isFunctionDeprecated(name: String): Boolean {
        return builtin.functions[name]?.deprecated ?: false
    }

    fun isVariableDeprecated(name: String): Boolean {
        return builtin.variables[name]?.deprecated ?: false
    }

    fun isValueDeprecated(name: String): Boolean {
        return builtin.values[name]?.deprecated ?: false
    }
}

data class ElvishModule(
    val values: Map<String, ElvishValue>,
    val variables: Map<String, ElvishVariable>,
    val functions: Map<String, ElvishFunction>
)

data class ElvishValue(val deprecated: Boolean = false)
data class ElvishVariable(val deprecated: Boolean = false)
data class ElvishFunction(val deprecated: Boolean = false)

enum class LanguageParseFlag {
    UseWithOptionalRename,
    CarrotContinuation
}

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

    val versionNames: List<String>
        get() = versions.map { it.name }

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
    var previous = ElvishLanguageVersion("", ElvishModule(emptyMap(), emptyMap(), emptyMap()), release = false)
    for (def in vd.reversed()) {
        val flags =
            (previous.parseFlags + def.parseFlags.added.map { LanguageParseFlag.valueOf(it) }) - def.parseFlags.removed.map {
                LanguageParseFlag.valueOf(it)
            }
        val version = ElvishLanguageVersion(
            def.name, previous.builtin.applyDiff(def.builtin), if (flags.isEmpty()) {
                emptyLanguageParseFlagSet
            } else {
                EnumSet.copyOf(flags)
            }, def.release
        )
        versions = listOf(version) + versions
        previous = version
    }

    return versions
}

private fun ElvishModule.applyDiff(diff: ModuleDef): ElvishModule {
    var builtinValues = diff.values.added.fold(values.toMutableMap()) { m, name ->
        m[name] = ElvishValue()
        m
    }
    builtinValues = diff.values.deprecated.fold(builtinValues) { m, name ->
        m[name] = ElvishValue(deprecated = true)
        m
    }
    var builtinVariables = diff.variables.added.fold(variables.toMutableMap()) { m, name ->
        m[name] = ElvishVariable()
        m
    }
    builtinVariables = diff.variables.deprecated.fold(builtinVariables) { m, name ->
        m[name] = ElvishVariable(deprecated = true)
        m
    }
    var builtinFunctions = diff.functions.added.fold(functions.toMutableMap()) { m, name ->
        m[name] = ElvishFunction()
        m
    }
    builtinFunctions = diff.functions.deprecated.fold(builtinFunctions) { m, name ->
        m[name] = ElvishFunction(deprecated = true)
        m
    }
    return ElvishModule(
        builtinValues - diff.values.removed,
        builtinVariables - diff.variables.removed,
        builtinFunctions - diff.functions.removed
    )
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

private fun extractChildren(it: URI): List<String> = if (it.scheme == "file") {
    Paths.get(it).toFile().listFiles()?.map { it.readText() }?.toList() ?: emptyList()
} else {
    FileSystems.newFileSystem(it, emptyMap<String, Any>(), null).use { fs ->
        val p = fs.getPath("versions")
        Files.newDirectoryStream(p).map { it.readText() }
    }
}

@Serializable
private data class VersionDef(
    val name: String,
    val previous: String? = null,
    val release: Boolean = true,
    val builtin: ModuleDef = ModuleDef(),
    val parseFlags: VersionChanges = VersionChanges()
)

@Serializable
private data class ModuleDef(
    val values: VersionChanges = VersionChanges(),
    val variables: VersionChanges = VersionChanges(),
    val functions: VersionChanges = VersionChanges()
)

@Serializable
private data class VersionChanges(
    val added: List<String> = emptyList(),
    val deprecated: List<String> = emptyList(),
    val removed: List<String> = emptyList()
)
