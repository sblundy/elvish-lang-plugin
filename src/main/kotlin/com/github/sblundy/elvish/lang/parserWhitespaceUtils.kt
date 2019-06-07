package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.ITokenTypeRemapper
import com.intellij.lang.PsiBuilder
import com.intellij.lang.WhitespaceSkippedCallback
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.TextRange
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType


private val newLines = Regex("[\n\r]")
private val TrailingWhiteSpaceAccumKey = Key.create<TrailingWhiteSpaceAccum>("TrailingWhiteSpaceAccum")

internal fun PsiBuilder.hasTrailingWhitespace(): Boolean {
    return accum().hasTrailingWhitespace(currentOffset)
}

internal fun PsiBuilder.trailingWhitespaceContainsEOL(end: Int): Boolean {
    return accum().trailingWhitespaceContainsEOL(end)
}

internal fun PsiBuilder.whiteSpaceRange(): TextRange {
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

    fun hasTrailingWhitespace(end: Int): Boolean = whiteSpaceRange.containsOffset(end)

    fun trailingWhitespaceContainsEOL(end: Int): Boolean = hasTrailingWhitespace(end) &&
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

internal fun PsiBuilder.remap(range: TextRange) {
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
        source == TokenType.WHITE_SPACE &&
        remapEOLs.containsRange(start, end) &&
        text.subSequence(start, end).contains(newLines)
    ) {
        ElvishTypes.EOL
    } else {
        source
    }

}
