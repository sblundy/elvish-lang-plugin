package com.github.sblundy.elvish.settings

import com.intellij.openapi.components.BaseState
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.project.Project
import com.intellij.util.messages.Topic
import com.intellij.util.xmlb.annotations.Tag

@State(name = "Elvish")
class ElvishSettings(private val project: Project) : PersistentStateComponent<ElvishSettings.ElvishLanguageOptions> {
    var languageVersion: String?
        get() = state.languageVersion
        set(value) {
            val old = state.languageVersion
            state.languageVersion = value
            fireLanguageVersionChanged(value, old)
        }

    companion object {
        @Topic.ProjectLevel
        val TOPIC = Topic(Listener::class.java)

        @JvmStatic
        fun getInstance(project: Project): ElvishSettings {
            return project.getService(ElvishSettings::class.java)
        }
    }

    private var state = ElvishLanguageOptions()

    override fun getState() = state

    override fun loadState(state: ElvishLanguageOptions) {
        languageVersion = state.languageVersion
    }

    @Tag("language-options")
    class ElvishLanguageOptions: BaseState() {
        var languageVersion by string(null)
    }

    private fun fireLanguageVersionChanged(new: String?, old: String?) {
        if (new == old) {
            return
        }

        project.messageBus.syncPublisher(TOPIC).languageVersionChanged()
    }

    interface Listener {
        fun languageVersionChanged()
    }
}