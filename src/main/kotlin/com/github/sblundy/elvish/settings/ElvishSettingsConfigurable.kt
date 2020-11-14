package com.github.sblundy.elvish.settings

import com.github.sblundy.elvish.ElvishBundle
import com.github.sblundy.elvish.lang.version.ElvishLanguageVersion
import com.github.sblundy.elvish.lang.version.VersionsService
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.options.ConfigurableBase
import com.intellij.openapi.options.ConfigurableUi
import com.intellij.openapi.options.SearchableConfigurable
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.ColoredListCellRenderer
import com.intellij.ui.SimpleTextAttributes
import com.intellij.ui.components.Label
import com.intellij.ui.layout.*
import javax.swing.DefaultComboBoxModel
import javax.swing.JComponent
import javax.swing.JList

private val DEFAULT_LANGUAGE_VERSION: ElvishLanguageVersion? = null

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
    private val model = DefaultComboBoxModel(VersionsService.getInstance().versions.let {
        listOf(DEFAULT_LANGUAGE_VERSION).plus(it)
    }.toTypedArray())

    private var selectedVersion: ElvishLanguageVersion?
        get() = model.selectedItem as? ElvishLanguageVersion
        set(value) {
            model.selectedItem = value
        }

    override fun reset(settings: ElvishSettings.ElvishLanguageOptions) {
        log.info("in reset($settings)")
        selectedVersion = settings.languageVersion?.let { VersionsService.getInstance().getVersion(it) }
    }

    override fun isModified(settings: ElvishSettings.ElvishLanguageOptions): Boolean {
        log.info("in isModified($settings)/${model.selectedItem}")
        return selectedVersion != settings.languageVersion?.let { VersionsService.getInstance().getVersion(it) }
    }

    override fun apply(settings: ElvishSettings.ElvishLanguageOptions) {
        log.info("in apply($settings)")
        settings.languageVersion = selectedVersion?.name
    }

    override fun getComponent(): JComponent {
        val cb = ComboBox(model)
        cb.renderer = object : ColoredListCellRenderer<ElvishLanguageVersion?>() {
            override fun customizeCellRenderer(list: JList<out ElvishLanguageVersion?>, value: ElvishLanguageVersion?, index: Int, selected: Boolean, hasFocus: Boolean) {
                when {
                    value == null -> {
                        toolTipText = ElvishBundle.message("settings.version.latest.tooltip")
                        append(ElvishBundle.message("settings.version.latest.text"), textAttributes(selected, true))
                    }
                    value.release -> {
                        toolTipText = ElvishBundle.message("settings.version.release.tooltip")
                        append(ElvishBundle.message("settings.version.release.text", value.name), textAttributes(selected, true))
                    }
                    else -> {
                        toolTipText = ElvishBundle.message("settings.version.prerelease.tooltip")
                        append(ElvishBundle.message("settings.version.prerelease.text", value.name), textAttributes(selected, false))
                    }
                }
            }
            fun textAttributes(selected: Boolean, release: Boolean): SimpleTextAttributes {
                return when {
                    selected -> SimpleTextAttributes.SELECTED_SIMPLE_CELL_ATTRIBUTES
                    release -> SimpleTextAttributes.SIMPLE_CELL_ATTRIBUTES
                    else -> SimpleTextAttributes.merge(SimpleTextAttributes.SIMPLE_CELL_ATTRIBUTES, SimpleTextAttributes.DARK_TEXT)
                }
            }
        }
        return panel {
            row {
                cell {
                    Label(ElvishBundle.message("settings.version.label"))()
                    cb()
                }
            }
        }
    }
}