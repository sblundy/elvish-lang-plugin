package com.github.sblundy.elvish

import com.intellij.AbstractBundle
import org.jetbrains.annotations.PropertyKey

const val BUNDLE: String = "com.github.sblundy.elvish.ElvishBundle"

object ElvishBundle : AbstractBundle(BUNDLE) {
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params:Any) : String {
        return getMessage(key, *params)
    }
}