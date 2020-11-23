package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

internal class ElvishVariableReference(element: ElvishVariableRefBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishVariableRefBase?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishVariableReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val climber = object : ElvishScopeClimber() {
            val name = element.variableName
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
                        Pair(p.first + s.chunk.matchingVariables(), p.second)
                    }
                    is ElvishFnCommand -> {
                        val p = s.lambdaArguments?.parameterList?.find { it.matches() }?.let {
                            Pair(listOf(it), false)
                        }?: Pair(emptyList(), true)
                        Pair(p.first + s.chunk.matchingVariables(), p.second)
                    }
                    is ElvishLambda -> {
                        val p = s.lambdaArguments?.parameterList?.find { it.matches()}?.let {
                            Pair(listOf(it), false)
                        }?: Pair(emptyList(), true)
                        Pair(p.first + s.chunk.matchingVariables(), p.second)
                    }
                    is ElvishLambdaBlock -> Pair(s.chunk.matchingVariables(), true)
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

            fun ElvishChunk.matchingVariables(): Collection<ElvishVariableDeclaration> {
                return assignmentList.flatMap { it.variableList.filter { vl -> vl.matches() } }
            }

            fun ElvishVariable.matches(): Boolean = getVariableName().textMatches(name) && namespaceName == null

            fun ElvishParameter.matches(): Boolean = textMatches(name)
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
        climber.climb(element)
        return climber.variants.toTypedArray()
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