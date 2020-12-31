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
import com.intellij.psi.PsiReference;

public class ElvishVariableRefImpl extends ElvishVariableReferenceImpl implements ElvishVariableRef {

  public ElvishVariableRefImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitVariableRef(this);
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
  @Nullable
  public PsiElement getAtSymbol() {
    return findChildByType(AT_SYMBOL);
  }

  @Override
  @NotNull
  public PsiElement getDollarSign() {
    return findNotNullChildByType(DOLLAR_SIGN);
  }

  @Override
  @NotNull
  public PsiReference getReference() {
    return ElvishPsiImplUtil.getReference(this);
  }

}
