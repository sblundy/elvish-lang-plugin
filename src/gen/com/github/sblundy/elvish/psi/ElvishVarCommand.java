// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishVarCommand extends ElvishSpecialCommand {

  @NotNull
  List<ElvishCompound> getCompoundList();

  @NotNull
  PsiElement getKeyword();

  @NotNull
  List<ElvishVarLValue> getLValues();

}
