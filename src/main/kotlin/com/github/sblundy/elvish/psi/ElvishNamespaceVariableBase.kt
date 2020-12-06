package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.impl.ElvishVariableAssignmentImpl
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiReference
import javax.swing.Icon

abstract class ElvishNamespaceVariableBase(node: ASTNode) : ElvishVariableAssignmentImpl(node), ElvishNamespaceVariableAssignment {
     override fun getName(): String? = variableName.text

    override fun isWritable(): Boolean {
        return super.isWritable() && (namespaceIdentifier is ElvishNamespaceName || namespaceIdentifier is ElvishLocalNamespace || namespaceIdentifier is ElvishUpNamespace)
    }

    override fun getTextOffset(): Int = variableName.textOffset

    override fun getIcon(flags: Int): Icon? = AllIcons.Nodes.Variable

    override fun getPresentation(): ItemPresentation? = ElvishBasicItemPresentation(variableName.text, AllIcons.Nodes.Variable)
    override fun getReference(): PsiReference? {
        return ElvishNamespaceVariableReference(this, variableName.textRangeInParent)
    }
}