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
        val nsClimber = allNamespaceModuleFinder()
        nsClimber.climb(element)
        return (climber.variants + nsClimber.modules).toTypedArray()
    }

    internal open class VariableFinder(private val name: ElvishVariableName) : ElvishBlockClimber() {
        val declarations = mutableListOf<ElvishPsiElement>()
        override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
            val d = s.matchingVariables()
            if (d.isNotEmpty()) {
                declarations += d
            }
            return super.visitElvishFile(s, ctxt)
        }

        override fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
            val d = s.findVariables(name)
            if (d.isNotEmpty()) {
                declarations += d
            }
            return super.visitBuiltinScope(s, ctxt)
        }

        override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
            val p = s.lambdaArguments?.parameterList?.find { it.matches() }?.let {
                Pair(listOf(it), false)
            } ?: Pair(emptyList(), true)
            val (d, c) = p.and(s.chunk.matchingVariables())
            if (d.isNotEmpty()) {
                declarations += d
            }
            return c && super.visitLambdaScope(s, ctxt)
        }

        override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
            val (d, c) = s.chunk.matchingVariables()
            if (d.isNotEmpty()) {
                declarations += d
            }
            return c && super.visitChunkBlock(s, ctxt)
        }

        override fun visitExceptBlock(s: ElvishExceptBlock, ctxt: PsiElement): Boolean {
            val c = if (s.variable.matches()) {
                declarations += s.variable
                false
            } else {
                true
            }
            return visitChunkBlock(s, ctxt) && c
        }

        override fun visitForCommandBlock(s: ElvishForCommand, ctxt: PsiElement): Boolean {
            val c = if (s.variable.matches()) {
                declarations += s.variable
                false
            } else {
                true
            }
            return visitChunkBlock(s, ctxt) && c
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

        fun ElvishParameter.matches(): Boolean = variableName.textMatches(this@VariableFinder.name)

        fun Pair<Collection<ElvishVariableDeclaration>, Boolean>.and(p: Pair<Collection<ElvishVariableAssignment>, Boolean>): Pair<Collection<ElvishPsiElement>, Boolean> {
            return Pair(this.first + p.first, this.second && p.second)
        }
    }

    internal class VariableVariantFinder : ElvishBlockClimber() {
        private val log = logger<VariableVariantFinder>()
        val variants = mutableListOf<LookupElement>()
        override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
            val variables = s.topLevelAssignments().flatMap { it.variableAssignmentList }
            addAllVariables(variables)
            val functions = s.topLevelFunctionsDeclarations().toList()
            addAllFunctions(functions)
            return super.visitElvishFile(s, ctxt)
        }

        override fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
            val variables = s.findVariables(null)
            addAllVariables(variables)
            val functions = s.findFnCommands(null)
            addAllFunctions(functions)
            return super.visitBuiltinScope(s, ctxt)
        }

        override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
            val p = s.lambdaArguments?.parameterList ?: emptyList()
            val variables = p + s.chunk.assignmentList.flatMap { it.variableAssignmentList }
            addAllVariables(variables)
            val functions = s.chunk.fnCommandList
            addAllFunctions(functions)
            return super.visitLambdaScope(s, ctxt)
        }

        override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
            val variables = s.chunk.assignmentList.flatMap { it.variableAssignmentList }
            addAllVariables(variables)
            val functions = s.chunk.fnCommandList
            addAllFunctions(functions)
            return super.visitChunkBlock(s, ctxt)
        }

        override fun visitExceptBlock(s: ElvishExceptBlock, ctxt: PsiElement): Boolean {
            val variables = listOf(s.variable)
            addAllVariables(variables)
            return super.visitExceptBlock(s, ctxt)
        }

        override fun visitForCommandBlock(s: ElvishForCommand, ctxt: PsiElement): Boolean {
            val variables = listOf(s.variable)
            addAllVariables(variables)
            return super.visitForCommandBlock(s, ctxt)
        }

        private fun addAllVariables(variables: Collection<ElvishPsiElement>) {
            if (variables.isNotEmpty()) {
                variants += variables.mapNotNull { dec ->
                    when (dec) {
                        is ElvishParameter -> dec.toLookupElement()
                        is ElvishVariable -> dec.toLookupElement()
                        is ElvishPsiBuiltinVariable -> dec.toLookupElement()
                        is ElvishPsiBuiltinValue -> dec.toLookupElement()
                        else -> {
                            log.warn("unrecognized:" + dec.javaClass.name); null
                        }
                    }
                }
            }
        }

        private fun addAllFunctions(functions: Collection<ElvishPsiElement>) {
            if (functions.isNotEmpty()) {
                variants += functions.mapNotNull {
                    when (it) {
                        is ElvishFnCommand -> it.toVariableLookupElement()
                        is ElvishPsiBuiltinCommand -> it.toVariableLookupElement()
                        else -> {
                            log.warn("unrecognized:" + it.javaClass.name); null
                        }
                    }
                }
            }
        }
    }
}