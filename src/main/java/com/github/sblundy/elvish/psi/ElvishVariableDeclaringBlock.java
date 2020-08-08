package com.github.sblundy.elvish.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public interface ElvishVariableDeclaringBlock extends ElvishVariableScope, PsiElement {
    ElvishVariable getVariable();

    @Override
    default @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull String name, @NotNull List<String> ns) {
        ElvishVariable v = getVariable();
        if (v != null && v.nameMatches(name, ns)) {
            return Collections.singletonList(v);
        }

        return ElvishPsiUtils.INSTANCE.findVariableInParentScope(name, ns, this);
    }
}
