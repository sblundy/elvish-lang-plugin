package com.github.sblundy.elvish.psi;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class ElvishChunkBase extends ASTWrapperPsiElement implements ElvishChunk, ElvishVariableScope {
    public ElvishChunkBase(@NotNull ASTNode node) {
        super(node);
    }

    @NotNull
    @Override
    public Collection<ElvishVariableDeclaration> findVariables(@NotNull String name, @NotNull List<String> ns) {
        List<ElvishVariableDeclaration> found = new ArrayList<>();
        for (ElvishAssignment assignment : getAssignmentList()) {
            for (ElvishVariableDeclaration declaration : assignment.getVariableList()) {
                if (declaration.nameMatches(name, ns)) {
                    found.add(declaration);
                }
            }
        }

        found.addAll(ElvishPsiUtils.INSTANCE.findVariableInParentScope(name, ns, this));
        return found;
    }
}
