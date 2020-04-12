// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishUseCommand extends PsiElement {

  @NotNull
  List<ElvishNamespacePath> getNamespacePathList();

  @NotNull
  ElvishVariableName getVariableName();

  @NotNull
  PsiElement getInlineWhitespace();

}
