package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.lang.version.ElvishBundledService
import com.github.sblundy.elvish.psi.BuiltinScope
import com.github.sblundy.elvish.settings.ElvishSettings
import com.intellij.openapi.Disposable
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager

class BaseScopeHolder(private val project: Project) : Disposable {
    private var builtinScope: BuiltinScope? = null

    companion object {
        @JvmStatic
        fun getInstance(project: Project): BaseScopeHolder {
            return ServiceManager.getService(project, BaseScopeHolder::class.java)
        }
    }

    init {
        project.messageBus.connect(this).subscribe(ElvishSettings.TOPIC, object : ElvishSettings.Listener {
            override fun languageVersionChanged() {
                builtinScope = null
            }
        })
    }

    internal fun builtinScope(): BuiltinScope? {
        ElvishBundledService.getInstance().currentVersion(project)?.let { projectVersion ->
            if (builtinScope?.version != projectVersion) {
                builtinScope = BuiltinScope(projectVersion, PsiManager.getInstance(project))
            }
        } ?: return null
        return builtinScope
    }

    override fun dispose() {}
}