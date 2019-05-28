// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishFnStatement extends PsiElement {

  @NotNull
  List<ElvishAssignment> getAssignmentList();

  @NotNull
  List<ElvishDeleteStatement> getDeleteStatementList();

  @NotNull
  List<ElvishFnStatement> getFnStatementList();

  @NotNull
  List<ElvishForStatement> getForStatementList();

  @NotNull
  List<ElvishIfStatement> getIfStatementList();

  @NotNull
  List<ElvishLineTerminator> getLineTerminatorList();

  @NotNull
  List<ElvishOrdinaryCommand> getOrdinaryCommandList();

  @NotNull
  List<ElvishParameter> getParameterList();

  @NotNull
  List<ElvishPipeline> getPipelineList();

  @NotNull
  List<ElvishTryStatement> getTryStatementList();

  @NotNull
  List<ElvishWhileStatement> getWhileStatementList();

  @NotNull
  PsiElement getVariable();

}
