package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ElvishVariableBase extends ASTWrapperPsiElement implements PsiNameIdentifierOwner {
    public ElvishVariableBase(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public PsiElement getNameIdentifier() {
        return getVariableName();
    }

    @Override
    public String getName() {
        return getVariableName().getText();
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        ElvishVariableName ne = ElvishPsiUtils.INSTANCE.newNameElement(name, getProject());
        getVariableName().replace(ne);
        return this;
    }

    @NotNull
    public abstract ElvishVariableName getVariableName();
}
