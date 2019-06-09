@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishBundle
import com.github.sblundy.elvish.psi.ElvishTypes
import com.github.sblundy.elvish.psi.ElvishTypes.CONTINUATION
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.psi.tree.TokenSet

private val keywords = setOf(
    ElvishTypes.KEYWORD_DEL,
    ElvishTypes.KEYWORD_ELIF,
    ElvishTypes.KEYWORD_ELSE,
    ElvishTypes.KEYWORD_EXCEPT,
    ElvishTypes.KEYWORD_FINALLY,
    ElvishTypes.KEYWORD_FN,
    ElvishTypes.KEYWORD_FOR,
    ElvishTypes.KEYWORD_IF,
    ElvishTypes.KEYWORD_TRY,
    ElvishTypes.KEYWORD_WHILE)

fun parseKeywordAsBareword(builder: PsiBuilder, level: Int): Boolean {
    if (!(builder.tokenType in keywords)) return false
    builder.remapCurrentToken(ElvishTypes.BAREWORD_CHAR)
    return true
}

fun parseArgList(
    builder: PsiBuilder,
    level: Int,
    arg: GeneratedParserUtilBase.Parser,
    lineTerminator: GeneratedParserUtilBase.Parser
): Boolean {
    if (!GeneratedParserUtilBase.recursion_guard_(builder, level, "parseArgList")) return false
    while (true) {
        val pos_ = GeneratedParserUtilBase.current_position_(builder)
        GeneratedParserUtilBase.consumeToken(builder, CONTINUATION)
        val result = lineTerminator.parse(builder, level)
        if (result) return true
        val marker = builder.mark()
        if (!arg.parse(builder, level)) {
            marker.drop()
            break
        } else if (builder.trailingWhitespaceContainsEOL(builder.currentOffset)) {
            marker.rollbackTo()
            builder.remap(builder.whiteSpaceRange())
            arg.parse(builder, level)
            lineTerminator.parse(builder, level)
            break
        }
        marker.drop()
        if (!GeneratedParserUtilBase.empty_element_parsed_guard_(builder, "parseArgList", pos_)) break
    }
    return true
}

fun parseBlockBody(
    builder: PsiBuilder,
    level: Int,
    command: GeneratedParserUtilBase.Parser
): Boolean {
    if (!GeneratedParserUtilBase.recursion_guard_(builder, level, "parseBlockBody")) return false
    while (true) {
        val pos_ = GeneratedParserUtilBase.current_position_(builder)
        if (!command.parse(builder, level)) {
            if (builder.tokenType == ElvishTypes.CLOSE_BRACE) {
                break
            }

            val marker = builder.mark()
            skipCountingBraces(builder, TokenSet.create(ElvishTypes.CLOSE_BRACE))
            marker.error(ElvishBundle.message("psi.error.command"))
        }
        if (!GeneratedParserUtilBase.empty_element_parsed_guard_(builder, "parseBlockBody", pos_)) break
    }
    return true
}

fun parseCompoundExpressionSegment(builder: PsiBuilder,
                                   level: Int,
                                   parser: GeneratedParserUtilBase.Parser,
                                   following: GeneratedParserUtilBase.Parser): Boolean {
    if (!GeneratedParserUtilBase.recursion_guard_(builder, level, "parseCompoundExpressionSegment")) return false
    val marker_ = GeneratedParserUtilBase.enter_section_(builder)
    var result: Boolean = parser.parse(builder, level) && !builder.hasTrailingWhitespace()
    if (result) {
        result = following.parse(builder, level)
    }

    GeneratedParserUtilBase.exit_section_(builder, marker_, null, result)
    return result
}

private fun skipCountingBraces(builder: PsiBuilder, until: TokenSet): Boolean {
    var braceLevel = 0
    while (true) {
        if (builder.eof()) {
            return false
        }
        val type = builder.tokenType
        if (braceLevel == 0 && until.contains(type)) {
            return true
        }

        if (ElvishTypes.OPEN_BRACE === type) {
            braceLevel++
        } else if (ElvishTypes.CLOSE_BRACE === type) {
            braceLevel--
        }
        builder.advanceLexer()
    }
}