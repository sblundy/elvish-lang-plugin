package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ElvishParameterBase extends ASTWrapperPsiElement implements PsiNameIdentifierOwner {
    public ElvishParameterBase(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return getCompound();
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        //TODO is actually a variable name?
        ElvishVariableName ne = ElvishPsiUtils.INSTANCE.newNameElement(name, getProject());
        getCompound().replace(ne);
        return this;
    }

    @NotNull
    public abstract ElvishCompound getCompound();
}
