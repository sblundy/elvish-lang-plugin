package com.github.sblundy.elvish.psi;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;

public interface ElvishVariableDeclaringBlock extends ElvishVariableScope, PsiElement {
    ElvishVariable getVariable();

    @Override
    default @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull ReferenceWithNamespacePsiElement ref) {
        ElvishVariable v = getVariable();
        if (v != null && v.nameMatches(ref)) {
            return Collections.singletonList(v);
        }

        return ElvishPsiUtils.INSTANCE.findVariableInParentScope(ref, this);
    }

    default void processVariables(@NotNull ElvishVariableScope.VariableProcessor processor) {
        ElvishVariable v = getVariable();
        if (v != null) {
            if (!processor.process(v)) {
                return;
            }
        }

        ElvishPsiUtils.INSTANCE.processVariablesInParentScope(processor, this);
    }
}
