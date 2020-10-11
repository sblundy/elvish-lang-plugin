package com.github.sblundy.elvish.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishCommandReference(element: ElvishCommandExpressionBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishCommandExpressionBase?>(element, rangeInElement, true) {
    override fun resolve(): PsiElement? {
        val results = multiResolve()
        return if (results.size == 1) results[0].element else null
    }

    private fun multiResolve(): Array<ResolveResult> {
        val scope = ElvishPsiUtils.findParentScope(element) ?: return ResolveResult.EMPTY_ARRAY

        val ns = element.namespaceNameList.map { ns -> ns.text }
        return scope.findFnCommands(element.commandBareword.text, ns).mapNotNull { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, element.project)
        element.commandBareword.replace(ne)
        return element
    }

    override fun isReferenceTo(element: PsiElement): Boolean {
        return super.isReferenceTo(element)
    }
}