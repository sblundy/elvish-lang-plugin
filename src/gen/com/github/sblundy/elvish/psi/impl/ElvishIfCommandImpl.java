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

public class ElvishIfCommandImpl extends ElvishChunkBlockImpl implements ElvishIfCommand {

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
  @Nullable
  public ElvishChunk getChunk() {
    return findChildByClass(ElvishChunk.class);
  }

  @Override
  @NotNull
  public List<ElvishChunkBlock> getChunkBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishChunkBlock.class);
  }

  @Override
  @Nullable
  public ElvishCondition getCondition() {
    return findChildByClass(ElvishCondition.class);
  }

  @Override
  @Nullable
  public PsiElement getEol() {
    return findChildByType(EOL);
  }

}
