package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.findVariableInParentScope
import com.github.sblundy.elvish.psi.ElvishPsiUtils.processVariablesInParentScope
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ElvishLambdaBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishVariableScope {
    override fun findVariables(ref: ReferenceWithNamespacePsiElement): Collection<ElvishVariableDeclaration> {
        val found = lambdaArguments?.let { lambdaArguments ->
            if (!ref.hasNamespace) {
                lambdaArguments.parameterList.filter { it.nameMatches(ref) }
            } else {
                listOf()
            }
        } ?: listOf()

        return if (found.isNotEmpty()) { found } else findVariableInParentScope(ref, this)
    }

    override fun processVariables(processor: ElvishVariableScope.VariableProcessor) {
        lambdaArguments?.parameterList?.let { parameterList ->
            val c = parameterList.any {
                processor.process(it)
            }
            if (c) {
                processVariablesInParentScope(processor, this)
            }
        }
    }

    abstract val lambdaArguments: ElvishLambdaArguments?
}