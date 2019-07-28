package com.github.sblundy.elvish

import com.intellij.lang.Language

class ElvishLanguage private constructor(): Language("Elvish") {
    companion object {
        @JvmField val INSTANCE = ElvishLanguage()
    }
}