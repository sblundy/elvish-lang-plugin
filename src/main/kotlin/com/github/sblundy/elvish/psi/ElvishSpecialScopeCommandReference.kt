package com.github.sblundy.elvish.psi

import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishSpecialScopeCommandReference(element: ElvishSpecialScopeCommandExpression, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishSpecialScopeCommandExpression?>(element, rangeInElement, true) {
    private val log = logger<ElvishSpecialScopeCommandReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve()
        return if (results.size == 1) results[0].element else null
    }

    private fun multiResolve(): Array<ResolveResult> {
        val declarations = when (element.namespaceIdentifier) {
            is ElvishLocalNamespace -> {
                val climber = object:ElvishCommandReference.CommandFinder(element.commandName) {
                    override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
                        super.visitScope(s, ctxt)
                        return false
                    }
                }
                climber.climb(element)
                climber.declarations
            }
            is ElvishUpNamespace -> {
                val climber = ElvishCommandReference.CommandFinder(element.commandName)
                element.scope?.let {climber.climb(it)}
                climber.declarations
            }
            else -> emptyList()//TODO handle?
        }

        return declarations.map { declaration ->
            log.debug("declaration=${declaration.text}")
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, element.project)
        element.commandName.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val variants = when (element.namespaceIdentifier) {
            is ElvishLocalNamespace -> {
                val climber = ElvishCommandReference.CommandVariantFinder()

                climber.climb(element)
                climber.variants
            }
            is ElvishUpNamespace -> {
                val climber = ElvishCommandReference.CommandVariantFinder()

                climber.climb(element)
                climber.variants
            }
            else -> emptyList() //TODO handle?
        }
        return variants.toTypedArray()
    }
}
