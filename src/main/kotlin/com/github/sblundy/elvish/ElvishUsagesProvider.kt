package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.*
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class ElvishUsagesProvider : FindUsagesProvider {
    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = getDescriptiveName(element)

    override fun getDescriptiveName(element: PsiElement): String {
        return when (element) {
            is ElvishVariable -> element.text
            is ElvishParameter -> element.text
            is ElvishFnCommand -> element.variableName.text
            else -> ""
        }
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is ElvishVariable -> "variable"
            is ElvishVariableRef -> "variable"
            is ElvishParameter -> "parameter"
            is ElvishFnCommand -> "function"
            is ElvishCommandExpression -> "function"
            is ElvishPsiBuiltinCommand -> "builtin command"
            is ElvishPsiBuiltinVariable -> "builtin variable"
            else -> ""
        }
    }

    override fun getHelpId(psiElement: PsiElement): String? = HelpID.FIND_OTHER_USAGES

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean =
        psiElement is ElvishVariable || psiElement is ElvishParameter || psiElement is ElvishFnCommand || psiElement is ElvishPsiBuiltinCommand || psiElement is ElvishPsiBuiltinVariable
}