package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*

internal class ElvishCommandReference(element: ElvishCommandExpression, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishCommandExpression?>(element, rangeInElement, true) {
    private val log = logger<ElvishCommandReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve()
        return if (results.size == 1) results[0].element else null
    }

    private fun multiResolve(): Array<ResolveResult> {
        val climber = CommandFinder(element.commandName)

        climber.climb(element)

        return climber.declarations.map { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, element.project)
        element.commandName.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val climber = CommandVariantFinder()
        climber.climb(element)
        val nsClimber = allNamespaceModuleFinder()
        nsClimber.climb(element)
        return (climber.variants + nsClimber.modules).toTypedArray()
    }

    internal open class CommandFinder(val command: ElvishCommandBareword) : ElvishBlockClimber() {
        val declarations = mutableListOf<ElvishFunctionDeclaration>()
        override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
            val d = s.matchingFnCommands()

            if (d.isNotEmpty()) {
                declarations += d
            }
            return d.isEmpty() && return super.visitElvishFile(s, ctxt)
        }

        override fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
            val d = s.findFnCommands(command)

            if (d.isNotEmpty()) {
                declarations += d
            }
            return d.isEmpty() && return super.visitBuiltinScope(s, ctxt)
        }

        override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
            val d = s.chunk.matchingFnCommands()

            if (d.isNotEmpty()) {
                declarations += d
            }
            return d.isEmpty() && return super.visitLambdaScope(s, ctxt)
        }

        override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
            val d = s.chunk.matchingFnCommands()

            if (d.isNotEmpty()) {
                declarations += d
            }

            return d.isEmpty() && super.visitChunkBlock(s, ctxt)
        }

        private fun ElvishFile.matchingFnCommands(): Collection<ElvishFunctionDeclaration> =
            chunk.matchingFnCommands()

        private fun ElvishChunk.matchingFnCommands(): Collection<ElvishFunctionDeclaration> =
            fnCommandList.filter { it.commandName.textMatches(command) }
    }

    internal class CommandVariantFinder : ElvishBlockClimber() {
        private val log = logger<CommandVariantFinder>()
        val variants = mutableListOf<LookupElement>()
        override fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
            val functions = s.exportedFunctions()
            addAll(functions)
            return super.visitElvishFile(s, ctxt)
        }

        override fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
            val functions = s.findFnCommands(null)
            addAll(functions)
            return super.visitBuiltinScope(s, ctxt)
        }

        override fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
            val functions = s.chunk.fnCommandList
            addAll(functions)
            return super.visitLambdaScope(s, ctxt)
        }

        override fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
            val functions = s.chunk.fnCommandList
            addAll(functions)
            return super.visitChunkBlock(s, ctxt)
        }

        private fun addAll(functions: Collection<ElvishFunctionDeclaration>) {
            variants += functions.map { it.toCommandLookupElement() }
        }
    }
}