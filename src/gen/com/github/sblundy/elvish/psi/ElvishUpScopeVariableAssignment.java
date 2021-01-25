// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import javax.swing.Icon;

public interface ElvishUpScopeVariableAssignment extends ElvishVariableAssignment, ElvishLValue {

  @NotNull
  List<ElvishVarIndex> getVarIndexList();

  @NotNull
  ElvishUpNamespace getNamespaceIdentifier();

  @NotNull
  String getName();

  int getTextOffset();

  @NotNull
  Icon getIcon(int p1);

  @NotNull
  ItemPresentation getPresentation();

  @Nullable
  PsiReference getReference();

}
