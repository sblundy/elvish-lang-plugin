package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory
import org.jetbrains.annotations.NotNull

object ElvishPsiUtils {
    fun findVariableInParentScope(name: @NotNull String, ns: @NotNull List<String>, e: @NotNull PsiElement): @NotNull Collection<ElvishVariableDeclaration> {
        return (findParentScope(e) ?: return emptyList()).findVariables(name, ns)
    }

    fun findParentScope(e: PsiElement): ElvishVariableScope? {
        var parent: PsiElement? = e.parent
        while (parent != null) {
            if (parent is ElvishFile) {
                break
            } else if (parent is ElvishVariableScope) {
                return parent
            }
            parent = parent.parent
        }
        return null
    }

    fun newNameElement(name: String, myProject: Project): ElvishVariableName {
        val file = createDummyFile("$name = 1", myProject)
        val chunk = file.firstChild as ElvishChunk
        return chunk.assignmentList[0].variableList[0].variableName
    }


    private fun createDummyFile(content: String, myProject: Project): PsiFile {
        val psiFileFactory = PsiFileFactory.getInstance(myProject)
        return psiFileFactory.createFileFromText(
            "dummy." + ElvishFileType.INSTANCE.defaultExtension,
            ElvishFileType.INSTANCE,
            content
        )
    }
}