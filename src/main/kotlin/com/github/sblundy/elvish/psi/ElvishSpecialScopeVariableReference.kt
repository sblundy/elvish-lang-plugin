package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal abstract class ElvishSpecialScopeVariableReference<T: ElvishPsiElement>(element: T, rangeInElement: TextRange?) :
    PsiReferenceBase<T>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishSpecialScopeVariableReference<T>>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = when (getNamespaceIdentifier()) {
            is ElvishLocalNamespace -> {
                val climber = object : ElvishVariableReference.VariableFinder(getVariableName()) {
                    override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
                        super.visitScope(s, ctxt)
                        return false
                    }
                }
                climber.climb(element)
                climber.declarations
            }
            is ElvishUpNamespace -> {
                val climber = ElvishVariableReference.VariableFinder(getVariableName())
                element.scope?.let { climber.climb(it) }
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
        val ne = newNameElement(name, element.project)
        getVariableName().replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val variants = when (getNamespaceIdentifier()) {
            is ElvishLocalNamespace -> {
                val climber = ElvishVariableReference.VariableVariantFinder()

                climber.climb(element)
                climber.variants
            }
            is ElvishUpNamespace -> {
                val climber = ElvishVariableReference.VariableVariantFinder()

                climber.climb(element)
                climber.variants
            }
            else -> emptyList() //TODO handle?
        }
        return variants.toTypedArray()
    }

    protected abstract fun getVariableName(): ElvishVariableName
    protected abstract fun getNamespaceIdentifier(): ElvishNamespaceIdentifier
}
