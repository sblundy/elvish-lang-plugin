package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

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
                climber.declarations.mapNotNull { it?.exportedVariable(element.variableName) }
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
                    builtin.findVariables(null).mapNotNull {
                        when (it) {
                            is ElvishPsiBuiltinVariable -> it.toLookupElement()
                            is ElvishPsiBuiltinValue -> it.toLookupElement()
                            else -> null
                        }
                    } + builtin.findFnCommands(null).mapNotNull {
                        when (it) {
                            is ElvishPsiBuiltinCommand -> it.toLookupElement()
                            else -> null
                        }
                    }

                }?: emptyList()
            }
            is ElvishNamespaceName -> {
                val ns = element.namespaceIdentifier as ElvishNamespaceName
                val climber = NamespaceModuleFinder(ns, element.project)

                climber.climb(element)
                climber.declarations.flatMap {
                    it?.exportedVariables() ?: emptyList()
                }.map {
                    (it as ElvishVariable).toLookupElement()
                } + climber.declarations.flatMap {
                    it?.exportedFunctions() ?: emptyList()
                }.map {
                    (it as ElvishFnCommand).toLookupElement()
                }
            }
            else -> emptyList() //TODO handle?
        }
        return variants.toTypedArray()
    }
}

private fun ElvishPsiBuiltinVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VARIABLE)
}

private fun ElvishPsiBuiltinValue.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VALUE)
}

private fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "$name~").withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

private fun ElvishVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, variableName.text).withIcon(AllIcons.Nodes.Variable)
}

private fun ElvishFnCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "${commandName.text}~").withIcon(AllIcons.Nodes.Function)
}
