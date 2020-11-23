package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

internal class ElvishNamespaceVariableReference(element: ElvishNamespaceVariableRef, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishNamespaceVariableRef?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishNamespaceVariableReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val declarations = when (element.namespaceIdentifier) {
            is ElvishLocalNamespace -> {
                val climber = object:ElvishVariableReference.VariableFinder(element.variableName) {
                    override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
                        super.visitScope(s, ctxt)
                        return false
                    }
                }
                climber.climb(element)
                climber.declarations
            }
            is ElvishUpNamespace -> {
                val climber = ElvishVariableReference.VariableFinder(element.variableName)
                element.scope?.let {climber.climb(it)}
                climber.declarations
            }
            is ElvishBuiltinNamespace -> {
                element.project.getBuiltinScope()?.findVariables(element.variableName) ?: emptyList()
            }
            else -> {
                val climber = DefaultVariableFinder()
                climber.climb(element)
                climber.declarations
            }
        }

        return declarations.map { declaration ->
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
            is ElvishBuiltinNamespace -> {
                element.project.getBuiltinScope()?.findVariables(null)?.mapNotNull {
                    when (it) {
                        is ElvishPsiBuiltinVariable -> it.toLookupElement()
                        is ElvishPsiBuiltinValue -> it.toLookupElement()
                        else -> null
                    }
                } ?: emptyList()
            }
            else -> {
                val climber = DefaultVariableVariantFinder()

                climber.climb(element)
                climber.variants
            }
        }
        return variants.toTypedArray()
    }

    private inner class DefaultVariableFinder: ElvishScopeClimber() {
        val name = element.variableName
        val ns = element.namespaceIdentifier
        val declarations = mutableListOf<ElvishVariableDeclaration>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
            val d = when (s) {
                is ElvishFile -> s.matchingVariables()
                is ElvishLambdaBlock -> s.matchingVariables()
                else -> emptyList()
            }
            if (d.isNotEmpty()) {
                declarations += d
            }
            return true
        }

        fun ElvishFile.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return topLevelAssignments().flatMap { it.namespaceVariableList.filter { vl -> vl.matches() } }
        }

        fun ElvishLambdaBlock.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return chunk.assignmentList.flatMap { it.namespaceVariableList.filter { vl -> vl.matches() } }
        }

        fun ElvishNamespaceVariable.matches(): Boolean {
            return getVariableName().textMatches(name) && namespaceIdentifier.matches(ns)
        }
    }

    private inner class DefaultVariableVariantFinder: ElvishScopeClimber() {
        val ns = element.namespaceIdentifier
        val variants = mutableListOf<LookupElement>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
            val variables = when (s) {
                is ElvishFile -> s.matchingVariables()
                is ElvishLambdaBlock -> s.matchingVariables()
                else -> emptyList()
            }
            if (variables.isNotEmpty()) {
                variants += variables.mapNotNull { when (it) {
                    is ElvishNamespaceVariable -> it.toLookupElement()
                    is ElvishPsiBuiltinVariable -> it.toLookupElement()
                    is ElvishPsiBuiltinValue -> it.toLookupElement()
                    else -> null
                } }
            }
            val functions = when(s) {
                is BuiltinScope -> s.findFnCommands(ns, null)
                else -> emptyList()
            }
            if (functions.isNotEmpty()) {
                variants += functions.mapNotNull { when (it) {
                    is ElvishPsiBuiltinCommand -> it.toLookupElement()
                    else -> null
                } }
            }
            return true
        }

        fun ElvishFile.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return topLevelAssignments().flatMap { it.namespaceVariableList.filter { vl -> vl.matches() } }
        }

        fun ElvishLambdaBlock.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return chunk.assignmentList.flatMap { it.namespaceVariableList.filter { vl -> vl.matches() } }
        }

        fun ElvishNamespaceVariable.matches(): Boolean {
            return namespaceIdentifier.matches(ns)
        }
    }
}

private fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "builtin:$name~").withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

private fun ElvishNamespaceVariable.toLookupElement(): LookupElement {
    val fullname = (namespaceIdentifier.text+getVariableName().text)
    return LookupElementBuilder.create(this, fullname)
        .withPresentableText(fullname).withIcon(AllIcons.Nodes.Variable)
}

private fun ElvishPsiBuiltinVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VARIABLE)
}

private fun ElvishPsiBuiltinValue.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VALUE)
}
