package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishNamespaceIdentifier
import com.github.sblundy.elvish.psi.isInScope
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class ElvishNamespaceAnnotator: Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ElvishNamespaceIdentifier -> if (!element.isInScope()) {
                holder.newAnnotation(HighlightSeverity.ERROR, ElvishBundle.message("inspection.namespace.not.imported.message", element.text)).range(element).create()
            }
        }
    }
}