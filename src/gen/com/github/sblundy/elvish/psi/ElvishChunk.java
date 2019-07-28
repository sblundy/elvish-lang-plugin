// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishChunk extends PsiElement {

  @NotNull
  List<ElvishAssignment> getAssignmentList();

  @NotNull
  List<ElvishDeleteCommand> getDeleteCommandList();

  @NotNull
  List<ElvishFnCommand> getFnCommandList();

  @NotNull
  List<ElvishForCommand> getForCommandList();

  @NotNull
  List<ElvishIfCommand> getIfCommandList();

  @NotNull
  List<ElvishLogicCommand> getLogicCommandList();

  @NotNull
  List<ElvishPipelineSep> getPipelineSepList();

  @NotNull
  List<ElvishTryCommand> getTryCommandList();

  @NotNull
  List<ElvishUseCommand> getUseCommandList();

  @NotNull
  List<ElvishWhileCommand> getWhileCommandList();

  @NotNull
  List<ElvishArgument> getArgumentList();

  @NotNull
  List<ElvishHead> getHeadList();

}
