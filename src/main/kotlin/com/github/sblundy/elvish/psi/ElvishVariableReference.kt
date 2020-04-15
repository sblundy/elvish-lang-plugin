package com.github.sblundy.elvish.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishVariableReference(element: ElvishVariableRefBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishVariableRefBase?>(element, rangeInElement, true),
    PsiPolyVariantReference {

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val scope = ElvishPsiUtils.findParentScope(element) ?: return ResolveResult.EMPTY_ARRAY

        return scope.findVariables(element.variableName.text).mapNotNull { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }
}