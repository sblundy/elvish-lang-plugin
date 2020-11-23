package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishFileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiFileFactory

object ElvishPsiUtils {
    fun newNameElement(name: String, myProject: Project): ElvishVariableName {
        val file = createDummyFile("$name = 1", myProject)
        val chunk = file.firstChild as ElvishChunk
        return chunk.assignmentList[0].variableList[0].getVariableName()
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