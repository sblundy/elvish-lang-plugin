package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import icons.ElvishIcons

internal class ElvishNamespaceCommandReference(element: ElvishCommandExpressionBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishCommandExpressionBase?>(element, rangeInElement, true) {
    private val log = logger<ElvishCommandReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve()
        return if (results.size == 1) results[0].element else null
    }

    private fun multiResolve(): Array<ResolveResult> {
        val climber = object : ElvishScopeClimber() {
            val name = element.commandBareword
            val ns = element.namespaceName!!
            val declarations = mutableListOf<ElvishFunctionDeclaration>()
            override fun visitScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
                val d= when (s) {
                    is BuiltinScope -> s.findFnCommands(ns, name)
                    else -> emptyList()
                }
                if (d.isNotEmpty()) {
                    declarations += d
                }
                return d.isEmpty()
            }
        }

        climber.climb(element.parent)

        return climber.declarations.map { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, element.project)
        element.commandBareword.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val climber = object : ElvishScopeClimber() {
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
        climber.climb(element.parent)
        return climber.variants.toTypedArray()
    }
}

private fun ElvishFnCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, this.getCommandName().text).withIcon(AllIcons.Nodes.Function)
}

private fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, this.name).withIcon(ElvishIcons.BUILTIN_FUNCTION)
}