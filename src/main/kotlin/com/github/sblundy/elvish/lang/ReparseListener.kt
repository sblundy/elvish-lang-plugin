package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.impl.PsiManagerEx

class ReparseListener(private val project: Project): com.github.sblundy.elvish.settings.ElvishSettings.Listener {
    override fun languageVersionChanged() {
        val files = PsiManagerEx.getInstanceEx(project).fileManager.allCachedFiles.filter {
            it.language.`is`(ElvishLanguage.INSTANCE)
        }.map { it.virtualFile }
        PsiDocumentManager.getInstance(project).reparseFiles(files, true)
    }
}