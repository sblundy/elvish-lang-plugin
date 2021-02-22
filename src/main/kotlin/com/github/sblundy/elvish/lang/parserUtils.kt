@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.ElvishBundle
import com.github.sblundy.elvish.lang.version.LanguageParseFlag
import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase

fun parseKeywordAsBareword(builder: PsiBuilder, @Suppress("UNUSED_PARAMETER") level: Int): Boolean {
    if (builder.tokenType !in ElvishParserDefinition.KEYWORDS) return false
    builder.remapCurrentToken(ElvishTypes.BAREWORD_CHAR)
    return GeneratedParserUtilBase.consumeToken(builder, ElvishTypes.BAREWORD_CHAR)
}

fun ifFlag(builder: PsiBuilder, level: Int, flagName: String): Boolean {
    val version = getLanguageLevel(builder)
    val flag = LanguageParseFlag.valueOf(flagName)
    return version == null || version.parseFlags.contains(flag)
}

fun missingLambdaBody(builder: PsiBuilder, level: Int): Boolean {
    builder.error(ElvishBundle.message("psi.error.lambda.missing"))
    return true
}

fun missingSpaceInVarOrSet(builder: PsiBuilder, level: Int): Boolean {
    builder.error(ElvishBundle.message("psi.error.variable.space.missing"))
    return true
}

fun invalidQuotedVariableName(builder: PsiBuilder, level: Int): Boolean {
    builder.error(ElvishBundle.message("psi.error.variable.invalid"))
    return true
}