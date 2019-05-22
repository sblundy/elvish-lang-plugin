// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishLine extends PsiElement {

  @Nullable
  ElvishCommand getCommand();

  @Nullable
  ElvishLineTerminator getLineTerminator();

  @Nullable
  PsiElement getComment();

}
