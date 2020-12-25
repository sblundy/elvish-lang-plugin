// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishIfCommand extends ElvishPsiElement {

  @Nullable
  ElvishCondition getCondition();

  @NotNull
  List<ElvishElIfBlock> getElIfBlockList();

  @Nullable
  ElvishElseBlock getElseBlock();

  @Nullable
  ElvishLambdaBlock getLambdaBlock();

  @Nullable
  PsiElement getEol();

}
