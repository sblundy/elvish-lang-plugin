package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.ElvishBasicItemPresentation
import com.intellij.icons.AllIcons
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import javax.swing.Icon

abstract class ElvishVariableAssignmentMixin(node: ASTNode?) : ElvishVariableAssignmentImpl(node) {

    override fun getTextOffset(): Int = variableName.textOffset

    override fun getIcon(flags: Int): Icon = AllIcons.Nodes.Variable

    override fun getPresentation(): ItemPresentation =
        ElvishBasicItemPresentation(variableName.text, AllIcons.Nodes.Variable)
}