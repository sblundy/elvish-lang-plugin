package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

abstract class ElvishScopeClimber {
    fun climb(start: ElvishPsiElement) {
        var ctxt = start
        var scope = start.scope
        while (scope != null) {
            if (!visitScope(scope, ctxt)) {
                return
            }
            ctxt = scope
            scope = scope.scope
        }
    }

    protected abstract fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean
}