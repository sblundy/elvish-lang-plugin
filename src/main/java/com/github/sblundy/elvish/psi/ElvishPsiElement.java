package com.github.sblundy.elvish.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.Nullable;

public interface ElvishPsiElement extends PsiElement {
    @Nullable ElvishLexicalScope getScope();
}
