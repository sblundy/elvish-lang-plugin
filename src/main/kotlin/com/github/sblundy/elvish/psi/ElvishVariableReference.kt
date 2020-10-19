package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.newNameElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import com.intellij.openapi.diagnostic.logger
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.util.ArrayUtilRt

internal class ElvishVariableReference(element: ElvishVariableRefBase, rangeInElement: TextRange?) :
    PsiReferenceBase<ElvishVariableRefBase?>(element, rangeInElement, true),
    PsiPolyVariantReference {
    private val log = logger<ElvishVariableReference>()

    override fun resolve(): PsiElement? {
        val results = multiResolve(false)
        return if (results.size == 1) results[0].element else null
    }

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> {
        val scope = ElvishPsiUtils.findParentScope(element) ?: return ResolveResult.EMPTY_ARRAY

        return scope.findVariables(element).mapNotNull { declaration ->
            PsiElementResolveResult(declaration)
        }.toTypedArray()
    }

    override fun handleElementRename(name: String): PsiElement {
        val ne = newNameElement(name, element.project)
        element.variableName.replace(ne)
        return element
    }

    override fun getVariants(): Array<Any> {
        val scope = ElvishPsiUtils.findParentScope(element) ?: return ArrayUtilRt.EMPTY_OBJECT_ARRAY
        val variants = mutableListOf<Any>()
        scope.processVariables { dec ->
            when (dec) {
                is ElvishParameter -> variants.add(dec.toLookupElement())
                is ElvishVariable -> variants.add(dec.toLookupElement())
                else -> log.debug("unknown type:" + dec.javaClass.name)
            }
            true
        }
        scope.processFnCommands { dec ->
            when (dec) {
                is ElvishFnCommand -> variants.add(dec.toLookupElement())
                else -> log.debug("unknown type:" + dec.javaClass.name)
            }
            true
        }
        return variants.toTypedArray()
    }
}

private fun ElvishFnCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, variableName.text + "~").withIcon(AllIcons.Nodes.Function)
}

private fun ElvishParameter.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, compound.text).withIcon(AllIcons.Nodes.Parameter)
}

private fun ElvishVariable.toLookupElement(): LookupElement {
    val namespacePrefix = namespaceName?.let { namespaceName -> namespaceName.variableNameList.map { it.text }}?: emptyList()
    val fullname = (namespacePrefix+variableName.text).joinToString(":")
    return LookupElementBuilder.create(this, fullname)
        .withPresentableText(fullname).withIcon(AllIcons.Nodes.Variable)
}