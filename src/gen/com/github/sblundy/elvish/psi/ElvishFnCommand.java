// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;
import com.intellij.navigation.ItemPresentation;
import com.intellij.util.IncorrectOperationException;
import javax.swing.Icon;

public interface ElvishFnCommand extends ElvishFunctionDeclaration, ElvishLambdaScope, PsiNameIdentifierOwner, ElvishSpecialCommand {

  @NotNull
  ElvishChunk getChunk();

  @Nullable
  ElvishLambdaArguments getLambdaArguments();

  @NotNull
  PsiElement getKeyword();

  @NotNull
  ElvishUnquotedVariableName getCommandName();

  @NotNull
  PsiElement getNameIdentifier();

  @NotNull
  String getName();

  @NotNull
  PsiElement setName(@NotNull String p1) throws IncorrectOperationException;

  int getTextOffset();

  @NotNull
  Icon getIcon(int p1);

  @NotNull
  ItemPresentation getPresentation();

}
