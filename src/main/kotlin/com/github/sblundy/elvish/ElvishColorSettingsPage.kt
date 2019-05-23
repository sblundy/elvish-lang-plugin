package com.github.sblundy.elvish

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import icons.ElvishIcons
import java.util.*
import javax.swing.Icon

class ElvishColorSettingsPage : ColorSettingsPage {
    override fun getHighlighter(): SyntaxHighlighter = ElvishSyntaxHighlighter()
    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? =
        additionalHighlightingTag

    override fun getIcon(): Icon? = ElvishIcons.FILE_ICON
    override fun getColorDescriptors() = emptyArray<ColorDescriptor>()
    override fun getAttributeDescriptors() = elvishAttributesDescriptors
    override fun getDisplayName() = "Elvish"
    override fun getDemoText() = sampleElvishScript
}

private val additionalHighlightingTag = Collections.unmodifiableMap(
    mutableMapOf(
        "command" to ElvishSyntaxHighlighter.COMMAND,
        "variable" to ElvishSyntaxHighlighter.VARIABLE,
        "variableRef" to ElvishSyntaxHighlighter.VARIABLE_REF,
        "string" to ElvishSyntaxHighlighter.STRING
    )
)

private val elvishAttributesDescriptors = arrayOf(
    AttributesDescriptor("Command", ElvishSyntaxHighlighter.COMMAND),
    AttributesDescriptor("String", ElvishSyntaxHighlighter.STRING),
    AttributesDescriptor("Variable Declaration", ElvishSyntaxHighlighter.VARIABLE),
    AttributesDescriptor("Variable Reference", ElvishSyntaxHighlighter.VARIABLE_REF),
    AttributesDescriptor("Comment", ElvishSyntaxHighlighter.COMMENT)
)

private val sampleElvishScript: String = """
    # Comment
    <command>command</command> -f argument <string>'single quoted string'</string>
    <variable>x</variable> = <string>'value'</string>
    put <variableRef>${'$'}x</variableRef>
""".trimIndent()