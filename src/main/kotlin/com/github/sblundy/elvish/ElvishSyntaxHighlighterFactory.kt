package com.github.sblundy.elvish

import com.intellij.openapi.fileTypes.SingleLazyInstanceSyntaxHighlighterFactory

class ElvishSyntaxHighlighterFactory: SingleLazyInstanceSyntaxHighlighterFactory() {
    override fun createHighlighter() = ElvishSyntaxHighlighter()
}