// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface ElvishVarLValue extends ElvishVariableAssignment, ElvishLValue, ElvishVariableDeclaration, PsiNameIdentifierOwner {

  @NotNull
  List<ElvishVarIndex> getVarIndexList();

}
