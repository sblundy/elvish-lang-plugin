// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiReference;
import javax.swing.Icon;

public interface ElvishNamespaceVariableAssignment extends ElvishVariableAssignment, ElvishExternalVariableReference {

  @NotNull
  ElvishNamespaceIdentifier getNamespaceIdentifier();

  @NotNull
  String getName();

  boolean isWritable();

  int getTextOffset();

  @NotNull
  Icon getIcon(int p1);

  @NotNull
  ItemPresentation getPresentation();

  @NotNull
  PsiReference getReference();

}
