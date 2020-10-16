package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface ElvishVariableScope {
    @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull String name, @NotNull List<String> ns);
    void processVariables(@NotNull ElvishVariableScope.VariableProcessor processor);

    interface VariableProcessor {
        boolean process(ElvishVariableDeclaration d);
    }
}
