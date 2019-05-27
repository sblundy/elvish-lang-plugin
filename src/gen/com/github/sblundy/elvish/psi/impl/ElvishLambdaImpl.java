// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.github.sblundy.elvish.psi.*;

public class ElvishLambdaImpl extends ASTWrapperPsiElement implements ElvishLambda {

  public ElvishLambdaImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitLambda(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishAssignment> getAssignmentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishAssignment.class);
  }

  @Override
  @NotNull
  public List<ElvishDeleteStatement> getDeleteStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishDeleteStatement.class);
  }

  @Override
  @NotNull
  public List<ElvishForStatement> getForStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishForStatement.class);
  }

  @Override
  @NotNull
  public List<ElvishIfStatement> getIfStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishIfStatement.class);
  }

  @Override
  @NotNull
  public List<ElvishLineTerminator> getLineTerminatorList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishLineTerminator.class);
  }

  @Override
  @NotNull
  public List<ElvishOrdinaryCommand> getOrdinaryCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishOrdinaryCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishParameter> getParameterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishParameter.class);
  }

  @Override
  @NotNull
  public List<ElvishPipeline> getPipelineList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishPipeline.class);
  }

  @Override
  @NotNull
  public List<ElvishTryStatement> getTryStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishTryStatement.class);
  }

  @Override
  @NotNull
  public List<ElvishWhileStatement> getWhileStatementList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishWhileStatement.class);
  }

}
