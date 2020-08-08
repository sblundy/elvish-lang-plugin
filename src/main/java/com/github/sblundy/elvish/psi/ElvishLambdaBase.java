package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ElvishLambdaBase extends ASTWrapperPsiElement implements ElvishVariableScope {
    public ElvishLambdaBase(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull String name, @NotNull List<String> ns) {
        List<ElvishVariableDeclaration> found = new ArrayList<>();
        if (getLambdaArguments() != null && ns.isEmpty()) {
            for (ElvishVariableDeclaration declaration : getLambdaArguments().getParameterList()) {
                if (declaration.textMatches(name)) {
                    found.add(declaration);
                }
            }
        }

        if (!found.isEmpty()) {
            return found;
        }

        return ElvishPsiUtils.INSTANCE.findVariableInParentScope(name, ns, this);
    }

    @Nullable
    abstract public ElvishLambdaArguments getLambdaArguments();
}
