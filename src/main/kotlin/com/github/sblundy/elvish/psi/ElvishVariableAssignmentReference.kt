package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishVariableAssignmentReference(element: ElvishVariableRef, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishVariableRef?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishVariableAssignmentReference>()

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
        val declarations = mutableListOf<ElvishPsiElement>()
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

        private fun ElvishFile.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return topLevelAssignments().flatMap { it.variableAssignmentList.filterIsInstance(ElvishVariable::class.java).filter { vl -> vl.matches() } }
        }

        private fun ElvishChunk.matchingVariables(): Pair<Collection<ElvishVariableAssignment>, Boolean> {
            val variables = assignmentList.flatMap {
                it.variableAssignmentList.filter { vl -> vl.matches() }
            }
            val anyLocal = variables.any {
                it is ElvishLocalScopeVariableAssignment
            }
            val anyUp = variables.any { it is ElvishUpNamespace }
            if (!anyLocal) {
                return Pair(variables, true)
            } else if (!anyUp) {
                return Pair(variables, false)
            }

            return Pair(variables.filterNot { it is ElvishUpNamespace }, false)
        }

        fun ElvishVariableAssignment.matches(): Boolean = variableName.textMatches(name)

        fun ElvishParameter.matches(): Boolean = textMatches(name)

        fun Pair<Collection<ElvishVariableDeclaration>, Boolean>.and(p: Pair<Collection<ElvishVariableAssignment>, Boolean>): Pair<Collection<ElvishPsiElement>, Boolean> {
            return Pair(this.first + p.first, this.second && p.second)
        }
    }

    internal class VariableVariantFinder: ElvishScopeClimber() {
        private val log = logger<VariableVariantFinder>()
        val variants = mutableListOf<LookupElement>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
            val variables = when (s) {
                is ElvishFile -> s.topLevelAssignments().flatMap { it.variableAssignmentList }
                is BuiltinScope -> s.findVariables(null)
                is ElvishExceptBlock -> listOf(s.variable) + s.chunk.assignmentList.flatMap { it.variableAssignmentList }
                is ElvishFnCommand -> {
                    val p =s.lambdaArguments?.parameterList?: emptyList()
                    p + s.chunk.assignmentList.flatMap { it.variableAssignmentList }
                }
                is ElvishLambda -> {
                    val p = s.lambdaArguments?.parameterList?: emptyList()
                    p + s.chunk.assignmentList.flatMap { it.variableAssignmentList }
                }
                is ElvishLambdaBlock -> s.chunk.assignmentList.flatMap { it.variableAssignmentList }
                else -> emptyList()
            }
            if (variables.isNotEmpty()) {
                variants += variables.mapNotNull { dec -> when (dec) {
                    is ElvishParameter -> dec.toLookupElement()
                    is ElvishVariable -> dec.toLookupElement()
                    is ElvishPsiBuiltinVariable -> dec.toLookupElement()
                    is ElvishPsiBuiltinValue -> dec.toLookupElement()
                    else -> {log.warn("unrecognized:"+ dec.javaClass.name); null}
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
                    is ElvishFnCommand -> it.toVariableLookupElement()
                    is ElvishPsiBuiltinCommand -> it.toVariableLookupElement()
                    else -> {log.warn("unrecognized:"+ it.javaClass.name); null}
                } }
            }
            return true
        }
    }
}