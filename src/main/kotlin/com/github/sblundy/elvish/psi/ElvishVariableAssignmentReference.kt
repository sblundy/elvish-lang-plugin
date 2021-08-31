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
        return results.firstOrNull()?.element
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

    internal open class VariableFinder(private val target: ElvishVariableName) : ElvishBlockClimber() {
        val declarations = mutableListOf<ElvishPsiElement>()
        override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
            val d = s.matchingVariables()
            if (d.isNotEmpty()) {
                declarations += d
            }
            return super.visitElvishFile(s, ctxt)
        }

        override fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
            val d = s.findVariables(target)
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
            val c = if (s.matches()) {
                declarations += s
                false
            } else {
                true
            }
            return visitChunkBlock(s, ctxt) && c
        }

        override fun visitForCommandBlock(s: ElvishForCommand, ctxt: PsiElement): Boolean {
            val c = if (s.matches()) {
                declarations += s
                false
            } else {
                true
            }
            return visitChunkBlock(s, ctxt) && c
        }

        private fun ElvishFile.matchingVariables(): Collection<ElvishVariableDeclaration> {
            return exportedVariables().filter { vl -> vl.matches() }
        }

        private fun ElvishChunk.matchingVariables(): Pair<Collection<ElvishLValueVariable>, Boolean> {
            val variables = variableDeclarations.filter { vl -> vl.matches() }
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

        private fun ElvishLValueVariable.matches(): Boolean = variableName.textMatches(target)

        fun Pair<Collection<ElvishLValueVariable>, Boolean>.and(p: Pair<Collection<ElvishLValueVariable>, Boolean>): Pair<Collection<ElvishPsiElement>, Boolean> {
            return Pair(this.first + p.first, this.second && p.second)
        }
    }

    internal class VariableVariantFinder : ElvishBlockClimber() {
        private val log = logger<VariableVariantFinder>()
        val variants = mutableListOf<LookupElement>()
        override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
            val variables = s.exportedVariables().map { it.toVariableLookupElement() }
            addAllVariables(variables)
            val functions = s.exportedFunctions().map { it.toVariableLookupElement() }
            addAllVariables(functions)
            return super.visitElvishFile(s, ctxt)
        }

        override fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
            val variables = s.findVariables(null).map { it.toVariableLookupElement() }
            addAllVariables(variables)
            val functions = s.findFnCommands(null).map { it.toVariableLookupElement() }
            addAllVariables(functions)
            return super.visitBuiltinScope(s, ctxt)
        }

        override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
            val p = s.lambdaArguments?.parameterList?.map { it.toVariableLookupElement() } ?: emptyList()
            val variables = p + s.chunk.variableDeclarations.map { it.toVariableLookupElement() }
            addAllVariables(variables)
            val functions = s.chunk.fnCommandList.map { it.toVariableLookupElement() }
            addAllVariables(functions)
            return super.visitLambdaScope(s, ctxt)
        }

        override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
            val variables = s.chunk.variableDeclarations.map { it.toVariableLookupElement() }
            addAllVariables(variables)
            val functions = s.chunk.fnCommandList.map { it.toVariableLookupElement() }
            addAllVariables(functions)
            return super.visitChunkBlock(s, ctxt)
        }

        override fun visitExceptBlock(s: ElvishExceptBlock, ctxt: PsiElement): Boolean {
            val variables = listOf(s.toVariableLookupElement())
            addAllVariables(variables)
            return super.visitExceptBlock(s, ctxt)
        }

        override fun visitForCommandBlock(s: ElvishForCommand, ctxt: PsiElement): Boolean {
            val variables = listOf(s.toVariableLookupElement())
            addAllVariables(variables)
            return super.visitForCommandBlock(s, ctxt)
        }

        private fun addAllVariables(variables: Collection<LookupElement>) {
            if (variables.isNotEmpty()) {
                variants += variables
            }
        }
    }
}