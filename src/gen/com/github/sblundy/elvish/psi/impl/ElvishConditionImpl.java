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

public class ElvishConditionImpl extends ASTWrapperElvishPsiElement implements ElvishCondition {

  public ElvishConditionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitCondition(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElvishExceptionCapture getExceptionCapture() {
    return findChildByClass(ElvishExceptionCapture.class);
  }

  @Override
  @Nullable
  public ElvishOutputCapture getOutputCapture() {
    return findChildByClass(ElvishOutputCapture.class);
  }

  @Override
  @Nullable
  public ElvishVariableReference getVariableReference() {
    return findChildByClass(ElvishVariableReference.class);
  }

}
