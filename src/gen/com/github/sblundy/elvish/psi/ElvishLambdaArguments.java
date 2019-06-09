// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishLambdaArguments extends PsiElement {

  @NotNull
  List<ElvishMapPair> getMapPairList();

  @NotNull
  List<ElvishParameter> getParameterList();

}
