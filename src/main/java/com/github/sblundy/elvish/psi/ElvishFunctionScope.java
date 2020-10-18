package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface ElvishFunctionScope {
    @NotNull Collection<ElvishFunctionDeclaration> findFnCommands(@NotNull ReferenceWithNamespacePsiElement ref);
    void processFnCommands(@NotNull FnCommandProcessor processor);

    interface FnCommandProcessor {
        boolean process(ElvishFunctionDeclaration d);
    }
}
