package com.github.sblundy.elvish

import com.github.sblundy.elvish.psi.*
import com.intellij.ide.structureView.*
import com.intellij.ide.structureView.impl.common.PsiTreeElementBase
import com.intellij.ide.util.treeView.smartTree.Sorter
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class ElvishStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(psiFile: PsiFile): StructureViewBuilder {
        return object : TreeBasedStructureViewBuilder() {
            override fun createStructureViewModel(editor: Editor?): StructureViewModel {
                return ElvishStructureViewModel(psiFile, editor, ElvishStructureViewFile(psiFile as ElvishFile)).
                withSuitableClasses(ElvishFile::class.java, ElvishFnCommand::class.java, ElvishVariableAssignment::class.java).
                withSorters(Sorter.ALPHA_SORTER)
            }
        }
    }

    private class ElvishStructureViewModel(file: PsiFile, editor: Editor?, root: StructureViewTreeElement) :
        StructureViewModelBase(file, editor, root), StructureViewModel.ElementInfoProvider {
        override fun isAlwaysShowsPlus(element: StructureViewTreeElement?): Boolean = false

        override fun isAlwaysLeaf(element: StructureViewTreeElement?): Boolean = when (element) {
            is ElvishStructureViewFile -> false
            is ElvishStructureViewFunction -> false
            else -> true
        }
    }

    private class ElvishStructureViewFile(psiFile: ElvishFile) : PsiTreeElementBase<ElvishFile>(psiFile) {
        override fun getChildrenBase(): Collection<StructureViewTreeElement> {
            return element?.let { element ->
                element.chunk.fnCommandList.map {
                    ElvishStructureViewFunction(it)
                } + element.exportedVariables().map {
                    ElvishStructureViewVariable(it)
                }
            } ?: listOf()
        }

        override fun getPresentableText(): String? {
            return element?.name
        }
    }

    private class ElvishStructureViewFunction(fn: ElvishFnCommand) : PsiTreeElementBase<ElvishFnCommand>(fn) {
        override fun getChildrenBase(): Collection<StructureViewTreeElement> = element?.let { element ->
            element.chunk.fnCommandList.map {
                ElvishStructureViewFunction(it)
            }
        } ?: emptyList()

        override fun getPresentableText(): String? = element?.commandName?.text
    }

    private class ElvishStructureViewVariable(a: ElvishVariableDeclaration) :
        PsiTreeElementBase<ElvishVariableDeclaration>(a) {
        override fun getChildrenBase(): Collection<StructureViewTreeElement> = emptyList()
        override fun getPresentableText(): String? = element?.variableName?.text
    }
}