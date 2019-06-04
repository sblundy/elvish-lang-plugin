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

public class ElvishVariableIndexImpl extends ASTWrapperPsiElement implements ElvishVariableIndex {

  public ElvishVariableIndexImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitVariableIndex(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ElvishVariableRef getVariableRef() {
    return findChildByClass(ElvishVariableRef.class);
  }

  @Override
  @Nullable
  public PsiElement getBareword() {
    return findChildByType(BAREWORD);
  }

  @Override
  @Nullable
  public PsiElement getVariable() {
    return findChildByType(VARIABLE);
  }

}
