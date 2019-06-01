@file:JvmName("ElvishParserUtils")

package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.psi.ElvishTypes
import com.github.sblundy.elvish.psi.ElvishTypes.CONTINUATION
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.psi.TokenType.*

private val newLines = Regex("[\n\r]")

fun parseArgList(builder: PsiBuilder, level: Int, arg: GeneratedParserUtilBase.Parser, lineTerminator: GeneratedParserUtilBase.Parser): Boolean {
    if (!GeneratedParserUtilBase.recursion_guard_(builder, level, "parseArgList")) return false
    while (true) {
        val pos_ = GeneratedParserUtilBase.current_position_(builder)
        builder.setTokenTypeRemapper { source, start, end, text -> when(source) {
            WHITE_SPACE -> {
                if (text.subSequence(start, end).contains(newLines)) {
                    ElvishTypes.EOL
                } else {
                    source
                }
            }
            else -> source
        } }
        try {
            GeneratedParserUtilBase.consumeToken(builder, CONTINUATION)
            val result = lineTerminator.parse(builder, level)
            if (result) return true
            if (!result && !arg.parse(builder, level)) break
        } finally {
            builder.setTokenTypeRemapper(null)
        }
        if (!GeneratedParserUtilBase.empty_element_parsed_guard_(builder, "parseArgList", pos_)) break
    }
    return true
}