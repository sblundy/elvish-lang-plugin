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

public class ElvishIndexRangeImpl extends ASTWrapperElvishPsiElement implements ElvishIndexRange {

  public ElvishIndexRangeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitIndexRange(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
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
  public List<ElvishVariableName> getVariableNameList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVariableName.class);
  }

  @Override
  @NotNull
  public List<ElvishVariableRef> getVariableRefList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishVariableRef.class);
  }

}
