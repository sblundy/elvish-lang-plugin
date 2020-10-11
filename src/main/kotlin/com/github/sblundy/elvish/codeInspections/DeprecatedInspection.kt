package com.github.sblundy.elvish.codeInspections

import com.github.sblundy.elvish.ElvishBundle
import com.github.sblundy.elvish.lang.version.ElvishBundledService
import com.github.sblundy.elvish.psi.ElvishCommandExpression
import com.github.sblundy.elvish.psi.ElvishVisitor
import com.intellij.codeInspection.LocalInspectionTool
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElementVisitor

class DeprecatedInspection : LocalInspectionTool() {
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor =
        object : ElvishVisitor() {
            val bundledService = ElvishBundledService.getInstance()

            override fun visitCommandExpression(e: ElvishCommandExpression) {
                bundledService.currentVersion(e.project)?.let { version ->
                    val cmd = e.text
                    if (version.isFunctionDeprecated(cmd)) {
                        val message = ElvishBundle.message("inspection.deprecated.message", cmd)
                        holder.registerProblem(e, message, ProblemHighlightType.LIKE_DEPRECATED)
                    }
                }
            }
        }
}