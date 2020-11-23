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

public class ElvishArgumentImpl extends ASTWrapperElvishPsiElement implements ElvishArgument {

  public ElvishArgumentImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitArgument(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElvishCompound getCompound() {
    return findChildByClass(ElvishCompound.class);
  }

  @Override
  @Nullable
  public ElvishExitusRedir getExitusRedir() {
    return findChildByClass(ElvishExitusRedir.class);
  }

  @Override
  @Nullable
  public ElvishMapPair getMapPair() {
    return findChildByClass(ElvishMapPair.class);
  }

  @Override
  @Nullable
  public ElvishRedir getRedir() {
    return findChildByClass(ElvishRedir.class);
  }

}
