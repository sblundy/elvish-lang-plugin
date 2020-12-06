// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishIndexRange extends ElvishPsiElement {

  @NotNull
  List<ElvishDoubleQuoted> getDoubleQuotedList();

  @NotNull
  List<ElvishExceptionCapture> getExceptionCaptureList();

  @NotNull
  List<ElvishNamespaceVariableRef> getNamespaceVariableRefList();

  @NotNull
  List<ElvishOutputCapture> getOutputCaptureList();

  @NotNull
  List<ElvishSingleQuoted> getSingleQuotedList();

  @NotNull
  List<ElvishSpecialScopeVariableRef> getSpecialScopeVariableRefList();

  @NotNull
  List<ElvishVariableName> getVariableNameList();

  @NotNull
  List<ElvishVariableRef> getVariableRefList();

}
