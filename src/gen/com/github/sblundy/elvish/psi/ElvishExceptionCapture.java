// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishExceptionCapture extends PsiElement {

  @NotNull
  ElvishChunk getChunk();

  @NotNull
  PsiElement getCloseParan();

  @NotNull
  PsiElement getOpenParan();

  @NotNull
  PsiElement getQuestion();

}
