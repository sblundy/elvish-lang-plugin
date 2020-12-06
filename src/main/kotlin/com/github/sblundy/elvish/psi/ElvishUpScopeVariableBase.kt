package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.impl.ElvishVariableAssignmentImpl
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiReference
import javax.swing.Icon

abstract class ElvishUpScopeVariableBase(node: ASTNode) : ElvishVariableAssignmentImpl(node), ElvishUpScopeVariableAssignment {
     override fun getName(): String? = variableName.text

    override fun getTextOffset(): Int = variableName.textOffset

    override fun getIcon(flags: Int): Icon? = AllIcons.Nodes.Variable

    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(variableName.text, AllIcons.Nodes.Variable)

    override fun getReference(): PsiReference? {
        if (namespaceIdentifier is ElvishLocalNamespace) {
            return null
        }
        return object : ElvishSpecialScopeVariableReference<ElvishUpScopeVariableAssignment>(this, variableName.textRangeInParent) {
            override fun getVariableName(): ElvishVariableName {
                return element.variableName
            }

            override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier {
                return element.namespaceIdentifier
            }
        }
    }
}