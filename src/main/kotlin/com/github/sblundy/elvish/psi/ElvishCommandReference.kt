package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

internal class ElvishCommandReference(element: ElvishCommandExpressionBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishCommandExpressionBase?>(element, rangeInElement, true) {
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
        return climber.variants.toTypedArray()
    }

    internal open class CommandFinder(val command: ElvishCommandBareword) : ElvishScopeClimber() {
        val declarations = mutableListOf<ElvishFunctionDeclaration>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
            val d= when (s) {
                is ElvishFile -> s.matchingFnCommands()
                is BuiltinScope -> s.findFnCommands(command)
                is ElvishLambdaBlock -> s.chunk.matchingFnCommands()
                else -> emptyList()
            }
            if (d.isNotEmpty()) {
                declarations += d
            }
            return d.isEmpty()
        }

        fun ElvishFile.matchingFnCommands(): Collection<ElvishFunctionDeclaration> =
            topLevelFunctionsDeclarations().filter { it.commandName.textMatches(command) }

        fun ElvishChunk.matchingFnCommands(): Collection<ElvishFunctionDeclaration> =
            fnCommandList.filter { it.commandName.textMatches(command) }
    }

    internal class CommandVariantFinder(): ElvishScopeClimber() {
        val variants = mutableListOf<LookupElement>()
        override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
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
    return LookupElementBuilder.create(this, this.getCommandName().text).withIcon(AllIcons.Nodes.Function)
}

private fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, this.name).withIcon(ElvishIcons.BUILTIN_FUNCTION)
}