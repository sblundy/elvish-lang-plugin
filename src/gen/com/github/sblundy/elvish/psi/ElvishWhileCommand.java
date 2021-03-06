// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishWhileCommand extends ElvishChunkBlock, ElvishSpecialCommand {

  @Nullable
  ElvishChunk getChunk();

  @NotNull
  ElvishCondition getCondition();

  @Nullable
  ElvishElseBlock getElseBlock();

  @Nullable
  PsiElement getEol();

  @NotNull
  PsiElement getKeyword();

}
