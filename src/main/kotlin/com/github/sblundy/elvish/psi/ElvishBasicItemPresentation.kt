package com.github.sblundy.elvish.psi

import com.intellij.navigation.ItemPresentation
import javax.swing.Icon

internal class ElvishBasicItemPresentation(private val name: String, private val icon: Icon):ItemPresentation {
    override fun getPresentableText(): String? = name

    override fun getLocationString(): String? = null

    override fun getIcon(unused: Boolean): Icon? = icon
}