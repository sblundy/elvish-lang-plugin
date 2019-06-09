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

public class ElvishIfCommandImpl extends ASTWrapperPsiElement implements ElvishIfCommand {

  public ElvishIfCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitIfCommand(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ElvishChunk getChunk() {
    return findNotNullChildByClass(ElvishChunk.class);
  }

  @Override
  @NotNull
  public ElvishCondition getCondition() {
    return findNotNullChildByClass(ElvishCondition.class);
  }

  @Override
  @NotNull
  public List<ElvishElIfBlock> getElIfBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishElIfBlock.class);
  }

  @Override
  @Nullable
  public ElvishElseBlock getElseBlock() {
    return findChildByClass(ElvishElseBlock.class);
  }

}