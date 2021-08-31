package com.github.sblundy.elvish.psi

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishNamespaceCommandReference(element: ElvishNamespaceCommandExpression, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishNamespaceCommandExpression?>(element, rangeInElement, true) {
    private val log = logger<ElvishCommandReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve()
        return if (results.size == 1) results[0].element else null
    }

    private fun multiResolve(): Array<ResolveResult> {
        val declarations = when (val ns = element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> ns.resolveBuiltinScope()?.exportedFunction(element.commandName)?.let { listOf(it) } ?: emptyList()
            is ElvishNamespaceName -> ns.resolveModule()?.exportedFunction(element.commandName)?.let { listOf(it) } ?: emptyList()
            else -> emptyList()//TODO handle?
        }

        return declarations.map { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, element.project)
        element.commandName.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val variants = when (val ns = element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> ns.resolveBuiltinScope()?.exportedFunctions()?.map {
                it.toCommandLookupElement()
            } ?: emptyList()
            is ElvishNamespaceName -> ns.resolveModule()?.let { mod ->
                mod.exportedFunctions().map { it.toCommandLookupElement() } + mod.childModuleNames()
                    .map { name -> toNSLookupElement(name) }
            } ?: emptyList()
            else -> emptyList() //TODO handle?
        }

        val nsClimber = namespaceModuleFinder(element.namespaceIdentifier)
        nsClimber.climb(element)
        return (variants + nsClimber.modules).toTypedArray()
    }
}
