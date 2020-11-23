package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

internal class ElvishVariableReference(element: ElvishVariableRef, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishVariableRef?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishVariableReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val climber = VariableFinder(element.variableName)

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
        val climber = VariableVariantFinder()
        climber.climb(element)
        return climber.variants.toTypedArray()
    }

    internal open class VariableFinder(private val name: ElvishVariableName): ElvishScopeClimber() {
        val declarations = mutableListOf<ElvishVariableDeclaration>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
            val (d, c) = when (s) {
                is ElvishFile -> Pair(s.matchingVariables(), true)
                is BuiltinScope -> Pair(s.findVariables(name), true)
                is ElvishExceptBlock -> {
                    val p = if (s.variable.matches()) {
                        Pair(listOf(s.variable), false)
                    } else {
                        Pair(emptyList(), true)
                    }
                    p.and(s.chunk.matchingVariables())
                }
                is ElvishFnCommand -> {
                    val p = s.lambdaArguments?.parameterList?.find { it.matches() }?.let {
                        Pair(listOf(it), false)
                    } ?: Pair(emptyList(), true)
                    p.and(s.chunk.matchingVariables())
                }
                is ElvishLambda -> {
                    val p = s.lambdaArguments?.parameterList?.find { it.matches() }?.let {
                        Pair(listOf(it), false)
                    } ?: Pair(emptyList(), true)
                    p.and(s.chunk.matchingVariables())
                }
                is ElvishLambdaBlock -> s.chunk.matchingVariables()
                else -> Pair(emptyList(), true)
            }
            if (d.isNotEmpty()) {
                declarations += d
            }
            return c
        }

        fun ElvishFile.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return topLevelAssignments().flatMap { it.variableList.filter { vl -> vl.matches() } }
        }

        fun ElvishChunk.matchingVariables(): Pair<Collection<ElvishVariableDeclaration>, Boolean> {
            val variables = assignmentList.flatMap { it.variableList.filter { vl -> vl.matches() } }
            val upLocal = this.matchingUpLocalVariables()
            if (upLocal.isEmpty() || !upLocal.any { it.second }) {
                return Pair(variables, true)
            }

            return Pair(variables + upLocal.mapNotNull {
                if (it.second) {
                    it.first
                } else {
                    null
                }
            }, false)
        }

        fun ElvishChunk.matchingUpLocalVariables(): Collection<Pair<ElvishVariableDeclaration, Boolean>> {
            return assignmentList.flatMap {
                it.namespaceVariableList.filter { vl ->
                    vl.getVariableName().textMatches(name)
                }.mapNotNull { vl ->
                    when (vl.namespaceIdentifier) {
                        is ElvishLocalNamespace -> Pair(vl, true)
                        is ElvishUpNamespace -> Pair(vl, false)
                        else -> null
                    }
                }
            }
        }

        fun ElvishVariable.matches(): Boolean = getVariableName().textMatches(name)

        fun ElvishParameter.matches(): Boolean = textMatches(name)

        fun Pair<Collection<ElvishVariableDeclaration>, Boolean>.and(p: Pair<Collection<ElvishVariableDeclaration>, Boolean>): Pair<Collection<ElvishVariableDeclaration>, Boolean> {
            return Pair(this.first + p.first, this.second && p.second)
        }
    }

    internal class VariableVariantFinder: ElvishScopeClimber() {
        val variants = mutableListOf<LookupElement>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
            val variables = when (s) {
                is ElvishFile -> s.topLevelAssignments().flatMap { it.variableList }
                is BuiltinScope -> s.findVariables(null)
                is ElvishExceptBlock -> listOf(s.variable) + s.chunk.assignmentList.flatMap { it.variableList }
                is ElvishFnCommand -> {
                    val p =s.lambdaArguments?.parameterList?: emptyList()
                    p + s.chunk.assignmentList.flatMap { it.variableList }
                }
                is ElvishLambda -> {
                    val p = s.lambdaArguments?.parameterList?: emptyList()
                    p + s.chunk.assignmentList.flatMap { it.variableList }
                }
                is ElvishLambdaBlock -> s.chunk.assignmentList.flatMap { it.variableList }
                else -> emptyList()
            }
            if (variables.isNotEmpty()) {
                variants += variables.mapNotNull { dec -> when (dec) {
                    is ElvishParameter -> dec.toLookupElement()
                    is ElvishVariable -> dec.toLookupElement()
                    is ElvishPsiBuiltinVariable -> dec.toLookupElement()
                    is ElvishPsiBuiltinValue -> dec.toLookupElement()
                    else -> null //TODO log error?
                }}
            }
            val functions:Collection<ElvishFunctionDeclaration> = when (s) {
                is ElvishFile -> s.topLevelFunctionsDeclarations().toList()
                is BuiltinScope -> s.findFnCommands(null)
                is ElvishLambdaBlock -> s.chunk.fnCommandList
                else -> emptyList()
            }
            if (functions.isNotEmpty()) {
                variants += functions.mapNotNull { when (it) {
                    is ElvishFnCommand -> it.toLookupElement()
                    is ElvishPsiBuiltinCommand -> it.toLookupElement()
                    else -> null
                } }
            }
            return true
        }
    }
}

private fun ElvishFnCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, getCommandName().text + "~").withIcon(AllIcons.Nodes.Function)
}

private fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "$name~").withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

private fun ElvishParameter.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, getVariableName().text).withIcon(AllIcons.Nodes.Parameter)
}

private fun ElvishVariable.toLookupElement(): LookupElement {
    val fullname = getVariableName().text
    return LookupElementBuilder.create(this, fullname)
        .withPresentableText(fullname).withIcon(AllIcons.Nodes.Variable)
}

private fun ElvishPsiBuiltinVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VARIABLE)
}

private fun ElvishPsiBuiltinValue.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VALUE)
}