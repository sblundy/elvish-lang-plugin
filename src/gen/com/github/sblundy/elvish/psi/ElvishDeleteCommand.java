// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishDeleteCommand extends ElvishSpecialCommand {

  @Nullable
  ElvishNamespaceIdentifier getNamespaceIdentifier();

  @NotNull
  List<ElvishVarIndex> getVarIndexList();

  @NotNull
  ElvishVariableName getVariableName();

  @NotNull
  PsiElement getKeyword();

}
