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

public class ElvishFnCommandImpl extends ElvishFunctionDeclarationImpl implements ElvishFnCommand {

  public ElvishFnCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitFnCommand(this);
  }

  @Override
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
  @Nullable
  public ElvishLambdaArguments getLambdaArguments() {
    return findChildByClass(ElvishLambdaArguments.class);
  }

  @Override
  @NotNull
  public PsiElement getKeyword() {
    return findNotNullChildByType(KEYWORD_FN);
  }

  @Override
  @NotNull
  public ElvishUnquotedVariableName getCommandName() {
    return findNotNullChildByClass(ElvishUnquotedVariableName.class);
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
  public int getTextOffset() {
    return ElvishPsiImplUtil.getTextOffset(this);
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
