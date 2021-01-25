// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishForCommand extends ElvishChunkBlock, ElvishSpecialCommand, ElvishVariableDeclaration {

  @Nullable
  ElvishChunk getChunk();

  @NotNull
  ElvishCollection getCollection();

  @Nullable
  ElvishElseBlock getElseBlock();

  @NotNull
  ElvishVariableName getVariableName();

  @Nullable
  PsiElement getEol();

  @NotNull
  PsiElement getKeyword();

}
