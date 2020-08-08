package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

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

    @Override
    public int getTextOffset() {
        return getVariableName().getTextOffset();
    }

    @NotNull
    public abstract ElvishVariableName getVariableName();

    @NotNull
    public abstract List<ElvishNamespaceName> getNamespaceNameList();

    public boolean nameMatches(@NotNull String name, @NotNull List<String> ns) {
        if (!name.equals(getName())) {
            return false;
        } else if (ns.size() != getNamespaceNameList().size()) {
            return false;
        }

        for (int i = 0; i < ns.size(); i++) {
            if (!ns.get(i).equals(getNamespaceNameList().get(i).getText())) {
                return false;
            }
        }

        return true;
    }
}
