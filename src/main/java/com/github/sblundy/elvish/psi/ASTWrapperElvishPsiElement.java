package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class ASTWrapperElvishPsiElement extends ASTWrapperPsiElement implements ElvishPsiElement {
    public ASTWrapperElvishPsiElement(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @Nullable ElvishBlock getBlock() {
        PsiElement parent = getParent();
        while (parent != null) {
            if (parent instanceof ElvishBlock) {
                return (ElvishBlock) parent;
            }

            parent = parent.getParent();
        }
        return null;
    }

    @Override
    public @Nullable ElvishLexicalScope getScope() {
        PsiElement parent = getParent();
        while (parent != null) {
            if (parent instanceof ElvishLexicalScope) {
                return (ElvishLexicalScope) parent;
            }

            parent = parent.getParent();
        }
        return null;
    }
}
