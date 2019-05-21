package com.github.sblundy.elvish.psi

import com.github.sblundy.elvish.ElvishLanguage
import com.intellij.psi.tree.IElementType
import org.jetbrains.annotations.*

class ElvishElementType(@NonNls debugName: String) : IElementType(debugName, ElvishLanguage.INSTANCE)