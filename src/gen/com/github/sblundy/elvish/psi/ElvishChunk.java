// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishChunk extends ElvishPsiElement {

  @NotNull
  List<ElvishAssignment> getAssignmentList();

  @NotNull
  List<ElvishChunkBlock> getChunkBlockList();

  @NotNull
  List<ElvishDeleteCommand> getDeleteCommandList();

  @NotNull
  List<ElvishFnCommand> getFnCommandList();

  @NotNull
  List<ElvishLogicCommand> getLogicCommandList();

  @NotNull
  List<ElvishPipelineSep> getPipelineSepList();

  @NotNull
  List<ElvishUseCommand> getUseCommandList();

  @NotNull
  List<ElvishArgument> getArgumentList();

  @NotNull
  List<ElvishHead> getHeadList();

}
