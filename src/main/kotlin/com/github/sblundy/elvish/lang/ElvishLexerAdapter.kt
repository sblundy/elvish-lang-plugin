package com.github.sblundy.elvish.lang

import com.intellij.lexer.FlexAdapter

class ElvishLexerAdapter : FlexAdapter(ElvishLexer(null))