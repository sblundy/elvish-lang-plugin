package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.ArrayUtilRt

internal class ElvishCommandReference(element: ElvishCommandExpressionBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishCommandExpressionBase?>(element, rangeInElement, true) {
    override fun resolve(): PsiElement? {
        val results = multiResolve()
        return if (results.size == 1) results[0].element else null
    }

    private fun multiResolve(): Array<ResolveResult> {
        val scope = ElvishPsiUtils.findParentScope(element) ?: return ResolveResult.EMPTY_ARRAY

        return scope.findFnCommands(element).mapNotNull { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, element.project)
        element.commandBareword.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val scope = ElvishPsiUtils.findParentScope(element) ?: return ArrayUtilRt.EMPTY_OBJECT_ARRAY
        val variants = mutableListOf<Any>()
        scope.processFnCommands { variants.add(LookupElementBuilder.create(it, it.text).withIcon(AllIcons.Nodes.Function)) }
        return variants.toTypedArray()
    }
}