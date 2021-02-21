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

public class ElvishForCommandImpl extends ElvishChunkBlockImpl implements ElvishForCommand {

  public ElvishForCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitForCommand(this);
  }

  @Override
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
  public ElvishCollection getCollection() {
    return findNotNullChildByClass(ElvishCollection.class);
  }

  @Override
  @Nullable
  public ElvishElseBlock getElseBlock() {
    return findChildByClass(ElvishElseBlock.class);
  }

  @Override
  @NotNull
  public ElvishVariableName getVariableName() {
    return findNotNullChildByClass(ElvishVariableName.class);
  }

  @Override
  @Nullable
  public PsiElement getEol() {
    return findChildByType(EOL);
  }

  @Override
  @NotNull
  public PsiElement getKeyword() {
    return findNotNullChildByType(KEYWORD_FOR);
  }

}
