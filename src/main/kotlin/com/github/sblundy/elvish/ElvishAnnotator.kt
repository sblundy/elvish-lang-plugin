package com.github.sblundy.elvish

import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.BRACKETS
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.BUILTIN
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.COMMAND
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.COMMAND_CAPTURE
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.PARAMETER
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.VARIABLE
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.VARIABLE_REF
import com.github.sblundy.elvish.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity.INFORMATION
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement

class ElvishAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ElvishOutputCapture -> {
                holder.annotateRanges(COMMAND_CAPTURE, element.openParan)
                holder.annotateRanges(COMMAND_CAPTURE, element.closeParan)
            }
            is ElvishExceptionCapture -> {
                holder.annotateRanges(COMMAND_CAPTURE, element.question, element.openParan)
                holder.annotateRanges(COMMAND_CAPTURE, element.closeParan)
            }
            is ElvishVarIndex -> {
                holder.annotateRanges(BRACKETS, element.openBracket)
                element.closeBracket?.let { holder.annotateRanges(BRACKETS, it) }
            }
            is ElvishVariableRef -> {
                element.atSymbol?.let {
                    holder.annotateRanges(VARIABLE_REF, element.dollarSign, it, element.variableName)
                }?: holder.annotateRanges(VARIABLE_REF, element.dollarSign, element.variableName)
            }
            is ElvishNamespaceVariableRef -> holder.annotateRanges(VARIABLE_REF, element.dollarSign, element.variableName)
            is ElvishSpecialScopeVariableRef -> {
                element.atSymbol?.let {
                    holder.annotateRanges(VARIABLE_REF, element.dollarSign, it, element.variableName)
                }?: holder.annotateRanges(VARIABLE_REF, element.dollarSign, element.variableName)
            }
            is ElvishSpecialCommand -> holder.annotateRanges(COMMAND, element.keyword)
            is ElvishCommandExpression -> holder.annotateRanges(COMMAND, element.commandName)
            is ElvishNamespaceCommandExpression -> holder.annotateRanges(COMMAND, element.namespaceIdentifier, element.commandName)
            is ElvishSpecialScopeCommandExpression -> holder.annotateRanges(COMMAND, element.namespaceIdentifier, element.commandName)
            is ElvishPsiBuiltinCommand -> holder.annotateRanges(BUILTIN, element.commandName)
            is ElvishVariable -> holder.annotateRanges(VARIABLE, element.variableName)
            is ElvishUpScopeVariableAssignment -> holder.annotateRanges(VARIABLE, element.namespaceIdentifier, element.variableName)
            is ElvishLocalScopeVariableAssignment -> holder.annotateRanges(VARIABLE, element.namespaceIdentifier, element.variableName)
            is ElvishParameter -> {
                element.atSymbol?.let {
                    holder.annotateRanges(PARAMETER, it, element.variableName)
                }?: holder.annotateRanges(PARAMETER, element.variableName)
            }
        }
    }

    private fun AnnotationHolder.annotateRanges(attr: TextAttributesKey, vararg ranges:PsiElement) {
        val range = ranges.fold(ranges.first().textRange) { tr, element -> tr.union(element.textRange) }
        newSilentAnnotation(INFORMATION).range(range).textAttributes(attr).create()
    }
}
