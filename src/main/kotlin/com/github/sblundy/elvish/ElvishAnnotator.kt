package com.github.sblundy.elvish

import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.BUILTIN
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.COMMAND
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.ESCAPED_STRING
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.STRING
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.VARIABLE
import com.github.sblundy.elvish.ElvishSyntaxHighlighter.Companion.VARIABLE_REF
import com.github.sblundy.elvish.psi.*
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class ElvishAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        val attributes = when (element) {
            is ElvishSingleQuotedString -> STRING
            is ElvishEscapedSequence -> ESCAPED_STRING
            is ElvishHead -> if (isBuiltin(element)) { BUILTIN } else { COMMAND }
            is ElvishVariable -> VARIABLE
            is ElvishVariableRef -> VARIABLE_REF
            else -> null
        }

        attributes?.let {
            val annotation = holder.createInfoAnnotation(element.textRange, null)
            annotation.textAttributes = attributes
        }
    }

    private fun isBuiltin(head: ElvishHead): Boolean {
        return builtins.contains(head.text)
    }
}

private val builtins = setOf(
    "external",
    "has-external",
    "search-external",
    "fg",
    "exec",
    "exit",
    "ns",
    "range",
    "repeat",
    "explode",
    "assoc",
    "dissoc",
    "all",
    "has-key",
    "has-value",
    "take",
    "drop",
    "count",
    "keys",
    "src",
    "-gc",
    "-stack",
    "-log",
    "has-env",
    "get-env",
    "run-parallel",
    "fail",
    "multi-error",
    "return",
    "break",
    "continue",
    "each",
    "peach",
    "cd",
    "dir-history",
    "tilde-abbr",
    "-is-dir",
    "put",
    "print",
    "echo",
    "pprint",
    "repr",
    "only-bytes",
    "only-values",
    "slurp",
    "from-lines",
    "from-json",
    "to-lines",
    "to-json",
    "fopen",
    "fclose",
    "pipe",
    "prclose",
    "pwclose",
    "nop",
    "kind-of",
    "constantly",
    "resolve",
    "-source",
    "esleep",
    "-time",
    "-ifaddrs",
    "float64",
    "-",
    "randint",
    "not",
    "is",
    "eq",
    "not-eq",
    "to-string",
    "ord",
    "chr",
    "base",
    "joins",
    "splits",
    "replaces",
    "eawk",
    "styled-segment",
    "styled"
)