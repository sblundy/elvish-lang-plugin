package com.github.sblundy.elvish.psi

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
import icons.ElvishIcons


internal fun ElvishFnCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, this.commandName.text).withIcon(AllIcons.Nodes.Function)
}

internal fun ElvishPsiBuiltinCommand.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, this.name).withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

internal fun ElvishFnCommand.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "${commandName.text}~").withIcon(AllIcons.Nodes.Function)
}

internal fun ElvishPsiBuiltinCommand.toVariableLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, "$name~").withIcon(ElvishIcons.BUILTIN_FUNCTION)
}

internal fun ElvishParameter.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, variableName.text).withIcon(AllIcons.Nodes.Parameter)
}

internal fun ElvishVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, variableName.text).withIcon(AllIcons.Nodes.Variable)
}

internal fun ElvishPsiBuiltinVariable.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VARIABLE)
}

internal fun ElvishPsiBuiltinValue.toLookupElement(): LookupElement {
    return LookupElementBuilder.create(this, name).withIcon(ElvishIcons.BUILTIN_VALUE)
}

internal fun toNSLookupElement(name: String): LookupElement {
    return LookupElementBuilder.create("$name:").withIcon(AllIcons.Nodes.Module)
}