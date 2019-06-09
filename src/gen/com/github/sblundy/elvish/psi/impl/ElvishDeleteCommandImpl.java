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

public class ElvishDeleteCommandImpl extends ASTWrapperPsiElement implements ElvishDeleteCommand {

  public ElvishDeleteCommandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitDeleteCommand(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishBareword> getBarewordList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishBareword.class);
  }

  @Override
  @NotNull
  public List<ElvishBraced> getBracedList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishBraced.class);
  }

  @Override
  @NotNull
  public List<ElvishDoubleQuoted> getDoubleQuotedList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishDoubleQuoted.class);
  }

  @Override
  @NotNull
  public List<ElvishExceptionCapture> getExceptionCaptureList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishExceptionCapture.class);
  }

  @Override
  @NotNull
  public List<ElvishLambda> getLambdaList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishLambda.class);
  }

  @Override
  @NotNull
  public List<ElvishList> getListList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishList.class);
  }

  @Override
  @NotNull
  public List<ElvishMap> getMapList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishMap.class);
  }

  @Override
  @NotNull
  public List<ElvishOutputCapture> getOutputCaptureList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishOutputCapture.class);
  }

  @Override
  @NotNull
  public List<ElvishSingleQuoted> getSingleQuotedList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishSingleQuoted.class);
  }

  @Override
  @NotNull
  public ElvishVariable getVariable() {
    return findNotNullChildByClass(ElvishVariable.class);
  }

  @Override
  @NotNull
  public List<ElvishVariableRef> getVariableRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVariableRef.class);
  }

}
