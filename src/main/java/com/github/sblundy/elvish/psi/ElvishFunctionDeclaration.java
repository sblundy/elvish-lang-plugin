package com.github.sblundy.elvish.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Marker interface for function declarations
 */
public interface ElvishFunctionDeclaration extends PsiElement {
    boolean nameMatches(@NotNull String name, @NotNull List<String> ns);
}
