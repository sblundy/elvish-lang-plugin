package com.github.sblundy.elvish.psi.impl

import com.github.sblundy.elvish.psi.*
import com.intellij.lang.ASTNode
import com.intellij.psi.util.PsiTreeUtil

abstract class ElvishChunkMixin(node: ASTNode) : ASTWrapperElvishPsiElement(node), ElvishVariableDeclarationsContainer {
    override val variableDeclarations: List<ElvishVariableDeclaration>
        get() {
            return PsiTreeUtil.getChildrenOfAnyType(this, ElvishVarCommand::class.java, ElvishAssignment::class.java)
                .flatMap {
                    when (it) {
                        is ElvishVarCommand -> it.lValues
                        is ElvishAssignment -> it.lValues.filterIsInstance(ElvishVariableDeclaration::class.java)
                        else -> emptyList()
                    }
                }
        }
}