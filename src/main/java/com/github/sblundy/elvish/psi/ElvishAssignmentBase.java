package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ElvishAssignmentBase extends ASTWrapperPsiElement implements ElvishAssignment, ElvishVariableScope {
    public ElvishAssignmentBase(@NotNull ASTNode node) {
        super(node);
    }

    @Override
    public @NotNull Collection<ElvishVariableDeclaration> findVariables(@NotNull String name, @NotNull List<String> ns) {
        List<ElvishVariableDeclaration> found = new ArrayList<>();
        for (ElvishVariableDeclaration declaration : getVariableList()) {
            if (declaration.nameMatches(name, ns)) {
                found.add(declaration);
            }
        }
        return found;
    }
}
