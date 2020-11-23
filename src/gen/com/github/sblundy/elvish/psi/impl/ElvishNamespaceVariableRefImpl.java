// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import com.github.sblundy.elvish.psi.ElvishNamespaceVariableRefBase;
import com.github.sblundy.elvish.psi.*;

public class ElvishNamespaceVariableRefImpl extends ElvishNamespaceVariableRefBase implements ElvishNamespaceVariableRef {

  public ElvishNamespaceVariableRefImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ElvishVisitor visitor) {
    visitor.visitNamespaceVariableRef(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ElvishVisitor) accept((ElvishVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ElvishIndexRange> getIndexRangeList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishIndexRange.class);
  }

  @Override
  @NotNull
  public List<ElvishIndexSingle> getIndexSingleList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ElvishIndexSingle.class);
  }

  @Override
  @NotNull
  public ElvishNamespaceIdentifier getNamespaceIdentifier() {
    return findNotNullChildByClass(ElvishNamespaceIdentifier.class);
  }

  @Override
  @NotNull
  public ElvishVariableName getVariableName() {
    return findNotNullChildByClass(ElvishVariableName.class);
  }

}