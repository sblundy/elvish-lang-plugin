@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishBundle
import com.github.sblundy.elvish.psi.ElvishTypes
import com.github.sblundy.elvish.psi.ElvishTypes.CONTINUATION
import com.intellij.lang.ITokenTypeRemapper
import com.intellij.lang.PsiBuilder
import com.intellij.lang.WhitespaceSkippedCallback
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.TextRange
import com.intellij.psi.TokenType.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet

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
        val marker = builder.mark()
        try {
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
        } finally {
            builder.setTokenTypeRemapper(null)
        }
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

fun skipCountingBraces(builder: PsiBuilder, until: TokenSet): Boolean {
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

private val TrailingWhiteSpaceAccumKey = Key.create<TrailingWhiteSpaceAccum>("TrailingWhiteSpaceAccum")

private fun PsiBuilder.trailingWhitespaceContainsEOL(end: Int): Boolean {
    return accum().trailingWhitespaceContainsEOL(end)
}

private fun PsiBuilder.whiteSpaceRange(): TextRange {
    return accum().whiteSpaceRange
}

private fun PsiBuilder.accum(): TrailingWhiteSpaceAccum {
    return if (!TrailingWhiteSpaceAccumKey.isIn(this)) {
        val accum = TrailingWhiteSpaceAccum(originalText)
        putUserData(TrailingWhiteSpaceAccumKey, accum)
        setWhitespaceSkippedCallback(accum)
        accum
    } else this.getUserData(TrailingWhiteSpaceAccumKey)!!
}

private class TrailingWhiteSpaceAccum(val text: CharSequence) : WhitespaceSkippedCallback {
    var whiteSpaceRange: TextRange = TextRange.EMPTY_RANGE

    fun trailingWhitespaceContainsEOL(end: Int): Boolean = whiteSpaceRange.containsOffset(end) &&
            text.subSequence(whiteSpaceRange.startOffset, whiteSpaceRange.endOffset).contains(newLines)

    override fun onSkip(type: IElementType, start: Int, end: Int) {
        whiteSpaceRange = if (whiteSpaceRange.isEmpty) {
            TextRange.create(start, end)
        } else if (!whiteSpaceRange.containsOffset(start)) {
            TextRange.create(start, end)
        } else {
            whiteSpaceRange.union(TextRange.create(start, end))
        }
    }

}

private val RemapperKey = Key.create<TrailingWhiteSpaceRemapper>("TrailingWhiteSpaceRemapper")

private fun PsiBuilder.remap(range: TextRange) {
    remapper().remapEOLs = range
}

private fun PsiBuilder.remapper(): TrailingWhiteSpaceRemapper {
    return if (!RemapperKey.isIn(this)) {
        val accum = TrailingWhiteSpaceRemapper()
        putUserData(RemapperKey, accum)
        this.setTokenTypeRemapper(accum)
        accum
    } else this.getUserData(RemapperKey)!!
}

private class TrailingWhiteSpaceRemapper : ITokenTypeRemapper {
    var remapEOLs = TextRange.EMPTY_RANGE

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
