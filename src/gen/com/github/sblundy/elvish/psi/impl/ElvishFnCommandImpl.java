// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.github.sblundy.elvish.psi.FnCommandBase;
import com.github.sblundy.elvish.psi.*;

public class ElvishFnCommandImpl extends FnCommandBase implements ElvishFnCommand {

  public ElvishFnCommandImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitFnCommand(this);
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
  @Nullable
  public ElvishLambdaArguments getLambdaArguments() {
    return findChildByClass(ElvishLambdaArguments.class);
  }

  @Override
  @NotNull
  public ElvishVariableName getCommandName() {
    return findNotNullChildByClass(ElvishVariableName.class);
  }

}
