// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishAssignment extends ElvishPsiElement {

  @NotNull
  List<ElvishIndexRange> getIndexRangeList();

  @NotNull
  List<ElvishIndexSingle> getIndexSingleList();

  @NotNull
  List<ElvishLocalScopeVariableAssignment> getLocalScopeVariableAssignmentList();

  @NotNull
  List<ElvishNamespaceVariableAssignment> getNamespaceVariableAssignmentList();

  @NotNull
  List<ElvishUpScopeVariableAssignment> getUpScopeVariableAssignmentList();

  @NotNull
  List<ElvishVariable> getVariableList();

  @Nullable
  ElvishCompound getValue();

}
