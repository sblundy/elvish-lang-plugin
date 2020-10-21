@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.lang.version.LanguageParseFlag
import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase

fun parseKeywordAsBareword(builder: PsiBuilder, @Suppress("UNUSED_PARAMETER") level: Int): Boolean {
    if (builder.tokenType !in ElvishParserDefinition.KEYWORDS) return false
    builder.remapCurrentToken(ElvishTypes.BAREWORD_CHAR)
    return GeneratedParserUtilBase.consumeToken(builder, ElvishTypes.BAREWORD_CHAR)
}

fun parseIfFlag(builder: PsiBuilder, level: Int, flagName: String, ifParser: GeneratedParserUtilBase.Parser): Boolean {
    val version = getLanguageLevel(builder)
    val flag = LanguageParseFlag.valueOf(flagName)
    return if (version == null || version.parseFlags.contains(flag)) {
        ifParser.parse(builder, level)
    } else {
        false
    }
}
