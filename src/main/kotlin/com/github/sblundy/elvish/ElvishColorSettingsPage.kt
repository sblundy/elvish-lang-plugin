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
        "builtIn" to ElvishSyntaxHighlighter.BUILTIN,
        "command" to ElvishSyntaxHighlighter.COMMAND,
        "variable" to ElvishSyntaxHighlighter.VARIABLE,
        "variableRef" to ElvishSyntaxHighlighter.VARIABLE_REF
    )
)

private val elvishAttributesDescriptors = arrayOf(
    AttributesDescriptor("Command", ElvishSyntaxHighlighter.COMMAND),
    AttributesDescriptor("Keyword", ElvishSyntaxHighlighter.KEYWORD),
    AttributesDescriptor("Built In", ElvishSyntaxHighlighter.BUILTIN),
    AttributesDescriptor("String", ElvishSyntaxHighlighter.STRING),
    AttributesDescriptor("Bracket", ElvishSyntaxHighlighter.BRACKETS),
    AttributesDescriptor("Brace", ElvishSyntaxHighlighter.BRACES),
    AttributesDescriptor("Parenthesis", ElvishSyntaxHighlighter.PARENTHESES),
    AttributesDescriptor("Escape Sequence", ElvishSyntaxHighlighter.ESCAPE_SEQUENCE),
    AttributesDescriptor("Invalid Escape Sequence", ElvishSyntaxHighlighter.INVALID_ESCAPE_SEQUENCE),
    AttributesDescriptor("Variable Declaration", ElvishSyntaxHighlighter.VARIABLE),
    AttributesDescriptor("Variable Reference", ElvishSyntaxHighlighter.VARIABLE_REF),
    AttributesDescriptor("Comment", ElvishSyntaxHighlighter.COMMENT)
)

private val sampleElvishScript: String = """
    # Comment
    <command>command</command> -f argument 'single quoted string'
    <variable>x</variable> = '''s'
    <builtIn>put</builtIn> <variableRef>${'$'}x</variableRef>
    if (<builtIn>has-suffix</builtIn> <variableRef>${'$'}fname</variableRef> .go) {
        <builtIn>echo</builtIn> <variableRef>${'$'}fname</variableRef> "is go f\ile"
    }
""".trimIndent()