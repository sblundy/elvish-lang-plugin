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

public class ElvishMapOrListImpl extends ASTWrapperPsiElement implements ElvishMapOrList {

  public ElvishMapOrListImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitMapOrList(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishCompoundExpression> getCompoundExpressionList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishCompoundExpression.class);
  }

  @Override
  @NotNull
  public List<ElvishDoubleQuotedString> getDoubleQuotedStringList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishDoubleQuotedString.class);
  }

  @Override
  @NotNull
  public List<ElvishEmptyMap> getEmptyMapList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishEmptyMap.class);
  }

  @Override
  @NotNull
  public List<ElvishLambda> getLambdaList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishLambda.class);
  }

  @Override
  @NotNull
  public List<ElvishMapEntry> getMapEntryList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishMapEntry.class);
  }

  @Override
  @NotNull
  public List<ElvishMapOrList> getMapOrListList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishMapOrList.class);
  }

  @Override
  @NotNull
  public List<ElvishOutputCapture> getOutputCaptureList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishOutputCapture.class);
  }

  @Override
  @NotNull
  public List<ElvishSingleQuotedString> getSingleQuotedStringList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishSingleQuotedString.class);
  }

  @Override
  @NotNull
  public List<ElvishVariableRef> getVariableRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVariableRef.class);
  }

}
