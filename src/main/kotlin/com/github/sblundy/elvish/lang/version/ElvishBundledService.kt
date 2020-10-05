package com.github.sblundy.elvish.lang.version

import com.github.sblundy.elvish.settings.ElvishSettings
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project

class ElvishBundledService {
    companion object {
        @JvmStatic
        fun getInstance(): ElvishBundledService {
            return ServiceManager.getService(ElvishBundledService::class.java)
        }
    }

    fun builtins(project: Project): Set<String> {
        val settings = ElvishSettings.getInstance(project)
        val vs = VersionsService.getInstance()
        //TODO warning on unknown version?
        val v = settings.state.languageVersion?.let { vs.getVersion(it) ?: vs.latestRelease } ?: vs.latestRelease
        return v?.let { v.builtinFunctions } ?: emptySet()
    }
}