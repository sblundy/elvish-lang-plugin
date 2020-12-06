package com.github.sblundy.elvish.psi

import com.intellij.icons.AllIcons
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.util.IncorrectOperationException
import javax.swing.Icon

object ElvishPsiImplUtil {
    @JvmStatic
    fun getReference(e: ElvishNamespaceVariableRef): PsiReference {
        return ElvishNamespaceVariableReference(e, e.variableName.textRangeInParent)
    }
    @JvmStatic
    fun getReference(e: ElvishSpecialScopeVariableRef): PsiReference =
        object: ElvishSpecialScopeVariableReference<ElvishSpecialScopeVariableRef>(e, e.variableName.textRangeInParent) {
            override fun getVariableName(): ElvishVariableName {
                return element.variableName
            }

            override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier {
                return element.namespaceIdentifier
            }
        }

    @JvmStatic
    fun getReference(e:ElvishVariableRef): PsiReference = ElvishVariableAssignmentReference(e, e.variableName.textRangeInParent)

    @JvmStatic fun getReference(e: ElvishCommandExpression): PsiReference = ElvishCommandReference(e, e.commandName.textRangeInParent)

    @JvmStatic
    fun getReference(e: ElvishSpecialScopeCommandExpression): PsiReference =
        ElvishSpecialScopeCommandReference(e, e.commandName.textRangeInParent)

    @JvmStatic
    fun getReference(e: ElvishNamespaceCommandExpression): PsiReference =
        ElvishNamespaceCommandReference(e, e.commandName.textRangeInParent)

    @JvmStatic
    fun getNameIdentifier(e: ElvishVariable): PsiElement = e.variableName

    @JvmStatic
    fun getName(e: ElvishVariable): String = e.variableName.text

    @Throws(IncorrectOperationException::class)
    @JvmStatic
    fun setName(e: ElvishVariable, name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, e.project)
        e.variableName.replace(ne)
        return e
    }

    @JvmStatic fun getTextOffset(e: ElvishVariable): Int = e.variableName.textOffset

    @JvmStatic fun getIcon(e: ElvishVariable, flags: Int): Icon = AllIcons.Nodes.Variable

    @JvmStatic fun getPresentation(e: ElvishVariable): ItemPresentation = ElvishBasicItemPresentation(e.variableName.text, AllIcons.Nodes.Variable)
    @JvmStatic
    fun getNameIdentifier(e: ElvishLocalScopeVariableAssignment): PsiElement = e.variableName

    @JvmStatic
    fun getName(e: ElvishLocalScopeVariableAssignment): String = e.variableName.text

    @Throws(IncorrectOperationException::class)
    @JvmStatic
    fun setName(e: ElvishLocalScopeVariableAssignment, name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, e.project)
        e.variableName.replace(ne)
        return e
    }

    @JvmStatic fun getTextOffset(e: ElvishLocalScopeVariableAssignment): Int = e.variableName.textOffset

    @JvmStatic fun getIcon(e: ElvishLocalScopeVariableAssignment, flags: Int): Icon = AllIcons.Nodes.Variable

    @JvmStatic fun getPresentation(e: ElvishLocalScopeVariableAssignment): ItemPresentation = ElvishBasicItemPresentation(e.variableName.text, AllIcons.Nodes.Variable)
    @JvmStatic fun getName(e: ElvishUpScopeVariableAssignment): String = e.variableName.text

    @JvmStatic fun getTextOffset(e: ElvishUpScopeVariableAssignment): Int = e.variableName.textOffset

    @JvmStatic fun getIcon(e: ElvishUpScopeVariableAssignment, flags: Int): Icon = AllIcons.Nodes.Variable

    @JvmStatic fun getPresentation(e: ElvishUpScopeVariableAssignment): ItemPresentation = ElvishBasicItemPresentation(e.variableName.text, AllIcons.Nodes.Variable)

    @JvmStatic fun getReference(e: ElvishUpScopeVariableAssignment): PsiReference? {
        if (e.namespaceIdentifier is ElvishLocalNamespace) {
            return null
        }
        return object : ElvishSpecialScopeVariableReference<ElvishUpScopeVariableAssignment>(e, e.variableName.textRangeInParent) {
            override fun getVariableName(): ElvishVariableName {
                return element.variableName
            }

            override fun getNamespaceIdentifier(): ElvishNamespaceIdentifier {
                return element.namespaceIdentifier
            }
        }
    }
    @JvmStatic fun getName(e: ElvishNamespaceVariableAssignment): String = e.variableName.text

    @JvmStatic fun isWritable(e: ElvishNamespaceVariableAssignment): Boolean {
        return e.parent.isWritable && (e.namespaceIdentifier is ElvishNamespaceName || e.namespaceIdentifier is ElvishLocalNamespace || e.namespaceIdentifier is ElvishUpNamespace)
    }

    @JvmStatic fun getTextOffset(e: ElvishNamespaceVariableAssignment): Int = e.variableName.textOffset

    @JvmStatic fun getIcon(e: ElvishNamespaceVariableAssignment, flags: Int): Icon = AllIcons.Nodes.Variable

    @JvmStatic fun getPresentation(e: ElvishNamespaceVariableAssignment): ItemPresentation = ElvishBasicItemPresentation(e.variableName.text, AllIcons.Nodes.Variable)
    @JvmStatic fun getReference(e: ElvishNamespaceVariableAssignment): PsiReference {
        return ElvishNamespaceVariableReference(e, e.variableName.textRangeInParent)
    }

    @JvmStatic fun getNameIdentifier(e: ElvishParameter): PsiElement = e.variableName

    @JvmStatic fun getName(e: ElvishParameter): String = e.variableName.text

    @Throws(IncorrectOperationException::class)
    @JvmStatic fun setName(e: ElvishParameter, name: String): PsiElement {
        //TODO is actually a variable name?
        val ne = ElvishPsiUtils.newNameElement(name, e.project)
        e.variableName.replace(ne)
        return e
    }

    @JvmStatic fun getIcon(e: ElvishParameter, flags: Int): Icon = AllIcons.Nodes.Parameter

    @JvmStatic fun getPresentation(e: ElvishParameter): ItemPresentation = ElvishBasicItemPresentation(e.variableName.text, AllIcons.Nodes.Parameter)


    @JvmStatic fun getNameIdentifier(e: ElvishFnCommand): PsiElement = e.commandName

    @JvmStatic fun getName(e: ElvishFnCommand): String {
        return e.commandName.text
    }

    @Throws(IncorrectOperationException::class)
    @JvmStatic fun setName(e: ElvishFnCommand, name: String): PsiElement {
        val ne = ElvishPsiUtils.newNameElement(name, e.project)
        e.commandName.replace(ne)
        return e
    }

    @JvmStatic fun getTextOffset(e: ElvishFnCommand): Int {
        return e.commandName.textOffset
    }

    @JvmStatic fun getIcon(e: ElvishFnCommand, flags: Int): Icon = AllIcons.Nodes.Function

    @JvmStatic fun getPresentation(e: ElvishFnCommand): ItemPresentation = ElvishBasicItemPresentation(e.commandName.text, AllIcons.Nodes.Function)
}