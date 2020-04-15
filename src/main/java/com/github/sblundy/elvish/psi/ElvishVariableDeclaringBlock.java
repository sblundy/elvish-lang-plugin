package com.github.sblundy.elvish.psi;

import com.intellij.psi.PsiElement;

import java.util.Collection;
import java.util.Collections;

public interface ElvishVariableDeclaringBlock extends ElvishVariableScope, PsiElement {
    ElvishVariable getVariable();

    @Override
    default Collection<ElvishVariableDeclaration> findVariables(String name) {
        ElvishVariable v = getVariable();
        if (v != null && v.textMatches(name)) {
            return Collections.singletonList(v);
        }

        return ElvishPsiUtils.INSTANCE.findVariableInParentScope(name, this);
    }
}
