package com.github.sblundy.elvish.psi

import com.intellij.psi.PsiElement
import java.lang.UnsupportedOperationException

abstract class ElvishBlockClimber {
    fun climb(start: ElvishPsiElement) {
        var ctxt = start
        var block = start.block
        while (block != null) {
            if (!visitType(block, ctxt)) {
                return
            }
            ctxt = block
            block = block.block
        }
    }

    private fun visitType(b: ElvishBlock, ctxt: PsiElement): Boolean {
        return when (b) {
            is ElvishFile -> visitElvishFile(b, ctxt)
            is BuiltinScope -> visitBuiltinScope(b, ctxt)
            is ElvishFnCommand -> visitFnCommand(b, ctxt)
            is ElvishLambda -> visitLambda(b, ctxt)
            is ElvishIfCommand -> visitIfBlock(b, ctxt)
            is ElvishElIfBlock -> visitElIfBlock(b, ctxt)
            is ElvishElseBlock -> visitElseBlock(b, ctxt)
            is ElvishTryCommand -> visitTryBlock(b, ctxt)
            is ElvishExceptBlock -> visitExceptBlock(b, ctxt)
            is ElvishFinallyBlock -> visitFinallyBlock(b, ctxt)
            is ElvishForCommand -> visitForCommandBlock(b, ctxt)
            is ElvishWhileCommand -> visitWhileCommandBlock(b, ctxt)
            else -> throw UnsupportedOperationException()
        }
    }

    open fun visitBlock(s: ElvishBlock, ctxt: PsiElement): Boolean = true

    open fun visitLexicalScope(s: ElvishLexicalScope, ctxt: PsiElement): Boolean {
        return visitBlock(s, ctxt)
    }

    open fun visitElvishFile(s: ElvishFile, ctxt: PsiElement): Boolean {
        return visitLexicalScope(s, ctxt)
    }

    internal open fun visitBuiltinScope(s: BuiltinScope, ctxt: PsiElement): Boolean {
        return visitLexicalScope(s, ctxt)
    }

    open fun visitLambdaScope(s: ElvishLambdaScope, ctxt: PsiElement): Boolean {
        return visitLexicalScope(s, ctxt)
    }

    open fun visitFnCommand(s: ElvishFnCommand, ctxt: PsiElement): Boolean {
        return visitLambdaScope(s, ctxt)
    }

    open fun visitLambda(s: ElvishLambda, ctxt: PsiElement): Boolean {
        return visitLambdaScope(s, ctxt)
    }

    open fun visitChunkBlock(s: ElvishChunkBlock, ctxt: PsiElement): Boolean {
        return visitBlock(s, ctxt)
    }

    open fun visitIfBlock(s: ElvishIfCommand, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitElIfBlock(s: ElvishElIfBlock, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitElseBlock(s: ElvishElseBlock, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitTryBlock(s: ElvishTryCommand, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitExceptBlock(s: ElvishExceptBlock, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitFinallyBlock(s: ElvishFinallyBlock, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitForCommandBlock(s: ElvishForCommand, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }

    open fun visitWhileCommandBlock(s: ElvishWhileCommand, ctxt: PsiElement): Boolean {
        return visitChunkBlock(s, ctxt)
    }
}