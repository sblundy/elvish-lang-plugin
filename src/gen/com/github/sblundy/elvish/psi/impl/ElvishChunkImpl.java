// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.github.sblundy.elvish.psi.ASTWrapperElvishPsiElement;
import com.github.sblundy.elvish.psi.*;

public class ElvishChunkImpl extends ASTWrapperElvishPsiElement implements ElvishChunk {

  public ElvishChunkImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitChunk(this);
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
  public List<ElvishChunkBlock> getChunkBlockList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishChunkBlock.class);
  }

  @Override
  @NotNull
  public List<ElvishDeleteCommand> getDeleteCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishDeleteCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishFnCommand> getFnCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishFnCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishLogicCommand> getLogicCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishLogicCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishPipelineSep> getPipelineSepList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishPipelineSep.class);
  }

  @Override
  @NotNull
  public List<ElvishSetCommand> getSetCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishSetCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishUseCommand> getUseCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishUseCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishVarCommand> getVarCommandList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVarCommand.class);
  }

  @Override
  @NotNull
  public List<ElvishArgument> getArgumentList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishArgument.class);
  }

  @Override
  @NotNull
  public List<ElvishHead> getHeadList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishHead.class);
  }

  @Override
  @NotNull
  public List<ElvishVariableDeclaration> getVariableDeclarations() {
    return ElvishPsiImplUtil.getVariableDeclarations(this);
  }

}
