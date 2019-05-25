// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.sblundy.elvish.psi.impl.*;

public interface ElvishTypes {

  IElementType ARGUMENT = new ElvishElementType("ARGUMENT");
  IElementType ARGUMENT_LIST = new ElvishElementType("ARGUMENT_LIST");
  IElementType ARGUMENT_LIST_CONTINUATION = new ElvishElementType("ARGUMENT_LIST_CONTINUATION");
  IElementType ARGUMENT_LIST_LINE = new ElvishElementType("ARGUMENT_LIST_LINE");
  IElementType ASSIGNMENT = new ElvishElementType("ASSIGNMENT");
  IElementType ASSIGNMENT_START = new ElvishElementType("ASSIGNMENT_START");
  IElementType COMMAND = new ElvishElementType("COMMAND");
  IElementType HEAD = new ElvishElementType("HEAD");
  IElementType LINE = new ElvishElementType("LINE");
  IElementType LINE_TERMINATOR = new ElvishElementType("LINE_TERMINATOR");
  IElementType ORDINARY_COMMAND = new ElvishElementType("ORDINARY_COMMAND");
  IElementType PIPELINE = new ElvishElementType("PIPELINE");
  IElementType SINGLE_QUOTED_STRING = new ElvishElementType("SINGLE_QUOTED_STRING");
  IElementType STRING = new ElvishElementType("STRING");
  IElementType VARIABLE = new ElvishElementType("VARIABLE");
  IElementType VARIABLE_REF = new ElvishElementType("VARIABLE_REF");

  IElementType AMPERSAND = new ElvishTokenType("&");
  IElementType BAREWORD = new ElvishTokenType("bareword");
  IElementType COMMENT = new ElvishTokenType("COMMENT");
  IElementType CONTINUATION = new ElvishTokenType("continuation");
  IElementType DOUBLE_QUOTE = new ElvishTokenType("\"");
  IElementType EOL = new ElvishTokenType("EOL");
  IElementType EQUALS = new ElvishTokenType("=");
  IElementType ESCAPED_SINGLE_QUOTED_TEXT = new ElvishTokenType("\\'");
  IElementType LEFT_BRACE = new ElvishTokenType("{");
  IElementType LEFT_BRACKET = new ElvishTokenType("[");
  IElementType LEFT_PAREN = new ElvishTokenType("(");
  IElementType PIPE = new ElvishTokenType("pipe");
  IElementType REF_MARKER = new ElvishTokenType("$");
  IElementType RIGHT_BRACE = new ElvishTokenType("}");
  IElementType RIGHT_BRACKET = new ElvishTokenType("]");
  IElementType RIGHT_PAREN = new ElvishTokenType(")");
  IElementType SINGLE_QUOTE = new ElvishTokenType("'");
  IElementType TEXT = new ElvishTokenType(".");
  IElementType TILDA = new ElvishTokenType("~");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new ElvishArgumentImpl(node);
      }
      else if (type == ARGUMENT_LIST) {
        return new ElvishArgumentListImpl(node);
      }
      else if (type == ARGUMENT_LIST_CONTINUATION) {
        return new ElvishArgumentListContinuationImpl(node);
      }
      else if (type == ARGUMENT_LIST_LINE) {
        return new ElvishArgumentListLineImpl(node);
      }
      else if (type == ASSIGNMENT) {
        return new ElvishAssignmentImpl(node);
      }
      else if (type == ASSIGNMENT_START) {
        return new ElvishAssignmentStartImpl(node);
      }
      else if (type == COMMAND) {
        return new ElvishCommandImpl(node);
      }
      else if (type == HEAD) {
        return new ElvishHeadImpl(node);
      }
      else if (type == LINE) {
        return new ElvishLineImpl(node);
      }
      else if (type == LINE_TERMINATOR) {
        return new ElvishLineTerminatorImpl(node);
      }
      else if (type == ORDINARY_COMMAND) {
        return new ElvishOrdinaryCommandImpl(node);
      }
      else if (type == PIPELINE) {
        return new ElvishPipelineImpl(node);
      }
      else if (type == SINGLE_QUOTED_STRING) {
        return new ElvishSingleQuotedStringImpl(node);
      }
      else if (type == STRING) {
        return new ElvishStringImpl(node);
      }
      else if (type == VARIABLE) {
        return new ElvishVariableImpl(node);
      }
      else if (type == VARIABLE_REF) {
        return new ElvishVariableRefImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
