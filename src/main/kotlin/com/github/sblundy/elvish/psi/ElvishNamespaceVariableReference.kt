package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

internal class ElvishNamespaceVariableReference(element: ElvishVariableRefBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishVariableRefBase?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishNamespaceVariableReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val climber = object : ElvishScopeClimber() {
            val name = element.variableName
            val ns = element.namespaceName!!
            val declarations = mutableListOf<ElvishVariableDeclaration>()
            override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
                val d = when (s) {
                    is ElvishFile -> s.matchingVariables()
                    is BuiltinScope -> s.findVariables(name)
                    is ElvishLambdaBlock -> s.matchingVariables()
                    else -> emptyList()
                }
                if (d.isNotEmpty()) {
                    declarations += d
                }
                return true
            }

            fun ElvishFile.matchingVariables(): Collection<ElvishVariableDeclaration> {
                return topLevelAssignments().flatMap { it.variableList.filter { vl -> vl.matches() } }
            }

            fun ElvishLambdaBlock.matchingVariables(): Collection<ElvishVariableDeclaration> {
                return chunk.assignmentList.flatMap { it.variableList.filter { vl -> vl.matches() } }
            }

            fun ElvishVariable.matches(): Boolean {
                return getVariableName().textMatches(name) && namespaceName?.textMatches(ns)?: false
            }
        }

        climber.climb(element)

        return climber.declarations.map { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = newNameElement(name, element.project)
        element.variableName.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val climber = object : ElvishScopeClimber() {
            val ns = element.namespaceName!!
            val variants = mutableListOf<LookupElement>()
            override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
                val variables = when (s) {
                    is ElvishFile -> s.matchingVariables()
                    is BuiltinScope -> s.findVariables(ns, null)
                    is ElvishLambdaBlock -> s.matchingVariables()
                    else -> emptyList()
                }
                if (variables.isNotEmpty()) {
                    variants += variables.mapNotNull { when (it) {
                        is ElvishParameter -> it.toLookupElement()
                        is ElvishVariable -> it.toLookupElement()
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
                return topLevelAssignments().flatMap { it.variableList.filter { vl -> vl.matches() } }
            }

            fun ElvishLambdaBlock.matchingVariables(): Collection<ElvishVariableDeclaration> {
                return chunk.assignmentList.flatMap { it.variableList.filter { vl -> vl.matches() } }
            }

            fun ElvishVariable.matches(): Boolean {
                return namespaceName?.textMatches(ns)?: false
            }
        }

        climber.climb(element)
        return climber.variants.toTypedArray()
    }
}

private fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "builtin:$name~").withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

private fun ElvishParameter.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, getVariableName().text).withIcon(AllIcons.Nodes.Parameter)
}

private fun ElvishVariable.toLookupElement(): LookupElement {
    val namespacePrefix = namespaceName?.let { namespaceName -> namespaceName.variableNameList.map { it.text }}?: emptyList()
    val fullname = (namespacePrefix+getVariableName().text).joinToString(":")
    return LookupElementBuilder.create(this, fullname)
        .withPresentableText(fullname).withIcon(AllIcons.Nodes.Variable)
}

private fun ElvishPsiBuiltinVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VARIABLE)
}

private fun ElvishPsiBuiltinValue.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VALUE)
}