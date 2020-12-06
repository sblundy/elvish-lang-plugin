package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.*
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class ElvishUsagesProvider : FindUsagesProvider {
    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = getDescriptiveName(element)

    override fun getDescriptiveName(element: PsiElement): String {
        return when (element) {
            is ElvishVariableAssignment -> element.text
            is ElvishParameter -> element.text
            is ElvishFnCommand -> element.commandName.text
            else -> ""
        }
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is ElvishVariableAssignment -> "variable"
            is ElvishVariableRef -> "variable"
            is ElvishNamespaceVariableRef -> "variable"
            is ElvishParameter -> "parameter"
            is ElvishFnCommand -> "function"
            is ElvishCommandExpression -> "function"
            is ElvishPsiBuiltinCommand -> "builtin command"
            is ElvishPsiBuiltinVariable -> "builtin variable"
            else -> ""
        }
    }

    override fun getHelpId(psiElement: PsiElement): String = HelpID.FIND_OTHER_USAGES

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean =
        psiElement is ElvishVariableAssignment ||
                psiElement is ElvishParameter ||
                psiElement is ElvishFnCommand ||
                psiElement is ElvishPsiBuiltinCommand ||
                psiElement is ElvishPsiBuiltinVariable
}