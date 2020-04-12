// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishHead extends PsiElement {

  @Nullable
  ElvishCommandBareword getCommandBareword();

  @Nullable
  ElvishDoubleQuoted getDoubleQuoted();

  @Nullable
  ElvishExceptionCapture getExceptionCapture();

  @NotNull
  List<ElvishNamespaceName> getNamespaceNameList();

  @Nullable
  ElvishOutputCapture getOutputCapture();

  @Nullable
  ElvishSingleQuoted getSingleQuoted();

  @Nullable
  ElvishVariableRef getVariableRef();

}
