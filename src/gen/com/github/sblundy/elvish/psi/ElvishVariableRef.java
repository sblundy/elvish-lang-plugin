// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishVariableRef extends ReferenceWithNamespacePsiElement {

  @NotNull
  List<ElvishIndexRange> getIndexRangeList();

  @NotNull
  List<ElvishIndexSingle> getIndexSingleList();

  @Nullable
  ElvishNamespaceName getNamespaceName();

  @NotNull
  ElvishVariableName getVariableName();

}
