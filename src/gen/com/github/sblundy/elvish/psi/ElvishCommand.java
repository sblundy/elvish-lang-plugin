// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishCommand extends PsiElement {

  @Nullable
  ElvishAssignment getAssignment();

  @Nullable
  ElvishIfStatement getIfStatement();

  @Nullable
  ElvishOrdinaryCommand getOrdinaryCommand();

  @Nullable
  ElvishPipeline getPipeline();

  @Nullable
  ElvishWhileStatement getWhileStatement();

}
