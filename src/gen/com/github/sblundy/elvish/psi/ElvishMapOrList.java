// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishMapOrList extends PsiElement {

  @NotNull
  List<ElvishCompoundExpression> getCompoundExpressionList();

  @NotNull
  List<ElvishDoubleQuotedString> getDoubleQuotedStringList();

  @NotNull
  List<ElvishLambda> getLambdaList();

  @NotNull
  List<ElvishMapEntry> getMapEntryList();

  @NotNull
  List<ElvishMapOrList> getMapOrListList();

  @NotNull
  List<ElvishOutputCapture> getOutputCaptureList();

  @NotNull
  List<ElvishSingleQuotedString> getSingleQuotedStringList();

  @NotNull
  List<ElvishVariableRef> getVariableRefList();

}