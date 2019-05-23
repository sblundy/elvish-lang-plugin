package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishHead
import com.github.sblundy.elvish.psi.ElvishSingleQuotedString
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.diagnostic.logger
import com.intellij.psi.PsiElement

class ElvishAnnotator: Annotator {
    private val log = logger<ElvishAnnotator>()

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ElvishSingleQuotedString -> {
                val annotation = holder.createInfoAnnotation(element.textRange, null)
                annotation.textAttributes = ElvishSyntaxHighlighter.STRING
            }
            is ElvishHead -> {
                val annotation = holder.createInfoAnnotation(element.textRange, null)
                annotation.textAttributes = ElvishSyntaxHighlighter.COMMAND
            }
            else -> {
                log.info("element type not found:$element")
            }
        }
    }
}