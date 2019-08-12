package com.github.sblundy.elvish.style

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.application.options.IndentOptionsEditor
import com.intellij.psi.codeStyle.*

class ElvishLanguageCodeStyleSettingsProvider: LanguageCodeStyleSettingsProvider() {
    override fun getCodeSample(settingsType: SettingsType): String = codeSample
    override fun getLanguage() = ElvishLanguage.INSTANCE

    override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings? {
        return ElvishCodeStyleSettings(settings)
    }

    override fun createConfigurable(
        settings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ): CodeStyleConfigurable {
        return object : CodeStyleAbstractConfigurable(settings, modelSettings, language.id) {
            override fun createPanel(settings: CodeStyleSettings?): CodeStyleAbstractPanel {
                return ElvishCodeStylePanel(settings, modelSettings)
            }
        }
    }

    override fun getIndentOptionsEditor(): IndentOptionsEditor? {
        return IndentOptionsEditor(this)
    }

    override fun customizeDefaults(
        commonSettings: CommonCodeStyleSettings,
        indentOptions: CommonCodeStyleSettings.IndentOptions
    ) {
        indentOptions.INDENT_SIZE = 2
        indentOptions.TAB_SIZE = 2
    }
}

private val codeSample = """
    fn named []{
      if (command arg | filter) {
        echo text
      }
    }
""".trimIndent()