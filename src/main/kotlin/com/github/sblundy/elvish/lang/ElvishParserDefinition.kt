package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishLanguage
import com.github.sblundy.elvish.psi.ElvishFile
import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet



/**
 */
class ElvishParserDefinition: ParserDefinition {
    override fun createParser(project: Project?): PsiParser = ElvishParser()
    override fun createLexer(project: Project?): Lexer = ElvishLexerAdapter()
    override fun createFile(viewProvider: FileViewProvider): PsiFile = ElvishFile(viewProvider)
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun getFileNodeType(): IFileElementType = FILE
    override fun createElement(node: ASTNode?): PsiElement = ElvishTypes.Factory.createElement(node)
    override fun getCommentTokens(): TokenSet = COMMENT

    companion object {
        val FILE = IFileElementType(ElvishLanguage.INSTANCE)
        val COMMENT = TokenSet.create(ElvishTypes.COMMENT)
    }
}