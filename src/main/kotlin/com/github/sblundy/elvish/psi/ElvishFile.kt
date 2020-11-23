package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider

/**
 */
class ElvishFile(viewProvider: FileViewProvider): PsiFileBase(viewProvider, ElvishLanguage.INSTANCE), ElvishLexicalScope {
    override fun getFileType(): FileType = ElvishFileType.INSTANCE
    override fun toString(): String = "Elvish File"

    fun topLevelAssignments(): Array<ElvishAssignment> {
        return findChildrenByClass(ElvishChunk::class.java).flatMap { it ->it.assignmentList }.toTypedArray()
    }

    fun topLevelFunctionsDeclarations(): Array<ElvishFnCommand> {
        return findChildrenByClass(ElvishChunk::class.java).flatMap { it ->it.fnCommandList }.toTypedArray()
    }
}