// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishVariableRef extends ReferenceWithNamespacePsiElement {

  @NotNull
  List<ElvishBareword> getBarewordList();

  @NotNull
  List<ElvishBraced> getBracedList();

  @NotNull
  List<ElvishDoubleQuoted> getDoubleQuotedList();

  @NotNull
  List<ElvishExceptionCapture> getExceptionCaptureList();

  @NotNull
  List<ElvishLambda> getLambdaList();

  @NotNull
  List<ElvishList> getListList();

  @NotNull
  List<ElvishMap> getMapList();

  @Nullable
  ElvishNamespaceName getNamespaceName();

  @NotNull
  List<ElvishOutputCapture> getOutputCaptureList();

  @NotNull
  List<ElvishSingleQuoted> getSingleQuotedList();

  @NotNull
  ElvishVariableName getVariableName();

  @NotNull
  List<ElvishVariableRef> getVariableRefList();

}
