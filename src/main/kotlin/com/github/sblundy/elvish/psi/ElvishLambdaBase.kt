package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.psi.ElvishPsiUtils.findVariableInParentScope
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

    abstract val lambdaArguments: ElvishLambdaArguments?
}