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
  IElementType ASSIGNMENT_LEFT = new ElvishElementType("ASSIGNMENT_LEFT");
  IElementType ASSIGNMENT_RIGHT = new ElvishElementType("ASSIGNMENT_RIGHT");
  IElementType BLOCK = new ElvishElementType("BLOCK");
  IElementType COLLECTION = new ElvishElementType("COLLECTION");
  IElementType COMMAND = new ElvishElementType("COMMAND");
  IElementType CONDITION = new ElvishElementType("CONDITION");
  IElementType DELETE_STATEMENT = new ElvishElementType("DELETE_STATEMENT");
  IElementType DOUBLE_QUOTED_STRING = new ElvishElementType("DOUBLE_QUOTED_STRING");
  IElementType ELIF_STATEMENT = new ElvishElementType("ELIF_STATEMENT");
  IElementType ELSE_STATEMENT = new ElvishElementType("ELSE_STATEMENT");
  IElementType ESCAPED_SEQUENCE = new ElvishElementType("ESCAPED_SEQUENCE");
  IElementType EXCEPT_STATEMENT = new ElvishElementType("EXCEPT_STATEMENT");
  IElementType FINALLY_STATEMENT = new ElvishElementType("FINALLY_STATEMENT");
  IElementType FOR_STATEMENT = new ElvishElementType("FOR_STATEMENT");
  IElementType HEAD = new ElvishElementType("HEAD");
  IElementType IF_STATEMENT = new ElvishElementType("IF_STATEMENT");
  IElementType LINE = new ElvishElementType("LINE");
  IElementType LINE_TERMINATOR = new ElvishElementType("LINE_TERMINATOR");
  IElementType ORDINARY_COMMAND = new ElvishElementType("ORDINARY_COMMAND");
  IElementType OUTPUT_CAPTURE = new ElvishElementType("OUTPUT_CAPTURE");
  IElementType PIPELINE = new ElvishElementType("PIPELINE");
  IElementType SINGLE_QUOTED_STRING = new ElvishElementType("SINGLE_QUOTED_STRING");
  IElementType STRING = new ElvishElementType("STRING");
  IElementType TRY_STATEMENT = new ElvishElementType("TRY_STATEMENT");
  IElementType VARIABLE_DECLARATION = new ElvishElementType("VARIABLE_DECLARATION");
  IElementType VARIABLE_INDEX = new ElvishElementType("VARIABLE_INDEX");
  IElementType VARIABLE_NAME = new ElvishElementType("VARIABLE_NAME");
  IElementType VARIABLE_REF = new ElvishElementType("VARIABLE_REF");
  IElementType WHILE_STATEMENT = new ElvishElementType("WHILE_STATEMENT");

  IElementType AMPERSAND = new ElvishTokenType("&");
  IElementType BAREWORD = new ElvishTokenType("bareword");
  IElementType BUILTIN_OPERATOR_FN = new ElvishTokenType("BUILTIN_OPERATOR_FN");
  IElementType CLOSE_BRACE = new ElvishTokenType("close_brace");
  IElementType CLOSE_BRACKET = new ElvishTokenType("close_bracket");
  IElementType CLOSE_PARAN = new ElvishTokenType("close_paran");
  IElementType COMMENT = new ElvishTokenType("COMMENT");
  IElementType CONTINUATION = new ElvishTokenType("continuation");
  IElementType DOUBLE_QUOTE = new ElvishTokenType("\"");
  IElementType EOL = new ElvishTokenType("EOL");
  IElementType EQUALS = new ElvishTokenType("=");
  IElementType ESCAPED_QUOTED_TEXT = new ElvishTokenType("escaped_sequence");
  IElementType KEYWORD_DEL = new ElvishTokenType("del");
  IElementType KEYWORD_ELIF = new ElvishTokenType("elif");
  IElementType KEYWORD_ELSE = new ElvishTokenType("else");
  IElementType KEYWORD_EXCEPT = new ElvishTokenType("except");
  IElementType KEYWORD_FINALLY = new ElvishTokenType("finally");
  IElementType KEYWORD_FOR = new ElvishTokenType("for");
  IElementType KEYWORD_IF = new ElvishTokenType("if");
  IElementType KEYWORD_TRY = new ElvishTokenType("try");
  IElementType KEYWORD_WHILE = new ElvishTokenType("while");
  IElementType OPEN_BRACE = new ElvishTokenType("open_brace");
  IElementType OPEN_BRACKET = new ElvishTokenType("open_bracket");
  IElementType OPEN_PARAN = new ElvishTokenType("open_paran");
  IElementType PIPE = new ElvishTokenType("pipe");
  IElementType REF_MARKER = new ElvishTokenType("$");
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
      else if (type == ASSIGNMENT_LEFT) {
        return new ElvishAssignmentLeftImpl(node);
      }
      else if (type == ASSIGNMENT_RIGHT) {
        return new ElvishAssignmentRightImpl(node);
      }
      else if (type == BLOCK) {
        return new ElvishBlockImpl(node);
      }
      else if (type == COLLECTION) {
        return new ElvishCollectionImpl(node);
      }
      else if (type == COMMAND) {
        return new ElvishCommandImpl(node);
      }
      else if (type == CONDITION) {
        return new ElvishConditionImpl(node);
      }
      else if (type == DELETE_STATEMENT) {
        return new ElvishDeleteStatementImpl(node);
      }
      else if (type == DOUBLE_QUOTED_STRING) {
        return new ElvishDoubleQuotedStringImpl(node);
      }
      else if (type == ELIF_STATEMENT) {
        return new ElvishElifStatementImpl(node);
      }
      else if (type == ELSE_STATEMENT) {
        return new ElvishElseStatementImpl(node);
      }
      else if (type == ESCAPED_SEQUENCE) {
        return new ElvishEscapedSequenceImpl(node);
      }
      else if (type == EXCEPT_STATEMENT) {
        return new ElvishExceptStatementImpl(node);
      }
      else if (type == FINALLY_STATEMENT) {
        return new ElvishFinallyStatementImpl(node);
      }
      else if (type == FOR_STATEMENT) {
        return new ElvishForStatementImpl(node);
      }
      else if (type == HEAD) {
        return new ElvishHeadImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new ElvishIfStatementImpl(node);
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
      else if (type == OUTPUT_CAPTURE) {
        return new ElvishOutputCaptureImpl(node);
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
      else if (type == TRY_STATEMENT) {
        return new ElvishTryStatementImpl(node);
      }
      else if (type == VARIABLE_DECLARATION) {
        return new ElvishVariableDeclarationImpl(node);
      }
      else if (type == VARIABLE_INDEX) {
        return new ElvishVariableIndexImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new ElvishVariableNameImpl(node);
      }
      else if (type == VARIABLE_REF) {
        return new ElvishVariableRefImpl(node);
      }
      else if (type == WHILE_STATEMENT) {
        return new ElvishWhileStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
