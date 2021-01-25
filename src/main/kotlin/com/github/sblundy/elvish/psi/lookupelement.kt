package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import icons.ElvishIcons


internal fun ElvishFunctionDeclaration.toCommandLookupElement(): LookupElement {
    return when (this) {
        is ElvishPsiBuiltinCommand -> toCommandLookupElement()
        else -> LookupElementBuilder.create(this, commandName.text).withIcon(AllIcons.Nodes.Function)
    }
}

internal fun ElvishPsiBuiltinCommand.toCommandLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, this.name).withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

internal fun ElvishFunctionDeclaration.toVariableLookupElement(): LookupElement {
    return when (this) {
        is ElvishPsiBuiltinCommand -> toVariableLookupElement()
        else ->  LookupElementBuilder.create(this, "${commandName.text}~").withIcon(AllIcons.Nodes.Function)
    }
}

internal fun ElvishPsiBuiltinCommand.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "$name~").withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

internal fun ElvishVariableDeclaration.toVariableLookupElement(): LookupElement {
    return when (this) {
        is ElvishParameter -> toVariableLookupElement()
        is ElvishPsiBuiltinVariable -> toVariableLookupElement()
        is ElvishPsiBuiltinValue -> toVariableLookupElement()
        else -> LookupElementBuilder.create(this, variableName.text).withIcon(AllIcons.Nodes.Variable)
    }
}

internal fun ElvishParameter.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, variableName.text).withIcon(AllIcons.Nodes.Parameter)
}

internal fun ElvishPsiBuiltinVariable.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VARIABLE)
}

internal fun ElvishPsiBuiltinValue.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VALUE)
}

internal fun ElvishLValueVariable.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, variableName.text).withIcon(AllIcons.Nodes.Parameter)
}

internal fun toNSLookupElement(name: String): LookupElement {
    return LookupElementBuilder.create("$name:").withIcon(AllIcons.Nodes.Module)
}

internal fun ElvishLibModuleSpec.toNSLookupElement(trim: Int=0): LookupElement {
    return toNSLookupElement(variableNameList.drop(trim).joinToString(":") { it.text })
}

internal fun ElvishRelativeModuleSpec.toNSLookupElement(trim: Int=0): LookupElement {
    return toNSLookupElement(variableNameList.drop(trim).joinToString(":") { it.text })
}