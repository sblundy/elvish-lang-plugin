package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.github.sblundy.elvish.ElvishLanguage
import com.github.sblundy.elvish.lang.ElvishModule
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider


class ElvishFile(viewProvider: FileViewProvider): PsiFileBase(viewProvider, ElvishLanguage.INSTANCE), ElvishLexicalScope,
    ElvishModule {
    override fun getFileType(): FileType = ElvishFileType.INSTANCE
    override fun toString(): String = "Elvish File"
    override fun getScope(): ElvishLexicalScope? = project.getBuiltinScope()

    fun topLevelAssignments(): Array<ElvishAssignment> {
        return findChildrenByClass(ElvishChunk::class.java).flatMap { it.assignmentList }.toTypedArray()
    }

    fun topLevelFunctionsDeclarations(): Array<ElvishFnCommand> {
        return findChildrenByClass(ElvishChunk::class.java).flatMap { it.fnCommandList }.toTypedArray()
    }

    fun topLevelUseCommands(): Array<ElvishUseCommand> {
        return findChildrenByClass(ElvishChunk::class.java).flatMap { it.useCommandList }.toTypedArray()
    }

    override fun exportedVariables(): Collection<ElvishVariableDeclaration> {
        return topLevelAssignments().flatMap { it.variableAssignmentList }.filterIsInstance(ElvishVariable::class.java)
    }

    override fun exportedFunctions(): Collection<ElvishFunctionDeclaration> {
        return topLevelFunctionsDeclarations().map { it }
    }
}
