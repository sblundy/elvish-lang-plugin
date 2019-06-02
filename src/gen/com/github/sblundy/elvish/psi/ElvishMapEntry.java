// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishMapEntry extends PsiElement {

  @Nullable
  ElvishCompoundExpression getCompoundExpression();

  @NotNull
  List<ElvishDoubleQuotedString> getDoubleQuotedStringList();

  @Nullable
  ElvishEmptyMap getEmptyMap();

  @Nullable
  ElvishLambda getLambda();

  @Nullable
  ElvishMapOrList getMapOrList();

  @Nullable
  ElvishOutputCapture getOutputCapture();

  @NotNull
  List<ElvishSingleQuotedString> getSingleQuotedStringList();

  @Nullable
  ElvishVariableRef getVariableRef();

}
