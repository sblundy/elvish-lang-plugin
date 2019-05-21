package com.github.sblundy.elvish

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class ElvishFileType private constructor(): LanguageFileType(ElvishLanguage.INSTANCE) {
    companion object {
        val INSTANCE = ElvishFileType()
    }
    override fun getIcon(): Icon? = icons.ElvishIcons.FILE_ICON

    override fun getName(): String = "Elvish"

    override fun getDefaultExtension(): String = "elv"

    override fun getDescription(): String = "Elvish Script"
}