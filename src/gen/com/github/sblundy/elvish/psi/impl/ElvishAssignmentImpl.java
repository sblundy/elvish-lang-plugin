// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.github.sblundy.elvish.psi.ASTWrapperElvishPsiElement;
import com.github.sblundy.elvish.psi.*;

public class ElvishAssignmentImpl extends ASTWrapperElvishPsiElement implements ElvishAssignment {

  public ElvishAssignmentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitAssignment(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishVarIndex> getVarIndexList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVarIndex.class);
  }

  @Override
  @NotNull
  public List<ElvishVariableAssignment> getVariableAssignmentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVariableAssignment.class);
  }

  @Override
  @Nullable
  public ElvishCompound getValue() {
    return findChildByClass(ElvishCompound.class);
  }

}
