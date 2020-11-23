package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

abstract class ElvishScopeClimber {
    fun climb(start: PsiElement) {
        if (start is ElvishFile) {
            return
        }

        var ctxt = start
        var parent: PsiElement? = start.parent
        while (parent != null) {
            when (parent) {
                is ElvishFile -> {
                    if (visitScope(parent, ctxt)) {
                        start.project.getBuiltinScope()?.let { visitScope(it, parent as PsiElement)}
                    }
                    return
                }
                is ElvishLexicalScope -> {
                    if (!visitScope(parent, ctxt)) {
                        return
                    }
                }
            }
            ctxt = parent
            parent = parent.parent
        }
    }

    protected abstract fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean
}