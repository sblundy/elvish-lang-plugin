package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishBlock
import com.github.sblundy.elvish.psi.ElvishLambda
import com.github.sblundy.elvish.psi.ElvishOutputCapture
import com.github.sblundy.elvish.psi.ElvishTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class ElvishPairBraceMatcher: PairedBraceMatcher {
    private val log = logger<ElvishPairBraceMatcher>()

    override fun getPairs() = arrayOf(
        BracePair(ElvishTypes.OPEN_BRACE, ElvishTypes.CLOSE_BRACE, true),
        BracePair(ElvishTypes.OPEN_BRACKET, ElvishTypes.CLOSE_BRACKET, true),
        BracePair(ElvishTypes.OPEN_PARAN, ElvishTypes.CLOSE_PARAN, true))

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int {
        val element = file.findElementAt(openingBraceOffset)

        val p = when (element) {
            is ElvishLambda -> element
            is ElvishOutputCapture -> element
            is ElvishBlock -> element.parent
            else -> null
        }

        log.info("found construct:$p")
        return p?.let { p.textRange.startOffset }?: openingBraceOffset
    }

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true
}