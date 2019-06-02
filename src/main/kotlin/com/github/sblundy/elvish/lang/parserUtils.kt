@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishTypes
import com.github.sblundy.elvish.psi.ElvishTypes.CONTINUATION
import com.intellij.lang.ITokenTypeRemapper
import com.intellij.lang.PsiBuilder
import com.intellij.lang.WhitespaceSkippedCallback
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.TextRange
import com.intellij.psi.TokenType.*
import com.intellij.psi.tree.IElementType

private val newLines = Regex("[\n\r]")

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
        val accum = TrailingWhiteSpaceAccum(builder.originalText)
        builder.setWhitespaceSkippedCallback(accum)
        val marker = builder.mark()
        try {
            if (!arg.parse(builder, level)) {
                marker.drop()
                break
            } else if (accum.trailingWhitespaceContainsEOL(builder.currentOffset)) {
                marker.rollbackTo()
                builder.setTokenTypeRemapper(TrailingWhiteSpaceRemapper(accum.whiteSpaceRange))
                arg.parse(builder, level)
                break
            }
            marker.drop()
        } finally {
            builder.setTokenTypeRemapper(null)
            builder.setWhitespaceSkippedCallback(null)
        }
        if (!GeneratedParserUtilBase.empty_element_parsed_guard_(builder, "parseArgList", pos_)) break
    }
    return true
}

private class TrailingWhiteSpaceAccum(val text: CharSequence) : WhitespaceSkippedCallback {
    var whiteSpaceRange: TextRange = TextRange.EMPTY_RANGE

    fun trailingWhitespaceContainsEOL(end: Int): Boolean = whiteSpaceRange.containsOffset(end) &&
            text.subSequence(whiteSpaceRange.startOffset, whiteSpaceRange.endOffset).contains(newLines)

    override fun onSkip(type: IElementType, start: Int, end: Int) {
        whiteSpaceRange = if (whiteSpaceRange.isEmpty) {
            TextRange.create(start, end)
        } else if (!whiteSpaceRange.containsOffset(start)) {
            TextRange.EMPTY_RANGE
        } else {
            whiteSpaceRange.union(TextRange.create(start, end))
        }
    }
}

private class TrailingWhiteSpaceRemapper(val remapEOLs:TextRange) : ITokenTypeRemapper {
    override fun filter(source: IElementType, start: Int, end: Int, text: CharSequence): IElementType = if (
        source == WHITE_SPACE &&
        remapEOLs.containsRange(start, end) &&
        text.subSequence(start, end).contains(newLines)
    ) {
        ElvishTypes.EOL
    } else {
        source
    }

}
