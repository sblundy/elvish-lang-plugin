package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.NonNls

class ElvishTokenType(@NonNls debugName: String): IElementType(debugName, ElvishLanguage.INSTANCE) {
    override fun toString(): String = "ElvishTokenType." + super.toString()
}