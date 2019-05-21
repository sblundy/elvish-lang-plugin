// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ElvishVisitor extends PsiElementVisitor {

  public void visitArgument(@NotNull ElvishArgument o) {
    visitPsiElement(o);
  }

  public void visitArgumentList(@NotNull ElvishArgumentList o) {
    visitPsiElement(o);
  }

  public void visitCommand(@NotNull ElvishCommand o) {
    visitPsiElement(o);
  }

  public void visitHead(@NotNull ElvishHead o) {
    visitPsiElement(o);
  }

  public void visitLine(@NotNull ElvishLine o) {
    visitPsiElement(o);
  }

  public void visitLineTerminator(@NotNull ElvishLineTerminator o) {
    visitPsiElement(o);
  }

  public void visitOrdinaryCommand(@NotNull ElvishOrdinaryCommand o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
