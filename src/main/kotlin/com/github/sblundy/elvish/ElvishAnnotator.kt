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
    "!=",
    "*",
    "+",
    "-",
    "-gc",
    "-ifaddrs",
    "-is-dir",
    "-log",
    "-override-wcwidth",
    "-source",
    "-stack",
    "-time",
    "/",
    "<",
    "<=",
    "==",
    ">",
    ">=",
    "^",
    "all",
    "assoc",
    "base",
    "bool",
    "break",
    "cd",
    "chr",
    "constantly",
    "continue",
    "count",
    "dir-history",
    "dissoc",
    "drop",
    "each",
    "eawk",
    "echo",
    "eq",
    "esleep",
    "eval-symlinks",
    "exec",
    "exit",
    "explode",
    "external",
    "fail",
    "fclose",
    "fg",
    "float64",
    "fopen",
    "from-json",
    "from-lines",
    "get-env",
    "has-env",
    "has-external",
    "has-key",
    "has-prefix",
    "has-suffix",
    "has-value",
    "is",
    "joins",
    "keys",
    "kind-of",
    "multi-error",
    "nop",
    "not",
    "not-eq",
    "ns",
    "only-bytes",
    "only-values",
    "ord",
    "path-abs",
    "path-base",
    "path-clean",
    "path-dir",
    "path-ext",
    "peach",
    "pipe",
    "pprint",
    "prclose",
    "print",
    "put",
    "pwclose",
    "rand",
    "randint",
    "range",
    "repeat",
    "replaces",
    "repr",
    "resolve",
    "return",
    "run-parallel",
    "search-external",
    "set-env",
    "slurp",
    "splits",
    "src",
    "styled",
    "styled-segment",
    "take",
    "tilde-abbr",
    "to-json",
    "to-lines",
    "to-string",
    "unset-env",
    "wcswidth"
)