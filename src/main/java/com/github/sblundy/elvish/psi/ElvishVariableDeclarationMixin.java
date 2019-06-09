package com.github.sblundy.elvish.psi;
/*
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ElvishVariableDeclarationMixin extends ASTWrapperPsiElement implements PsiNamedElement, ElvishVariableDeclaration {
    public ElvishVariableDeclarationMixin(@NotNull ASTNode node) {
        super(node);
    }

    @Nullable
    @Override
    public String getName() {
        final PsiElement name = findChildByType(ElvishTypes.BAREWORD);
        return name == null ? null : name.getText();
    }

    @Override
    public PsiElement setName(@NotNull String name) throws IncorrectOperationException {
        throw new IncorrectOperationException();
    }
}
*/