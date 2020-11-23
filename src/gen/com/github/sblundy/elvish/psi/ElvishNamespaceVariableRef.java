// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishNamespaceVariableRef extends ElvishPsiElement {

  @NotNull
  List<ElvishIndexRange> getIndexRangeList();

  @NotNull
  List<ElvishIndexSingle> getIndexSingleList();

  @NotNull
  ElvishNamespaceIdentifier getNamespaceIdentifier();

  @NotNull
  ElvishVariableName getVariableName();

}
