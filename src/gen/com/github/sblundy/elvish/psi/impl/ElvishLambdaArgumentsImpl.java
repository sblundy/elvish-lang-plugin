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

public class ElvishLambdaArgumentsImpl extends ASTWrapperElvishPsiElement implements ElvishLambdaArguments {

  public ElvishLambdaArgumentsImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitLambdaArguments(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishMapPair> getMapPairList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishMapPair.class);
  }

  @Override
  @NotNull
  public List<ElvishParameter> getParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishParameter.class);
  }

}
