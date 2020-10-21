package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishLanguage
import com.github.sblundy.elvish.lang.version.ElvishLanguageVersion
import com.github.sblundy.elvish.lang.version.LanguageParseFlag
import com.github.sblundy.elvish.lang.version.VersionsService
import com.github.sblundy.elvish.psi.ElvishFile
import com.github.sblundy.elvish.psi.ElvishTypes
import com.github.sblundy.elvish.settings.ElvishSettings
import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Key
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import java.util.*

private val key = Key.create<ElvishLanguageVersion>("ElvishParserDefinition.LanguageLevel")

class ElvishParserDefinition : ParserDefinition {
    override fun createParser(project: Project?): PsiParser = object : PsiParser {
        val inner = ElvishParser()
        override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
            project?.let {
                val settings = ElvishSettings.getInstance(project)
                val vs = VersionsService.getInstance()
                val version = settings.state.languageVersion?.let {
                    vs.getVersion(it) ?: vs.latestRelease
                } ?: vs.latestRelease
                version?.let { setLanguageLevel(builder, version) }
            }
            return inner.parse(root, builder)
        }
    }

    override fun createLexer(project: Project?): Lexer = ElvishLexerAdapter(project.currentVersionParseFlags())
    override fun createFile(viewProvider: FileViewProvider): PsiFile = ElvishFile(viewProvider)
    override fun getStringLiteralElements(): TokenSet = TokenSet.EMPTY
    override fun getFileNodeType(): IFileElementType = FILE
    override fun createElement(node: ASTNode?): PsiElement = ElvishTypes.Factory.createElement(node)
    override fun getCommentTokens(): TokenSet = COMMENT

    companion object {
        val FILE = IFileElementType(ElvishLanguage.INSTANCE)
        val COMMENT = TokenSet.create(ElvishTypes.COMMENT)
        val KEYWORDS = TokenSet.create(
            ElvishTypes.KEYWORD_DEL,
            ElvishTypes.KEYWORD_ELIF,
            ElvishTypes.KEYWORD_ELSE,
            ElvishTypes.KEYWORD_EXCEPT,
            ElvishTypes.KEYWORD_FINALLY,
            ElvishTypes.KEYWORD_FN,
            ElvishTypes.KEYWORD_FOR,
            ElvishTypes.KEYWORD_IF,
            ElvishTypes.KEYWORD_TRY,
            ElvishTypes.KEYWORD_WHILE
        )
    }
}

fun setLanguageLevel(builder: PsiBuilder, level: ElvishLanguageVersion) {
    builder.putUserData(key, level)
}

fun getLanguageLevel(builder: PsiBuilder): ElvishLanguageVersion? {
    return builder.getUserData(key)
}

fun Project?.currentVersionParseFlags():EnumSet<LanguageParseFlag> {
    return this?.let {
        val settings = ElvishSettings.getInstance(this)
        val vs = VersionsService.getInstance()
        val version = settings.state.languageVersion?.let {
            vs.getVersion(it) ?: vs.latestRelease
        } ?: vs.latestRelease
        version?.let { version.parseFlags }
    }?: allLanguageParseFlags
}

val allLanguageParseFlags = EnumSet.allOf(LanguageParseFlag::class.java)