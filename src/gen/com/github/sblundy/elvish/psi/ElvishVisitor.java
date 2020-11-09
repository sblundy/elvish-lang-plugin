// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ElvishVisitor extends PsiElementVisitor {

  public void visitArray(@NotNull ElvishArray o) {
    visitPsiElement(o);
  }

  public void visitAssignment(@NotNull ElvishAssignment o) {
    visitPsiElement(o);
  }

  public void visitBareword(@NotNull ElvishBareword o) {
    visitPsiElement(o);
  }

  public void visitBraced(@NotNull ElvishBraced o) {
    visitPsiElement(o);
  }

  public void visitBracedBareword(@NotNull ElvishBracedBareword o) {
    visitPsiElement(o);
  }

  public void visitBracedSep(@NotNull ElvishBracedSep o) {
    visitPsiElement(o);
  }

  public void visitChunk(@NotNull ElvishChunk o) {
    visitPsiElement(o);
  }

  public void visitCollection(@NotNull ElvishCollection o) {
    visitPsiElement(o);
  }

  public void visitCommandBareword(@NotNull ElvishCommandBareword o) {
    visitPsiElement(o);
  }

  public void visitCommandExpression(@NotNull ElvishCommandExpression o) {
    visitReferenceWithNamespacePsiElement(o);
  }

  public void visitCompound(@NotNull ElvishCompound o) {
    visitPsiElement(o);
  }

  public void visitCondition(@NotNull ElvishCondition o) {
    visitPsiElement(o);
  }

  public void visitDeleteCommand(@NotNull ElvishDeleteCommand o) {
    visitPsiElement(o);
  }

  public void visitDoubleQuoted(@NotNull ElvishDoubleQuoted o) {
    visitPsiElement(o);
  }

  public void visitElIfBlock(@NotNull ElvishElIfBlock o) {
    visitPsiElement(o);
  }

  public void visitElseBlock(@NotNull ElvishElseBlock o) {
    visitPsiElement(o);
  }

  public void visitExceptBlock(@NotNull ElvishExceptBlock o) {
    visitVariableDeclaringBlock(o);
  }

  public void visitExceptionCapture(@NotNull ElvishExceptionCapture o) {
    visitPsiElement(o);
  }

  public void visitExitusRedir(@NotNull ElvishExitusRedir o) {
    visitPsiElement(o);
  }

  public void visitFinallyBlock(@NotNull ElvishFinallyBlock o) {
    visitPsiElement(o);
  }

  public void visitFnCommand(@NotNull ElvishFnCommand o) {
    visitFunctionDeclaration(o);
  }

  public void visitForCommand(@NotNull ElvishForCommand o) {
    visitVariableDeclaringBlock(o);
  }

  public void visitIfCommand(@NotNull ElvishIfCommand o) {
    visitPsiElement(o);
  }

  public void visitLambda(@NotNull ElvishLambda o) {
    visitPsiElement(o);
  }

  public void visitLambdaArguments(@NotNull ElvishLambdaArguments o) {
    visitPsiElement(o);
  }

  public void visitLibModuleSpec(@NotNull ElvishLibModuleSpec o) {
    visitPsiElement(o);
  }

  public void visitList(@NotNull ElvishList o) {
    visitPsiElement(o);
  }

  public void visitLogicCommand(@NotNull ElvishLogicCommand o) {
    visitPsiElement(o);
  }

  public void visitMap(@NotNull ElvishMap o) {
    visitPsiElement(o);
  }

  public void visitMapPair(@NotNull ElvishMapPair o) {
    visitPsiElement(o);
  }

  public void visitModuleAlias(@NotNull ElvishModuleAlias o) {
    visitPsiElement(o);
  }

  public void visitNamespaceName(@NotNull ElvishNamespaceName o) {
    visitPsiElement(o);
  }

  public void visitOutputCapture(@NotNull ElvishOutputCapture o) {
    visitPsiElement(o);
  }

  public void visitPipelineSep(@NotNull ElvishPipelineSep o) {
    visitPsiElement(o);
  }

  public void visitRedir(@NotNull ElvishRedir o) {
    visitPsiElement(o);
  }

  public void visitRelativeModuleSpec(@NotNull ElvishRelativeModuleSpec o) {
    visitPsiElement(o);
  }

  public void visitSingleQuoted(@NotNull ElvishSingleQuoted o) {
    visitPsiElement(o);
  }

  public void visitTryCommand(@NotNull ElvishTryCommand o) {
    visitPsiElement(o);
  }

  public void visitUseCommand(@NotNull ElvishUseCommand o) {
    visitPsiElement(o);
  }

  public void visitVariable(@NotNull ElvishVariable o) {
    visitVariableDeclaration(o);
  }

  public void visitVariableName(@NotNull ElvishVariableName o) {
    visitPsiElement(o);
  }

  public void visitVariableRef(@NotNull ElvishVariableRef o) {
    visitReferenceWithNamespacePsiElement(o);
  }

  public void visitWhileCommand(@NotNull ElvishWhileCommand o) {
    visitPsiElement(o);
  }

  public void visitArgument(@NotNull ElvishArgument o) {
    visitPsiElement(o);
  }

  public void visitHead(@NotNull ElvishHead o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull ElvishParameter o) {
    visitVariableDeclaration(o);
  }

  public void visitFunctionDeclaration(@NotNull ElvishFunctionDeclaration o) {
    visitPsiElement(o);
  }

  public void visitVariableDeclaration(@NotNull ElvishVariableDeclaration o) {
    visitPsiElement(o);
  }

  public void visitVariableDeclaringBlock(@NotNull ElvishVariableDeclaringBlock o) {
    visitPsiElement(o);
  }

  public void visitReferenceWithNamespacePsiElement(@NotNull ReferenceWithNamespacePsiElement o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
