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

public class ElvishExceptionCaptureImpl extends ASTWrapperElvishPsiElement implements ElvishExceptionCapture {

  public ElvishExceptionCaptureImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitExceptionCapture(this);
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
  public PsiElement getCloseParan() {
    return findNotNullChildByType(CLOSE_PARAN);
  }

  @Override
  @NotNull
  public PsiElement getOpenParan() {
    return findNotNullChildByType(OPEN_PARAN);
  }

  @Override
  @NotNull
  public PsiElement getQuestion() {
    return findNotNullChildByType(QUESTION);
  }

}
