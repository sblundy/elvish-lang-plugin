// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;

public interface ElvishNamespaceVariableRef extends ElvishExternalVariableReference {

  @NotNull
  List<ElvishVarIndex> getVarIndexList();

  @NotNull
  PsiElement getDollarSign();

  @NotNull
  PsiReference getReference();

}
