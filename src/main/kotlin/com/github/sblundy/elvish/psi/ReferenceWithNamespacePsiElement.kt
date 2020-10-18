package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement

interface ReferenceWithNamespacePsiElement : PsiElement {
    val targetElement: PsiElement
    val namespacePathElements: List<PsiElement>
    val namespaceLength: Int
    val hasNamespace: Boolean
}