package com.github.sblundy.elvish

import com.github.sblundy.elvish.lang.allLanguageParseFlags
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import icons.ElvishIcons
import java.util.*
import javax.swing.Icon

class ElvishColorSettingsPage : ColorSettingsPage {
    override fun getHighlighter(): SyntaxHighlighter = ElvishSyntaxHighlighter(allLanguageParseFlags)
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
        "commandCapture" to ElvishSyntaxHighlighter.COMMAND_CAPTURE,
        "parameter" to ElvishSyntaxHighlighter.PARAMETER,
        "variable" to ElvishSyntaxHighlighter.VARIABLE,
        "variableRef" to ElvishSyntaxHighlighter.VARIABLE_REF
    )
)

private val elvishAttributesDescriptors = arrayOf(
    AttributesDescriptor(ElvishBundle.message("attribute.COMMAND.displayName"), ElvishSyntaxHighlighter.COMMAND),
    AttributesDescriptor(ElvishBundle.message("attribute.COMMAND_CAPTURE.displayName"), ElvishSyntaxHighlighter.COMMAND_CAPTURE),
    AttributesDescriptor(ElvishBundle.message("attribute.KEYWORD.displayName"), ElvishSyntaxHighlighter.KEYWORD),
    AttributesDescriptor(ElvishBundle.message("attribute.SEP_KEYWORD.displayName"), ElvishSyntaxHighlighter.SEP_KEYWORD),
    AttributesDescriptor(ElvishBundle.message("attribute.BUILTIN.displayName"), ElvishSyntaxHighlighter.BUILTIN),
    AttributesDescriptor(ElvishBundle.message("attribute.STRING.displayName"), ElvishSyntaxHighlighter.STRING),
    AttributesDescriptor(ElvishBundle.message("attribute.BRACKETS.displayName"), ElvishSyntaxHighlighter.BRACKETS),
    AttributesDescriptor(ElvishBundle.message("attribute.BRACES.displayName"), ElvishSyntaxHighlighter.BRACES),
    AttributesDescriptor(ElvishBundle.message("attribute.PARENTHESES.displayName"), ElvishSyntaxHighlighter.PARENTHESES),
    AttributesDescriptor(ElvishBundle.message("attribute.OPERATOR.displayName"), ElvishSyntaxHighlighter.OPERATOR),
    AttributesDescriptor(ElvishBundle.message("attribute.PARAMETER.displayName"), ElvishSyntaxHighlighter.PARAMETER),
    AttributesDescriptor(ElvishBundle.message("attribute.ESCAPE_SEQUENCE.displayName"), ElvishSyntaxHighlighter.ESCAPE_SEQUENCE),
    AttributesDescriptor(ElvishBundle.message("attribute.INVALID_ESCAPE_SEQUENCE.displayName"), ElvishSyntaxHighlighter.INVALID_ESCAPE_SEQUENCE),
    AttributesDescriptor(ElvishBundle.message("attribute.VARIABLE.displayName"), ElvishSyntaxHighlighter.VARIABLE),
    AttributesDescriptor(ElvishBundle.message("attribute.VARIABLE_REF.displayName"), ElvishSyntaxHighlighter.VARIABLE_REF),
    AttributesDescriptor(ElvishBundle.message("attribute.COMMENT.displayName"), ElvishSyntaxHighlighter.COMMENT)
)

private val sampleElvishScript: String = """
    # Comment
    <command>command</command> -f argument 'single quoted string'
    <variable>x</variable> = '''s'
    <builtIn>put</builtIn> <variableRef>${'$'}x</variableRef>
    if <commandCapture>(</commandCapture><builtIn>has-suffix</builtIn> <variableRef>${'$'}fname</variableRef> .go<commandCapture>)</commandCapture> {
        <builtIn>echo</builtIn> "Package name is "<commandCapture>(</commandCapture><command>head</command> -n 1 <variableRef>${'$'}fname</variableRef> | <command>cut</command> -c 9-<commandCapture>)</commandCapture>
    } else {
        <builtIn>echo</builtIn> <variableRef>${'$'}fname</variableRef> "is not go f\ile"
    }
    <variable>y</variable> = [<parameter>x</parameter> <parameter>@y</parameter>]{ <builtIn>echo</builtIn> <variableRef>${'$'}x</variableRef> }
""".trimIndent()