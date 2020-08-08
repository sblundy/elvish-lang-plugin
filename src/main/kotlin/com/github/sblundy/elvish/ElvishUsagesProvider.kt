package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishParameter
import com.github.sblundy.elvish.psi.ElvishVariable
import com.intellij.lang.HelpID
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement

class ElvishUsagesProvider : FindUsagesProvider {
    override fun getNodeText(element: PsiElement, useFullName: Boolean): String = getDescriptiveName(element)

    override fun getDescriptiveName(element: PsiElement): String {
        return when (element) {
            is ElvishVariable -> element.text
            is ElvishParameter -> element.text
            else -> ""
        }
    }

    override fun getType(element: PsiElement): String {
        return when (element) {
            is ElvishVariable -> ElvishBundle.message("attribute.VARIABLE.displayName")
            is ElvishParameter -> ElvishBundle.message("attribute.PARAMETER.displayName")
            else -> ""
        }
    }

    override fun getHelpId(psiElement: PsiElement): String? = HelpID.FIND_OTHER_USAGES

    override fun canFindUsagesFor(psiElement: PsiElement): Boolean =
        psiElement is ElvishVariable || psiElement is ElvishParameter
}