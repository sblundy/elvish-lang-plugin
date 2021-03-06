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

public class ElvishTryCommandImpl extends ElvishChunkBlockImpl implements ElvishTryCommand {

  public ElvishTryCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitTryCommand(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishChunkBlock> getChunkBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishChunkBlock.class);
  }

  @Override
  @NotNull
  public PsiElement getKeyword() {
    return findNotNullChildByType(KEYWORD_TRY);
  }

}
