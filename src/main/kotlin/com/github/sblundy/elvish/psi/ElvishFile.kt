package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

/**
 */
class ElvishFile(viewProvider: FileViewProvider): PsiFileBase(viewProvider, ElvishLanguage.INSTANCE) {
    override fun getFileType(): FileType = ElvishFileType.INSTANCE
    override fun toString(): String = "Elvish File"
}