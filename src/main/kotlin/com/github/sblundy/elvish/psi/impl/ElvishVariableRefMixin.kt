package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishVariableAssignmentReference
import com.github.sblundy.elvish.psi.ElvishVariableRef
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishVariableRefMixin(node: ASTNode?) : ElvishVariableReferenceImpl(node), ElvishVariableRef {
    override fun getReference(): PsiReference = ElvishVariableAssignmentReference(this, variableName.textRangeInParent)
}