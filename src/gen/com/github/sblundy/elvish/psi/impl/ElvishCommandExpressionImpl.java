// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.github.sblundy.elvish.psi.ElvishCommandExpressionBase;
import com.github.sblundy.elvish.psi.*;

public class ElvishCommandExpressionImpl extends ElvishCommandExpressionBase implements ElvishCommandExpression {

  public ElvishCommandExpressionImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitCommandExpression(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ElvishCommandBareword getCommandBareword() {
    return findNotNullChildByClass(ElvishCommandBareword.class);
  }

  @Override
  @NotNull
  public List<ElvishNamespaceName> getNamespaceNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishNamespaceName.class);
  }

}
