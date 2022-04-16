package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishNamespaceIdentifier
import com.github.sblundy.elvish.psi.ElvishSpecialScopeVariableRef
import com.github.sblundy.elvish.psi.ElvishSpecialScopeVariableReference
import com.github.sblundy.elvish.psi.ElvishVariableName
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishSpecialScopeVariableRefMixin(node: ASTNode?) : ElvishVariableReferenceImpl(node), ElvishSpecialScopeVariableRef {
    override fun getReference(): PsiReference = object :
        ElvishSpecialScopeVariableReference<ElvishSpecialScopeVariableRef>(this, variableName.textRangeInParent) {
        override fun getVariableName(): ElvishVariableName = element.variableName

        override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier = element.namespaceIdentifier
    }
}