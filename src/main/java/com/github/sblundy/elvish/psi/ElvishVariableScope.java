package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ElvishVariableScope {
    @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull ReferenceWithNamespacePsiElement ref);
    void processVariables(@NotNull ElvishVariableScope.VariableProcessor processor);

    interface VariableProcessor {
        boolean process(ElvishVariableDeclaration d);
    }
}
