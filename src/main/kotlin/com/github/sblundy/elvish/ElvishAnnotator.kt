package com.github.sblundy.elvish

import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.BUILTIN
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.COMMAND
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.COMMAND_CAPTURE
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.PARAMETER
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.VARIABLE
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.VARIABLE_REF
import com.github.sblundy.elvish.lang.version.ElvishBundledService
import com.github.sblundy.elvish.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement

class ElvishAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val builtins = ElvishBundledService.getInstance().builtins(element.project)
        when (element) {
            is ElvishOutputCapture -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(COMMAND_CAPTURE).create()
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(COMMAND_CAPTURE).create()
                return
            }
            is ElvishExceptionCapture -> {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.question.textRange.union(element.openParan.textRange))
                    .textAttributes(COMMAND_CAPTURE).create()
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(COMMAND_CAPTURE).create()
                return
            }
            else -> {
                val attributes = when (element) {
                    is ElvishHead -> if (builtins.contains(element.text)) {
                        BUILTIN
                    } else {
                        COMMAND
                    }
                    is ElvishVariable -> VARIABLE
                    is ElvishVariableRef -> VARIABLE_REF
                    is ElvishParameter -> PARAMETER
                    else -> null
                }

                attributes?.let {
                    holder.newSilentAnnotation(HighlightSeverity.INFORMATION).textAttributes(attributes).create()
                }
            }
        }
    }
}
