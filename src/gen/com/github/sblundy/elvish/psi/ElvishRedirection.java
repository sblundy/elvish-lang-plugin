// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ElvishRedirection extends PsiElement {

  @Nullable
  ElvishCompoundExpression getCompoundExpression();

  @Nullable
  ElvishDoubleQuotedString getDoubleQuotedString();

  @Nullable
  ElvishSingleQuotedString getSingleQuotedString();

  @Nullable
  ElvishVariableRef getVariableRef();

  @Nullable
  PsiElement getBareword();

  @Nullable
  PsiElement getCommandBareword();

  @Nullable
  PsiElement getVariable();

}