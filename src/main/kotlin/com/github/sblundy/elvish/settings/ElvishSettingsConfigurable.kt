package com.github.sblundy.elvish.settings

import com.github.sblundy.elvish.lang.version.VersionsService
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.options.ConfigurableBase
import com.intellij.openapi.options.ConfigurableUi
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.components.Label
import com.intellij.ui.layout.*
import javax.swing.DefaultComboBoxModel
import javax.swing.JComponent

private const val DEFAULT_LANGUAGE_VERSION = "latest"

class ElvishSettingsConfigurable internal constructor(private val project: Project) :
    ConfigurableBase<ElvishSettingsConfigurableUi, ElvishSettings.ElvishLanguageOptions>("elvish", "Elvish", null),
    SearchableConfigurable {
    override fun getSettings(): ElvishSettings.ElvishLanguageOptions {
        return ElvishSettings.getInstance(project).state
    }

    override fun createUi(): ElvishSettingsConfigurableUi = ElvishSettingsConfigurableUi()
}

class ElvishSettingsConfigurableUi : ConfigurableUi<ElvishSettings.ElvishLanguageOptions> {
    private val log = logger<ElvishSettingsConfigurableUi>()
    private val model = DefaultComboBoxModel(VersionsService.getInstance().versionNames.let {
        listOf<String?>(DEFAULT_LANGUAGE_VERSION).plus(it)
    }.toTypedArray())

    override fun reset(settings: ElvishSettings.ElvishLanguageOptions) {
        log.info("in reset($settings)")
        model.selectedItem = settings.languageVersion ?: DEFAULT_LANGUAGE_VERSION
    }

    override fun isModified(settings: ElvishSettings.ElvishLanguageOptions): Boolean {
        log.info("in isModified($settings)/${model.selectedItem}")
        return model.selectedItem != settings.languageVersion ?: DEFAULT_LANGUAGE_VERSION
    }

    override fun apply(settings: ElvishSettings.ElvishLanguageOptions) {
        log.info("in apply($settings)")
        settings.languageVersion = if (model.selectedItem == DEFAULT_LANGUAGE_VERSION) {
            null
        } else {
            model.selectedItem as String?
        }
    }

    override fun getComponent(): JComponent {
        val cb = ComboBox(model)
        return panel {
            row {
                cell {
                    Label("Elvish Release Version")()
                    cb()
                }
            }
        }
    }
}