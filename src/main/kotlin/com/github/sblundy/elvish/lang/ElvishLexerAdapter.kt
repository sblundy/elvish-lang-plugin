package com.github.sblundy.elvish.lang

import com.github.sblundy.elvish.lang.version.LanguageParseFlag
import com.intellij.lexer.FlexAdapter
import java.util.*

class ElvishLexerAdapter(parseFlags:EnumSet<LanguageParseFlag>) : FlexAdapter(createLexer(parseFlags))

private fun createLexer(parseFlags:EnumSet<LanguageParseFlag>) : ElvishLexer {
    val l = ElvishLexer(null)
    l.parseFlags = parseFlags
    return l
}