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

public class ElvishHeadImpl extends ASTWrapperElvishPsiElement implements ElvishHead {

  public ElvishHeadImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitHead(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElvishCommandExpression getCommandExpression() {
    return findChildByClass(ElvishCommandExpression.class);
  }

  @Override
  @Nullable
  public ElvishDoubleQuoted getDoubleQuoted() {
    return findChildByClass(ElvishDoubleQuoted.class);
  }

  @Override
  @Nullable
  public ElvishExceptionCapture getExceptionCapture() {
    return findChildByClass(ElvishExceptionCapture.class);
  }

  @Override
  @Nullable
  public ElvishNamespaceCommandExpression getNamespaceCommandExpression() {
    return findChildByClass(ElvishNamespaceCommandExpression.class);
  }

  @Override
  @Nullable
  public ElvishNamespaceVariableRef getNamespaceVariableRef() {
    return findChildByClass(ElvishNamespaceVariableRef.class);
  }

  @Override
  @Nullable
  public ElvishOutputCapture getOutputCapture() {
    return findChildByClass(ElvishOutputCapture.class);
  }

  @Override
  @Nullable
  public ElvishSingleQuoted getSingleQuoted() {
    return findChildByClass(ElvishSingleQuoted.class);
  }

  @Override
  @Nullable
  public ElvishVariableRef getVariableRef() {
    return findChildByClass(ElvishVariableRef.class);
  }

}
