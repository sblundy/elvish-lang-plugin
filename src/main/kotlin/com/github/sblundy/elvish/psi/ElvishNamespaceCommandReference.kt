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
        val declarations = when (element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> {
                element.project.getBuiltinScope()?.findFnCommands(element.commandName) ?: emptyList()
            }
            is ElvishNamespaceName -> {
                val climber = NamespaceModuleFinder(element.namespaceIdentifier as ElvishNamespaceName, element.project)
                climber.climb(element)
                climber.declarations.mapNotNull {
                    it.exportedFunction(element.commandName)
                }
            }
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
        val variants = when (element.namespaceIdentifier) {
            is ElvishBuiltinNamespace -> {
                element.project.getBuiltinScope()?.findFnCommands(null)?.mapNotNull {
                    when (it) {
                        is ElvishPsiBuiltinCommand -> it.toLookupElement()
                        else -> {log.warn("unrecognized:"+ it.javaClass.name); null}
                    }
                } ?: emptyList()
            }
            is ElvishNamespaceName -> {
                val ns = element.namespaceIdentifier as ElvishNamespaceName
                val climber = NamespaceModuleFinder(ns, element.project)

                climber.climb(element)
                climber.declarations.flatMap {
                    it.exportedFunctions()
                }.mapNotNull {
                    when(it) {
                        is ElvishFnCommand -> it.toLookupElement()
                        is ElvishPsiBuiltinCommand -> it.toLookupElement()
                        else -> {log.warn("unrecognized:"+ it.javaClass.name); null}
                    }
                } + climber.declarations.flatMap { it.childModuleNames().map { name -> toNSLookupElement(name) } }
            }
            else -> emptyList() //TODO handle?
        }

        val nsClimber = namespaceModuleFinder(element.namespaceIdentifier)
        nsClimber.climb(element)
        return (variants + nsClimber.modules).toTypedArray()
    }
}
