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
                val ns = element.namespaceIdentifier as ElvishBuiltinNamespace
                ns.resolveBuiltinScope()?.findVariables(element.variableName) ?: emptyList()
            }
            is ElvishNamespaceName -> {
                val ns = element.namespaceIdentifier as ElvishNamespaceName
                ns.resolveModule()?.exportedVariable(element.variableName)?.let { listOf(it) }?: emptyList()
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
        val variants = when (val ns = element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> {
                ns.resolveBuiltinScope()?.let { builtin ->
                    builtin.findVariables(null).map {
                        it.toVariableLookupElement()
                    } + builtin.findFnCommands(null).map {
                        it.toVariableLookupElement()
                    }

                }?: emptyList()
            }
            is ElvishNamespaceName -> {
                ns.resolveModule()?.let { mod ->
                    mod.exportedVariables().map { it.toVariableLookupElement() } +
                            mod.exportedFunctions().map { it.toVariableLookupElement() } +
                            mod.childModuleNames().map {  name -> toNSLookupElement(name) }
                } ?: emptyList()
            }
            else -> emptyList() //TODO handle?
        }
        val nsClimber = namespaceModuleFinder(element.namespaceIdentifier)
        nsClimber.climb(element)
        return (variants + nsClimber.modules).toTypedArray()
    }
}
