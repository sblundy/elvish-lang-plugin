package com.github.sblundy.elvish.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiReference

abstract class ElvishSpecialScopeVariableRefBase(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishSpecialScopeVariableRef {
    override fun getReference(): PsiReference? =
       object: ElvishSpecialScopeVariableReference<ElvishSpecialScopeVariableRef>(this, variableName.textRangeInParent) {
           override fun getVariableName(): ElvishVariableName {
               return element.variableName
           }

           override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier {
               return element.namespaceIdentifier
           }
       }
}