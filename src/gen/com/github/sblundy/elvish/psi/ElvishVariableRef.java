// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ElvishVariableRef extends ElvishVariableReference {

  @NotNull
  List<ElvishVarIndex> getVarIndexList();

  @Nullable
  PsiElement getAtSymbol();

  @NotNull
  PsiElement getDollarSign();

  @NotNull
  PsiReference getReference();

}
