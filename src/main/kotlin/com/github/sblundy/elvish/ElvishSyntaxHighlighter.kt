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
            ElvishTypes.SINGLE_QUOTED_STRING, ElvishTypes.DOUBLE_QUOTED_STRING, ElvishTypes.TEXT -> STRING_KEYS
            ElvishTypes.ESCAPED_QUOTED_TEXT -> ESCAPED_STRING_KEYS
            ElvishTypes.INVALID_ESCAPED_QUOTED_TEXT -> INVALID_ESCAPE_SEQUENCE_KEYS
            ElvishTypes.KEYWORD_IF,
                ElvishTypes.KEYWORD_ELIF,
                ElvishTypes.KEYWORD_EXCEPT,
                ElvishTypes.KEYWORD_TRY,
                ElvishTypes.KEYWORD_FINALLY,
                ElvishTypes.KEYWORD_FOR,
                ElvishTypes.KEYWORD_ELSE,
                ElvishTypes.KEYWORD_WHILE -> KEYWORD_KEYS
            ElvishTypes.OPEN_BRACKET, ElvishTypes.CLOSE_BRACKET -> BRACKETS_KEYS
            ElvishTypes.OPEN_BRACE, ElvishTypes.CLOSE_BRACE -> BRACES_KEYS
            ElvishTypes.OPEN_PARAN, ElvishTypes.CLOSE_PARAN -> PARENTHESES_KEYS
            ElvishTypes.HEAD -> COMMAND_KEYS
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
        val BUILTIN = createTextAttributesKey("ELVISH_BUILTIN", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
        val STRING = createTextAttributesKey("ELVISH_STRING", DefaultLanguageHighlighterColors.STRING)
        val KEYWORD = createTextAttributesKey("ELVISH_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val BRACKETS = createTextAttributesKey("ELVISH_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val BRACES = createTextAttributesKey("ELVISH_BRACES", DefaultLanguageHighlighterColors.BRACKETS)
        val PARENTHESES = createTextAttributesKey("ELVISH_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val ESCAPE_SEQUENCE = createTextAttributesKey("ELVISH_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
        val INVALID_ESCAPE_SEQUENCE = createTextAttributesKey("ELVISH_INVALID_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE)
        val COMMENT = createTextAttributesKey("ELVISH_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val VARIABLE = createTextAttributesKey("ELVISH_VARIABLE", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
        val VARIABLE_REF = createTextAttributesKey("ELVISH_VARIABLE_REF", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
        val BAD_CHARACTER = createTextAttributesKey("ELVISH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val COMMAND_KEYS = arrayOf(COMMAND)
        private val STRING_KEYS = arrayOf(STRING)
        private val ESCAPED_STRING_KEYS = arrayOf(ESCAPE_SEQUENCE)
        private val INVALID_ESCAPE_SEQUENCE_KEYS = arrayOf(INVALID_ESCAPE_SEQUENCE)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val BRACKETS_KEYS = arrayOf(BRACKETS)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val PARENTHESES_KEYS = arrayOf(PARENTHESES)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}