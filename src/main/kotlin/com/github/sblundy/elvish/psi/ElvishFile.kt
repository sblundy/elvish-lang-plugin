package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.github.sblundy.elvish.ElvishLanguage
import com.github.sblundy.elvish.lang.ElvishModule
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider


class ElvishFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ElvishLanguage.INSTANCE),
    ElvishLexicalScope,
    ElvishModule {
    override fun getFileType(): FileType = ElvishFileType.INSTANCE
    override fun toString(): String = "Elvish File"
    override fun getBlock(): ElvishBlock? = project.getBuiltinScope()
    override fun getScope(): ElvishLexicalScope? = project.getBuiltinScope()

    val chunk: ElvishChunk
        get() = findChildByClass(ElvishChunk::class.java)!!

    override fun exportedVariables(): Collection<ElvishVariableDeclaration> {
        return chunk.variableDeclarations
    }

    override fun exportedFunctions(): Collection<ElvishFunctionDeclaration> {
        return chunk.fnCommandList
    }
}
