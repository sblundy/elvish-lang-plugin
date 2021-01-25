// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.github.sblundy.elvish.psi.*;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import javax.swing.Icon;

public class ElvishNamespaceVariableAssignmentImpl extends ElvishVariableAssignmentImpl implements ElvishNamespaceVariableAssignment {

  public ElvishNamespaceVariableAssignmentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitNamespaceVariableAssignment(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ElvishNamespaceIdentifier getNamespaceIdentifier() {
    return findNotNullChildByClass(ElvishNamespaceIdentifier.class);
  }

  @Override
  @NotNull
  public List<ElvishVarIndex> getVarIndexList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVarIndex.class);
  }

  @Override
  @NotNull
  public String getName() {
    return ElvishPsiImplUtil.getName(this);
  }

  @Override
  public boolean isWritable() {
    return ElvishPsiImplUtil.isWritable(this);
  }

  @Override
  public int getTextOffset() {
    return ElvishPsiImplUtil.getTextOffset(this);
  }

  @Override
  @NotNull
  public Icon getIcon(int p1) {
    return ElvishPsiImplUtil.getIcon(this, p1);
  }

  @Override
  @NotNull
  public ItemPresentation getPresentation() {
    return ElvishPsiImplUtil.getPresentation(this);
  }

  @Override
  @NotNull
  public PsiReference getReference() {
    return ElvishPsiImplUtil.getReference(this);
  }

}
