@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase

fun parseKeywordAsBareword(builder: PsiBuilder, @Suppress("UNUSED_PARAMETER") level: Int): Boolean {
    if (builder.tokenType !in ElvishParserDefinition.KEYWORDS) return false
    builder.remapCurrentToken(ElvishTypes.BAREWORD_CHAR)
    return GeneratedParserUtilBase.consumeToken(builder, ElvishTypes.BAREWORD_CHAR)
}