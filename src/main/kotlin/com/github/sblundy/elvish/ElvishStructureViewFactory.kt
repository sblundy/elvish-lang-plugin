package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.ElvishFile
import com.github.sblundy.elvish.psi.ElvishFnStatement
import com.intellij.icons.AllIcons
import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import javax.swing.Icon

class ElvishStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder? {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return ElvishStructureViewModel(psiFile, editor, ElvishStructureViewFile(psiFile as ElvishFile))
            }
        }
    }

    private class ElvishStructureViewModel(file: PsiFile, editor: Editor?, root: StructureViewTreeElement):
        StructureViewModelBase(file, editor, root), StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false

        override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean = when(element) {
            is ElvishStructureViewFile -> false
            else -> true
        }
    }

    private class ElvishStructureViewFile(psiFile: ElvishFile): PsiTreeElementBase<ElvishFile>(psiFile) {
        override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> {
            val functions = element?.findChildrenByClass(ElvishFnStatement::class.java)
            return functions?.let {
                it.map { ElvishStructureViewFunction(it) }.toMutableList<StructureViewTreeElement>()
            } ?: mutableListOf()
        }

        override fun getPresentableText(): String? {
            return element?.name
        }
    }

    private class ElvishStructureViewFunction(fn: ElvishFnStatement): PsiTreeElementBase<ElvishFnStatement>(fn) {
        override fun getChildrenBase(): MutableCollection<StructureViewTreeElement> = mutableListOf()
        override fun getPresentableText(): String? = element?.variable?.text
        override fun getIcon(open: Boolean): Icon = AllIcons.Nodes.Method
    }
}