// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiNameIdentifierOwner;

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

  public void visitBlock(@NotNull ElvishBlock o) {
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

  public void visitBuiltinNamespace(@NotNull ElvishBuiltinNamespace o) {
    visitNamespaceIdentifier(o);
  }

  public void visitChunk(@NotNull ElvishChunk o) {
    visitVariableDeclarationsContainer(o);
  }

  public void visitChunkBlock(@NotNull ElvishChunkBlock o) {
    visitBlock(o);
  }

  public void visitCollection(@NotNull ElvishCollection o) {
    visitPsiElement(o);
  }

  public void visitCommand(@NotNull ElvishCommand o) {
    visitPsiElement(o);
  }

  public void visitCommandBareword(@NotNull ElvishCommandBareword o) {
    visitPsiElement(o);
  }

  public void visitCommandExpression(@NotNull ElvishCommandExpression o) {
    visitCommand(o);
  }

  public void visitCompound(@NotNull ElvishCompound o) {
    visitPsiElement(o);
  }

  public void visitCondition(@NotNull ElvishCondition o) {
    visitPsiElement(o);
  }

  public void visitDeleteCommand(@NotNull ElvishDeleteCommand o) {
    visitSpecialCommand(o);
  }

  public void visitDoubleQuoted(@NotNull ElvishDoubleQuoted o) {
    visitPsiElement(o);
  }

  public void visitDoubleQuotedVariableName(@NotNull ElvishDoubleQuotedVariableName o) {
    visitVariableName(o);
  }

  public void visitElIfBlock(@NotNull ElvishElIfBlock o) {
    visitChunkBlock(o);
  }

  public void visitElseBlock(@NotNull ElvishElseBlock o) {
    visitChunkBlock(o);
  }

  public void visitEnvVarNamespace(@NotNull ElvishEnvVarNamespace o) {
    visitNamespaceIdentifier(o);
  }

  public void visitExceptBlock(@NotNull ElvishExceptBlock o) {
    visitChunkBlock(o);
    // visitVariableDeclaration(o);
  }

  public void visitExceptionCapture(@NotNull ElvishExceptionCapture o) {
    visitPsiElement(o);
  }

  public void visitExitusRedir(@NotNull ElvishExitusRedir o) {
    visitPsiElement(o);
  }

  public void visitExternalVariableReference(@NotNull ElvishExternalVariableReference o) {
    visitVariableReference(o);
  }

  public void visitExternalsNamespace(@NotNull ElvishExternalsNamespace o) {
    visitNamespaceIdentifier(o);
  }

  public void visitFinallyBlock(@NotNull ElvishFinallyBlock o) {
    visitChunkBlock(o);
  }

  public void visitFnCommand(@NotNull ElvishFnCommand o) {
    visitFunctionDeclaration(o);
    // visitLambdaScope(o);
    // visitPsiNameIdentifierOwner(o);
    // visitSpecialCommand(o);
  }

  public void visitForCommand(@NotNull ElvishForCommand o) {
    visitChunkBlock(o);
    // visitSpecialCommand(o);
    // visitVariableDeclaration(o);
  }

  public void visitFunctionDeclaration(@NotNull ElvishFunctionDeclaration o) {
    visitPsiElement(o);
  }

  public void visitIfCommand(@NotNull ElvishIfCommand o) {
    visitChunkBlock(o);
    // visitSpecialCommand(o);
  }

  public void visitIndex(@NotNull ElvishIndex o) {
    visitPsiElement(o);
  }

  public void visitIndexRange(@NotNull ElvishIndexRange o) {
    visitIndex(o);
  }

  public void visitIndexSingle(@NotNull ElvishIndexSingle o) {
    visitIndex(o);
  }

  public void visitLValue(@NotNull ElvishLValue o) {
    visitLValueVariable(o);
  }

  public void visitLValueVariable(@NotNull ElvishLValueVariable o) {
    visitPsiElement(o);
  }

  public void visitLambda(@NotNull ElvishLambda o) {
    visitLambdaScope(o);
  }

  public void visitLambdaArguments(@NotNull ElvishLambdaArguments o) {
    visitPsiElement(o);
  }

  public void visitLambdaScope(@NotNull ElvishLambdaScope o) {
    visitLexicalScope(o);
  }

  public void visitLegacyVariableAssignment(@NotNull ElvishLegacyVariableAssignment o) {
    visitVariableAssignment(o);
    // visitLValue(o);
    // visitVariableDeclaration(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitLexicalScope(@NotNull ElvishLexicalScope o) {
    visitBlock(o);
  }

  public void visitLibModuleSpec(@NotNull ElvishLibModuleSpec o) {
    visitModuleSpec(o);
  }

  public void visitList(@NotNull ElvishList o) {
    visitPsiElement(o);
  }

  public void visitLocalNamespace(@NotNull ElvishLocalNamespace o) {
    visitNamespaceIdentifier(o);
  }

  public void visitLocalScopeVariableAssignment(@NotNull ElvishLocalScopeVariableAssignment o) {
    visitVariableDeclaration(o);
    // visitLValue(o);
    // visitVariableAssignment(o);
    // visitPsiNameIdentifierOwner(o);
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

  public void visitModuleSpec(@NotNull ElvishModuleSpec o) {
    visitPsiElement(o);
  }

  public void visitNamespaceCommandExpression(@NotNull ElvishNamespaceCommandExpression o) {
    visitCommand(o);
  }

  public void visitNamespaceIdentifier(@NotNull ElvishNamespaceIdentifier o) {
    visitPsiElement(o);
  }

  public void visitNamespaceName(@NotNull ElvishNamespaceName o) {
    visitNamespaceIdentifier(o);
  }

  public void visitNamespaceVariableAssignment(@NotNull ElvishNamespaceVariableAssignment o) {
    visitVariableAssignment(o);
    // visitLValue(o);
    // visitExternalVariableReference(o);
  }

  public void visitNamespaceVariableRef(@NotNull ElvishNamespaceVariableRef o) {
    visitExternalVariableReference(o);
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
    visitModuleSpec(o);
  }

  public void visitSetCommand(@NotNull ElvishSetCommand o) {
    visitSpecialCommand(o);
  }

  public void visitSetLValue(@NotNull ElvishSetLValue o) {
    visitVariableAssignment(o);
    // visitLValue(o);
  }

  public void visitSingleQuoted(@NotNull ElvishSingleQuoted o) {
    visitPsiElement(o);
  }

  public void visitSingleQuotedVariableName(@NotNull ElvishSingleQuotedVariableName o) {
    visitVariableName(o);
  }

  public void visitSpecialScopeCommandExpression(@NotNull ElvishSpecialScopeCommandExpression o) {
    visitCommand(o);
  }

  public void visitSpecialScopeVariableRef(@NotNull ElvishSpecialScopeVariableRef o) {
    visitVariableReference(o);
  }

  public void visitTryCommand(@NotNull ElvishTryCommand o) {
    visitChunkBlock(o);
    // visitSpecialCommand(o);
  }

  public void visitUnquotedVariableName(@NotNull ElvishUnquotedVariableName o) {
    visitVariableName(o);
  }

  public void visitUpNamespace(@NotNull ElvishUpNamespace o) {
    visitNamespaceIdentifier(o);
  }

  public void visitUpScopeVariableAssignment(@NotNull ElvishUpScopeVariableAssignment o) {
    visitVariableAssignment(o);
    // visitLValue(o);
  }

  public void visitUseCommand(@NotNull ElvishUseCommand o) {
    visitSpecialCommand(o);
  }

  public void visitVarCommand(@NotNull ElvishVarCommand o) {
    visitSpecialCommand(o);
  }

  public void visitVarIndex(@NotNull ElvishVarIndex o) {
    visitPsiElement(o);
  }

  public void visitVarLValue(@NotNull ElvishVarLValue o) {
    visitVariableAssignment(o);
    // visitLValue(o);
    // visitVariableDeclaration(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitVariableAssignment(@NotNull ElvishVariableAssignment o) {
    visitLValueVariable(o);
  }

  public void visitVariableDeclaration(@NotNull ElvishVariableDeclaration o) {
    visitLValueVariable(o);
  }

  public void visitVariableName(@NotNull ElvishVariableName o) {
    visitPsiElement(o);
  }

  public void visitVariableRef(@NotNull ElvishVariableRef o) {
    visitVariableReference(o);
  }

  public void visitVariableReference(@NotNull ElvishVariableReference o) {
    visitPsiElement(o);
  }

  public void visitWhileCommand(@NotNull ElvishWhileCommand o) {
    visitChunkBlock(o);
    // visitSpecialCommand(o);
  }

  public void visitArgument(@NotNull ElvishArgument o) {
    visitPsiElement(o);
  }

  public void visitHead(@NotNull ElvishHead o) {
    visitPsiElement(o);
  }

  public void visitParameter(@NotNull ElvishParameter o) {
    visitVariableDeclaration(o);
    // visitPsiNameIdentifierOwner(o);
  }

  public void visitSpecialCommand(@NotNull ElvishSpecialCommand o) {
    visitPsiElement(o);
  }

  public void visitVariableDeclarationsContainer(@NotNull ElvishVariableDeclarationsContainer o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull ElvishPsiElement o) {
    visitElement(o);
  }

}
