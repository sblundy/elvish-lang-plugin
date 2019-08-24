@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase

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
    ElvishTypes.KEYWORD_WHILE
)

fun parseKeywordAsBareword(builder: PsiBuilder, @Suppress("UNUSED_PARAMETER") level: Int): Boolean {
    if (builder.tokenType !in keywords) return false
    builder.remapCurrentToken(ElvishTypes.BAREWORD_CHAR)
    return GeneratedParserUtilBase.consumeTokenFast(builder, ElvishTypes.BAREWORD_CHAR)
}

fun parseBareword(builder: PsiBuilder, level: Int, parser: GeneratedParserUtilBase.Parser): Boolean {
    var result = false
    if (parser.parse(builder, level + 1)) {
        result = true
        whileNoWhitespace(builder, level, parser)
    }
    return result
}

fun parseCompound(
    builder: PsiBuilder,
    level: Int,
    tilda: GeneratedParserUtilBase.Parser,
    indexing: GeneratedParserUtilBase.Parser
): Boolean {
    var result = false
    if (!tilda.parseOptionalNoTrailingWhitespace(builder, level + 1)) {
        return result
    }

    if (indexing.parse(builder, level + 1)) {
        result = true
        whileNoWhitespace(builder, level, indexing)
    }

    return result
}

fun parseVariableName(
    builder: PsiBuilder,
    level: Int,
    variable: GeneratedParserUtilBase.Parser,
    index: GeneratedParserUtilBase.Parser
): Boolean {
    var result = false
    if (variable.parse(builder, level + 1)) {
        result = true
        whileNoWhitespace(builder, level, index)
    }

    return result
}

fun parseVariableRef(
    builder: PsiBuilder,
    level: Int,
    refSign: GeneratedParserUtilBase.Parser,
    atSymbol: GeneratedParserUtilBase.Parser,
    variable: GeneratedParserUtilBase.Parser,
    index: GeneratedParserUtilBase.Parser
): Boolean {
    var result = false

    if (!refSign.parse(builder, level + 1)) {
        return result
    } else if (builder.skippedWhitespaceOrComment()) {
        return result
    }

    if (!atSymbol.parseOptionalNoTrailingWhitespace(builder, level + 1)) {
        return result
    }

    if (variable.parse(builder, level + 1)) {
        result = true
        whileNoWhitespace(builder, level, index)
    }
    return result
}

private fun GeneratedParserUtilBase.Parser.parseOptionalNoTrailingWhitespace(builder: PsiBuilder, level: Int): Boolean {
    if (parse(builder, level)) {
        if (builder.skippedWhitespaceOrComment()) {
            return false
        }
    }

    return true
}

private fun whileNoWhitespace(builder: PsiBuilder, level: Int, parser: GeneratedParserUtilBase.Parser) {
    if (builder.skippedWhitespaceOrComment()) {
        return
    }
    while (true) {
        val indexMarker = builder.mark()
        if (!parser.parse(builder, level + 1)) {
            indexMarker.rollbackTo()
            break
        }
        indexMarker.drop()
        if (builder.skippedWhitespaceOrComment()) {
            break
        }
    }
}

fun PsiBuilder.skippedWhitespaceOrComment(): Boolean =
    eof() || GeneratedParserUtilBase.isWhitespaceOrComment(this, rawLookup(-1))