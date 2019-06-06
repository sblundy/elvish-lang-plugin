// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishAssignment extends PsiElement {

  @Nullable
  ElvishCompoundExpression getCompoundExpression();

  @Nullable
  ElvishDoubleQuotedString getDoubleQuotedString();

  @Nullable
  ElvishEmptyMap getEmptyMap();

  @Nullable
  ElvishLambda getLambda();

  @Nullable
  ElvishMapOrList getMapOrList();

  @Nullable
  ElvishOutputCapture getOutputCapture();

  @Nullable
  ElvishSingleQuotedString getSingleQuotedString();

  @NotNull
  List<ElvishVariableIndex> getVariableIndexList();

  @Nullable
  ElvishVariableRef getVariableRef();

  @Nullable
  PsiElement getBareword();

}
