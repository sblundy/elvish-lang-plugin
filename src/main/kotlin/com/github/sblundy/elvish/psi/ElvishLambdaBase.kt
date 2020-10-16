package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.findVariableInParentScope
import com.github.sblundy.elvish.psi.ElvishPsiUtils.processVariablesInParentScope
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode

abstract class ElvishLambdaBase(node: ASTNode) : ASTWrapperPsiElement(node), ElvishVariableScope {
    override fun findVariables(name: String, ns: List<String>): Collection<ElvishVariableDeclaration> {
        val found = lambdaArguments?.let { lambdaArguments ->
            if (ns.isEmpty()) {
                lambdaArguments.parameterList.filter { it.nameMatches(name, ns) }
            } else {
                listOf()
            }
        } ?: listOf()

        return if (found.isNotEmpty()) { found } else findVariableInParentScope(name, ns, this)
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