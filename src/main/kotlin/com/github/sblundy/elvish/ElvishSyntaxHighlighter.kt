package com.github.sblundy.elvish

import com.github.sblundy.elvish.lang.ElvishLexerAdapter
import com.github.sblundy.elvish.lang.version.LanguageParseFlag
import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.psi.TokenType
import java.util.*

class ElvishSyntaxHighlighter(private val parseFlags: EnumSet<LanguageParseFlag>) : SyntaxHighlighterBase() {
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        return when (tokenType) {
            ElvishTypes.COMMENT -> COMMENT_KEYS
                ElvishTypes.SINGLE_QUOTE,
                ElvishTypes.DOUBLE_QUOTE,
                ElvishTypes.TEXT -> STRING_KEYS
            ElvishTypes.ESCAPED_QUOTED_TEXT -> ESCAPED_STRING_KEYS
            ElvishTypes.INVALID_ESCAPED_QUOTED_TEXT -> INVALID_ESCAPE_SEQUENCE_KEYS
            ElvishTypes.KEYWORD_DEL,
                ElvishTypes.KEYWORD_FN,
                ElvishTypes.KEYWORD_FOR,
                ElvishTypes.KEYWORD_IF,
                ElvishTypes.KEYWORD_TRY,
                ElvishTypes.KEYWORD_USE,
                ElvishTypes.KEYWORD_VAR,
                ElvishTypes.KEYWORD_SET,
                ElvishTypes.KEYWORD_WHILE -> KEYWORD_KEYS
            ElvishTypes.KEYWORD_ELIF,
                ElvishTypes.KEYWORD_ELSE,
                ElvishTypes.KEYWORD_EXCEPT,
                ElvishTypes.KEYWORD_FINALLY -> SEP_KEYWORD_KEYS
            ElvishTypes.OPEN_BRACKET, ElvishTypes.CLOSE_BRACKET -> BRACKETS_KEYS
            ElvishTypes.OPEN_BRACE, ElvishTypes.CLOSE_BRACE -> BRACES_KEYS
            ElvishTypes.OPEN_PARAN, ElvishTypes.CLOSE_PARAN -> PARENTHESES_KEYS
            ElvishTypes.PIPE, ElvishTypes.REDIR -> OPERATOR_KEYS
            ElvishTypes.HEAD -> COMMAND_KEYS
            TokenType.BAD_CHARACTER -> BAD_CHAR_KEYS
            else -> EMPTY_KEYS
        }
    }

    override fun getHighlightingLexer(): Lexer = ElvishLexerAdapter(parseFlags)

    companion object {
        val COMMAND = createTextAttributesKey("ELVISH_COMMAND", DefaultLanguageHighlighterColors.IDENTIFIER)
        val BUILTIN = createTextAttributesKey("ELVISH_BUILTIN", COMMAND)
        val STRING = createTextAttributesKey("ELVISH_STRING", DefaultLanguageHighlighterColors.STRING)
        val KEYWORD = createTextAttributesKey("ELVISH_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val SEP_KEYWORD = createTextAttributesKey("ELVISH_SEP_KEYWORD", KEYWORD)
        val BRACKETS = createTextAttributesKey("ELVISH_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val BRACES = createTextAttributesKey("ELVISH_BRACES", DefaultLanguageHighlighterColors.BRACKETS)
        val PARENTHESES = createTextAttributesKey("ELVISH_PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val PARAMETER = createTextAttributesKey("ELVISH_PARAMETER", DefaultLanguageHighlighterColors.PARAMETER)
        val OPERATOR = createTextAttributesKey("ELVISH_REDIRECTION", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val COMMAND_CAPTURE = createTextAttributesKey("ELVISH_COMMAND_CAPTURE", OPERATOR)
        val ESCAPE_SEQUENCE = createTextAttributesKey("ELVISH_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE)
        val INVALID_ESCAPE_SEQUENCE = createTextAttributesKey("ELVISH_INVALID_ESCAPE_SEQUENCE", DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE)
        val COMMENT = createTextAttributesKey("ELVISH_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val VARIABLE_REF = createTextAttributesKey("ELVISH_VARIABLE_REF", DefaultLanguageHighlighterColors.GLOBAL_VARIABLE)
        val VARIABLE = createTextAttributesKey("ELVISH_VARIABLE", VARIABLE_REF)
        val BAD_CHARACTER = createTextAttributesKey("ELVISH_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER)

        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val COMMAND_KEYS = arrayOf(COMMAND)
        private val STRING_KEYS = arrayOf(STRING)
        private val ESCAPED_STRING_KEYS = arrayOf(ESCAPE_SEQUENCE)
        private val INVALID_ESCAPE_SEQUENCE_KEYS = arrayOf(INVALID_ESCAPE_SEQUENCE)
        private val KEYWORD_KEYS = arrayOf(KEYWORD)
        private val SEP_KEYWORD_KEYS = arrayOf(SEP_KEYWORD)
        private val BRACKETS_KEYS = arrayOf(BRACKETS)
        private val BRACES_KEYS = arrayOf(BRACES)
        private val PARENTHESES_KEYS = arrayOf(PARENTHESES)
        private val OPERATOR_KEYS = arrayOf(OPERATOR)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}