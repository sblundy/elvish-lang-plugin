// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishLValue extends ElvishLValueVariable {

  @Nullable
  ElvishNamespaceIdentifier getNamespaceIdentifier();

  @NotNull
  List<ElvishVarIndex> getVarIndexList();

}
