package com.github.sblundy.elvish

import com.github.sblundy.elvish.lang.ElvishLexerAdapter
import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.psi.TokenType

class ElvishSyntaxHighlighter : SyntaxHighlighterBase() {
    private val log = logger<ElvishSyntaxHighlighter>()

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        return when (tokenType) {
            ElvishTypes.COMMENT -> COMMENT_KEYS
            ElvishTypes.SINGLE_QUOTED_STRING -> STRING_KEYS
            ElvishTypes.COMMAND -> COMMAND_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> {
                tokenType?.let { log.info("token type not found:$tokenType") }
                EMPTY_KEYS
            }
        }
    }

    override fun getHighlightingLexer(): Lexer = ElvishLexerAdapter()

    companion object {
        val COMMAND = createTextAttributesKey("ELVISH_COMMAND", DefaultLanguageHighlighterColors.FUNCTION_CALL)
        val STRING = createTextAttributesKey("ELVISH_STRING", DefaultLanguageHighlighterColors.STRING)
        val COMMENT = createTextAttributesKey("ELVISH_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BAD_CHARACTER = createTextAttributesKey("ELVISH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val COMMAND_KEYS = arrayOf(COMMAND)
        private val STRING_KEYS = arrayOf(STRING)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}