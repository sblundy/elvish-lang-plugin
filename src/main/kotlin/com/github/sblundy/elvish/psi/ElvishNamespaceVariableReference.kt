package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishNamespaceVariableReference(element: ElvishExternalVariableReference, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishExternalVariableReference?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishNamespaceVariableReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = when (element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> {
                element.project.getBuiltinScope()?.findVariables(element.variableName) ?: emptyList()
            }
            is ElvishNamespaceName -> {
                val climber = NamespaceModuleFinder(element.namespaceIdentifier as ElvishNamespaceName, element.project)
                climber.climb(element)
                climber.declarations.mapNotNull { it.exportedVariable(element.variableName) }
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
        element.variableName.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val variants = when (element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> {
                element.project.getBuiltinScope()?.let { builtin ->
                    builtin.findVariables(null).map {
                        it.toVariableLookupElement()
                    } + builtin.findFnCommands(null).map {
                        it.toVariableLookupElement()
                    }

                }?: emptyList()
            }
            is ElvishNamespaceName -> {
                val ns = element.namespaceIdentifier as ElvishNamespaceName
                val climber = NamespaceModuleFinder(ns, element.project)

                climber.climb(element)
                climber.declarations.flatMap {
                    it.exportedVariables()
                }.map {
                    it.toVariableLookupElement()
                } + climber.declarations.flatMap {
                    it.exportedFunctions()
                }.map {
                    it.toVariableLookupElement()
                } + climber.declarations.flatMap { it.childModuleNames().map { name -> toNSLookupElement(name) } }
            }
            else -> emptyList() //TODO handle?
        }
        val nsClimber = namespaceModuleFinder(element.namespaceIdentifier)
        nsClimber.climb(element)
        return (variants + nsClimber.modules).toTypedArray()
    }
}
