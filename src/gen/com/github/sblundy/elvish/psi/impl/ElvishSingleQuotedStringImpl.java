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

public class ElvishSingleQuotedStringImpl extends ASTWrapperPsiElement implements ElvishSingleQuotedString {

  public ElvishSingleQuotedStringImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitSingleQuotedString(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishEscapedSequence> getEscapedSequenceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishEscapedSequence.class);
  }

  @Override
  @NotNull
  public List<ElvishInvalidEscapeSequence> getInvalidEscapeSequenceList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishInvalidEscapeSequence.class);
  }

}
