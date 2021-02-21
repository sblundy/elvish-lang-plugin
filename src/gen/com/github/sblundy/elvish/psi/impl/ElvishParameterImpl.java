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
import com.intellij.navigation.ItemPresentation;
import com.intellij.util.IncorrectOperationException;
import javax.swing.Icon;

public class ElvishParameterImpl extends ElvishVariableDeclarationImpl implements ElvishParameter {

  public ElvishParameterImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitParameter(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public PsiElement getAtSymbol() {
    return findChildByType(AT_SYMBOL);
  }

  @Override
  @NotNull
  public PsiElement getNameIdentifier() {
    return ElvishPsiImplUtil.getNameIdentifier(this);
  }

  @Override
  @NotNull
  public String getName() {
    return ElvishPsiImplUtil.getName(this);
  }

  @Override
  @NotNull
  public PsiElement setName(@NotNull String p1) throws IncorrectOperationException {
    return ElvishPsiImplUtil.setName(this, p1);
  }

  @Override
  @NotNull
  public Icon getIcon(int p1) {
    return ElvishPsiImplUtil.getIcon(this, p1);
  }

  @Override
  @NotNull
  public ItemPresentation getPresentation() {
    return ElvishPsiImplUtil.getPresentation(this);
  }

}
