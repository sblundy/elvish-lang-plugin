package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishPsiBuiltin
import com.intellij.codeInsight.completion.CompletionLocation
import com.intellij.codeInsight.completion.CompletionWeigher
import com.intellij.codeInsight.lookup.LookupElement

class ElvishCompletionWeigher: CompletionWeigher() {
    override fun weigh(element: LookupElement, location: CompletionLocation): Comparable<*> {
        return when (val o = element.`object`) {
            is ElvishPsiBuiltin -> if (o.isDoNotUse) { -1 } else { 0 }
            else -> 0
        }
    }
}