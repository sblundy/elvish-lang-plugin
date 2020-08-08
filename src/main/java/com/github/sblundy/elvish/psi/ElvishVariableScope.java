package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public interface ElvishVariableScope {
    @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull String name, @NotNull List<String> ns);
}
