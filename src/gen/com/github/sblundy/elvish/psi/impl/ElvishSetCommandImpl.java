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

public class ElvishSetCommandImpl extends ASTWrapperElvishPsiElement implements ElvishSetCommand {

  public ElvishSetCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitSetCommand(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishCompound> getCompoundList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishCompound.class);
  }

  @Override
  @NotNull
  public PsiElement getKeyword() {
    return findNotNullChildByType(KEYWORD_SET);
  }

  @Override
  @NotNull
  public List<ElvishLValueVariable> getLValues() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishLValueVariable.class);
  }

}
