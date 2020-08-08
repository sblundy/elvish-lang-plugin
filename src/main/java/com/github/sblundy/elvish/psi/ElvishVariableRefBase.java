package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.*;
import org.jetbrains.annotations.NotNull;

public abstract class ElvishVariableRefBase extends ASTWrapperPsiElement implements ElvishVariableRef {
    public ElvishVariableRefBase(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public PsiReference getReference() {
        return new ElvishVariableReference(this, getVariableName().getTextRangeInParent());
    }
}
