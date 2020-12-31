package com.github.sblundy.elvish.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface ElvishSpecialCommand extends ElvishPsiElement {
    @NotNull
    PsiElement getKeyword();
}
