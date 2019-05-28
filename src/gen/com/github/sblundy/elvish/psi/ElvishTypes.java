// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.sblundy.elvish.psi.impl.*;

public interface ElvishTypes {

  IElementType ARGUMENT = new ElvishElementType("ARGUMENT");
  IElementType ASSIGNMENT = new ElvishElementType("ASSIGNMENT");
  IElementType BLOCK = new ElvishElementType("BLOCK");
  IElementType COLLECTION = new ElvishElementType("COLLECTION");
  IElementType COMPOUND_EXPRESSION = new ElvishElementType("COMPOUND_EXPRESSION");
  IElementType CONDITION = new ElvishElementType("CONDITION");
  IElementType DELETE_STATEMENT = new ElvishElementType("DELETE_STATEMENT");
  IElementType DOUBLE_QUOTED_STRING = new ElvishElementType("DOUBLE_QUOTED_STRING");
  IElementType ELIF_STATEMENT = new ElvishElementType("ELIF_STATEMENT");
  IElementType ELSE_STATEMENT = new ElvishElementType("ELSE_STATEMENT");
  IElementType ESCAPED_SEQUENCE = new ElvishElementType("ESCAPED_SEQUENCE");
  IElementType EXCEPT_STATEMENT = new ElvishElementType("EXCEPT_STATEMENT");
  IElementType FINALLY_STATEMENT = new ElvishElementType("FINALLY_STATEMENT");
  IElementType FN_STATEMENT = new ElvishElementType("FN_STATEMENT");
  IElementType FOR_STATEMENT = new ElvishElementType("FOR_STATEMENT");
  IElementType HEAD = new ElvishElementType("HEAD");
  IElementType IF_STATEMENT = new ElvishElementType("IF_STATEMENT");
  IElementType INVALID_ESCAPE_SEQUENCE = new ElvishElementType("INVALID_ESCAPE_SEQUENCE");
  IElementType LAMBDA = new ElvishElementType("LAMBDA");
  IElementType LINE_TERMINATOR = new ElvishElementType("LINE_TERMINATOR");
  IElementType MAP_ENTRY = new ElvishElementType("MAP_ENTRY");
  IElementType MAP_OR_LIST = new ElvishElementType("MAP_OR_LIST");
  IElementType ORDINARY_COMMAND = new ElvishElementType("ORDINARY_COMMAND");
  IElementType OUTPUT_CAPTURE = new ElvishElementType("OUTPUT_CAPTURE");
  IElementType PARAMETER = new ElvishElementType("PARAMETER");
  IElementType PIPELINE = new ElvishElementType("PIPELINE");
  IElementType SINGLE_QUOTED_STRING = new ElvishElementType("SINGLE_QUOTED_STRING");
  IElementType TRY_STATEMENT = new ElvishElementType("TRY_STATEMENT");
  IElementType VARIABLE_DECLARATION = new ElvishElementType("VARIABLE_DECLARATION");
  IElementType VARIABLE_REF = new ElvishElementType("VARIABLE_REF");
  IElementType WHILE_STATEMENT = new ElvishElementType("WHILE_STATEMENT");

  IElementType AMPERSAND = new ElvishTokenType("&");
  IElementType AT_VARIABLE = new ElvishTokenType("@VARIABLE");
  IElementType BAREWORD = new ElvishTokenType("BAREWORD");
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
  IElementType INVALID_ESCAPED_QUOTED_TEXT = new ElvishTokenType("invalid_escaped_sequence");
  IElementType KEYWORD_DEL = new ElvishTokenType("del");
  IElementType KEYWORD_ELIF = new ElvishTokenType("elif");
  IElementType KEYWORD_ELSE = new ElvishTokenType("else");
  IElementType KEYWORD_EXCEPT = new ElvishTokenType("except");
  IElementType KEYWORD_FINALLY = new ElvishTokenType("finally");
  IElementType KEYWORD_FN = new ElvishTokenType("fn");
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
  IElementType VARIABLE = new ElvishTokenType("VARIABLE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new ElvishArgumentImpl(node);
      }
      else if (type == ASSIGNMENT) {
        return new ElvishAssignmentImpl(node);
      }
      else if (type == BLOCK) {
        return new ElvishBlockImpl(node);
      }
      else if (type == COLLECTION) {
        return new ElvishCollectionImpl(node);
      }
      else if (type == COMPOUND_EXPRESSION) {
        return new ElvishCompoundExpressionImpl(node);
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
      else if (type == FN_STATEMENT) {
        return new ElvishFnStatementImpl(node);
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
      else if (type == INVALID_ESCAPE_SEQUENCE) {
        return new ElvishInvalidEscapeSequenceImpl(node);
      }
      else if (type == LAMBDA) {
        return new ElvishLambdaImpl(node);
      }
      else if (type == LINE_TERMINATOR) {
        return new ElvishLineTerminatorImpl(node);
      }
      else if (type == MAP_ENTRY) {
        return new ElvishMapEntryImpl(node);
      }
      else if (type == MAP_OR_LIST) {
        return new ElvishMapOrListImpl(node);
      }
      else if (type == ORDINARY_COMMAND) {
        return new ElvishOrdinaryCommandImpl(node);
      }
      else if (type == OUTPUT_CAPTURE) {
        return new ElvishOutputCaptureImpl(node);
      }
      else if (type == PARAMETER) {
        return new ElvishParameterImpl(node);
      }
      else if (type == PIPELINE) {
        return new ElvishPipelineImpl(node);
      }
      else if (type == SINGLE_QUOTED_STRING) {
        return new ElvishSingleQuotedStringImpl(node);
      }
      else if (type == TRY_STATEMENT) {
        return new ElvishTryStatementImpl(node);
      }
      else if (type == VARIABLE_DECLARATION) {
        return new ElvishVariableDeclarationImpl(node);
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
