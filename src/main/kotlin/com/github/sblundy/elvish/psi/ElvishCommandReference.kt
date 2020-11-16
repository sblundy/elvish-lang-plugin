package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.ArrayUtilRt
import icons.ElvishIcons

internal class ElvishCommandReference(element: ElvishCommandExpressionBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishCommandExpressionBase?>(element, rangeInElement, true) {
    private val log = logger<ElvishCommandReference>()

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
        scope.processFnCommands {
            when (it) {
                is ElvishFnCommand -> variants.add(LookupElementBuilder.create(it, it.getCommandName().text).withIcon(AllIcons.Nodes.Function))
                is ElvishPsiBuiltinCommand -> variants.add(LookupElementBuilder.create(it, it.name).withIcon(ElvishIcons.BUILTIN_FUNCTION))
                else -> log.error("function type not supported: ${it.javaClass.canonicalName}")
            }
            true
        }
        return variants.toTypedArray()
    }
}