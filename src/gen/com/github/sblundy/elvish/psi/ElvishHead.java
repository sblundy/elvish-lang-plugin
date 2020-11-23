// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishHead extends ElvishPsiElement {

  @Nullable
  ElvishCommandExpression getCommandExpression();

  @Nullable
  ElvishDoubleQuoted getDoubleQuoted();

  @Nullable
  ElvishExceptionCapture getExceptionCapture();

  @Nullable
  ElvishNamespaceCommandExpression getNamespaceCommandExpression();

  @Nullable
  ElvishNamespaceVariableRef getNamespaceVariableRef();

  @Nullable
  ElvishOutputCapture getOutputCapture();

  @Nullable
  ElvishSingleQuoted getSingleQuoted();

  @Nullable
  ElvishVariableRef getVariableRef();

}
