@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.PsiBuilder

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

fun parseCommaAsSeparator(builder: PsiBuilder, level: Int): Boolean {
    if (builder.tokenText != ",") return false
    builder.remapCurrentToken(ElvishTypes.BRACED_SEPARATOR)
    builder.advanceLexer()
    return true
}

fun parseKeywordAsBareword(builder: PsiBuilder, level: Int): Boolean {
    if (!(builder.tokenType in keywords)) return false
    builder.remapCurrentToken(ElvishTypes.BAREWORD_CHAR)
    return true
}