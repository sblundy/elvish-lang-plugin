package com.github.sblundy.elvish.psi;

import java.util.Collection;

public interface ElvishVariableScope {
    Collection<ElvishVariableDeclaration> findVariables(String name);
}
