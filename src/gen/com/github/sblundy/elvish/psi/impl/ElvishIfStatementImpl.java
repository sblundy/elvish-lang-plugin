// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.sblundy.elvish.psi.*;

public class ElvishIfStatementImpl extends ASTWrapperPsiElement implements ElvishIfStatement {

  public ElvishIfStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitIfStatement(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ElvishBlock getBlock() {
    return findNotNullChildByClass(ElvishBlock.class);
  }

  @Override
  @NotNull
  public ElvishCondition getCondition() {
    return findNotNullChildByClass(ElvishCondition.class);
  }

  @Override
  @NotNull
  public List<ElvishElifStatement> getElifStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishElifStatement.class);
  }

  @Override
  @Nullable
  public ElvishElseStatement getElseStatement() {
    return findChildByClass(ElvishElseStatement.class);
  }

}