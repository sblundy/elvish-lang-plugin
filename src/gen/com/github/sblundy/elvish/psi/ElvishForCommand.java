// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishForCommand extends ElvishChunkBlock, ElvishSpecialCommand {

  @Nullable
  ElvishChunk getChunk();

  @NotNull
  ElvishCollection getCollection();

  @Nullable
  ElvishElseBlock getElseBlock();

  @NotNull
  ElvishVariable getVariable();

  @Nullable
  PsiElement getEol();

  @NotNull
  PsiElement getKeyword();

}
