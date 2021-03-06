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

public class ElvishVarIndexImpl extends ASTWrapperElvishPsiElement implements ElvishVarIndex {

  public ElvishVarIndexImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitVarIndex(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishIndex> getIndexList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishIndex.class);
  }

  @Override
  @Nullable
  public PsiElement getCloseBracket() {
    return findChildByType(CLOSE_BRACKET);
  }

  @Override
  @NotNull
  public PsiElement getOpenBracket() {
    return findNotNullChildByType(OPEN_BRACKET);
  }

}
