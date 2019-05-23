package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishHead
import com.github.sblundy.elvish.psi.ElvishSingleQuotedString
import com.github.sblundy.elvish.psi.ElvishVariable
import com.github.sblundy.elvish.psi.ElvishVariableRef
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class ElvishAnnotator: Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val attributes = when (element) {
            is ElvishSingleQuotedString -> ElvishSyntaxHighlighter.STRING
            is ElvishHead -> ElvishSyntaxHighlighter.COMMAND
            is ElvishVariable -> ElvishSyntaxHighlighter.VARIABLE
            is ElvishVariableRef -> ElvishSyntaxHighlighter.VARIABLE_REF
            else -> null
        }

        attributes?.let {
            val annotation = holder.createInfoAnnotation(element.textRange, null)
            annotation.textAttributes = attributes
        }
    }
}