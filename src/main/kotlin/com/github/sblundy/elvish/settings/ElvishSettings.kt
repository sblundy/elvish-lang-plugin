package com.github.sblundy.elvish.settings

import com.intellij.openapi.components.BaseState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.annotations.Tag

@State(name = "Elvish")
class ElvishSettings : PersistentStateComponent<ElvishSettings.ElvishLanguageOptions> {
    companion object {
        @JvmStatic
        fun getInstance(project: Project): ElvishSettings {
            return ServiceManager.getService(project, ElvishSettings::class.java)
        }
    }
    private var state = ElvishLanguageOptions()

    override fun getState() = state

    override fun loadState(state: ElvishLanguageOptions) {
        this.state = state
    }

    @Tag("language-options")
    class ElvishLanguageOptions: BaseState() {
        var languageVersion by string(null)
    }
}