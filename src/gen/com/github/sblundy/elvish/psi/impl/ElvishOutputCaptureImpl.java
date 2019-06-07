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

public class ElvishOutputCaptureImpl extends ASTWrapperPsiElement implements ElvishOutputCapture {

  public ElvishOutputCaptureImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitOutputCapture(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElvishDeleteStatement getDeleteStatement() {
    return findChildByClass(ElvishDeleteStatement.class);
  }

  @Override
  @Nullable
  public ElvishFnStatement getFnStatement() {
    return findChildByClass(ElvishFnStatement.class);
  }

  @Override
  @Nullable
  public ElvishForStatement getForStatement() {
    return findChildByClass(ElvishForStatement.class);
  }

  @Override
  @Nullable
  public ElvishIfStatement getIfStatement() {
    return findChildByClass(ElvishIfStatement.class);
  }

  @Override
  @Nullable
  public ElvishOrdinaryCommand getOrdinaryCommand() {
    return findChildByClass(ElvishOrdinaryCommand.class);
  }

  @Override
  @Nullable
  public ElvishPipeline getPipeline() {
    return findChildByClass(ElvishPipeline.class);
  }

  @Override
  @Nullable
  public ElvishTryStatement getTryStatement() {
    return findChildByClass(ElvishTryStatement.class);
  }

  @Override
  @Nullable
  public ElvishWhileStatement getWhileStatement() {
    return findChildByClass(ElvishWhileStatement.class);
  }

}
