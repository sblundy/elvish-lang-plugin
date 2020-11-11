// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishCompound extends PsiElement {

  @NotNull
  List<ElvishBareword> getBarewordList();

  @NotNull
  List<ElvishBraced> getBracedList();

  @NotNull
  List<ElvishDoubleQuoted> getDoubleQuotedList();

  @NotNull
  List<ElvishExceptionCapture> getExceptionCaptureList();

  @NotNull
  List<ElvishIndexRange> getIndexRangeList();

  @NotNull
  List<ElvishIndexSingle> getIndexSingleList();

  @NotNull
  List<ElvishLambda> getLambdaList();

  @NotNull
  List<ElvishList> getListList();

  @NotNull
  List<ElvishMap> getMapList();

  @NotNull
  List<ElvishOutputCapture> getOutputCaptureList();

  @NotNull
  List<ElvishSingleQuoted> getSingleQuotedList();

  @NotNull
  List<ElvishVariableRef> getVariableRefList();

}
