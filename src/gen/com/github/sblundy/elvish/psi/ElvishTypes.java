// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import com.github.sblundy.elvish.psi.impl.*;

public interface ElvishTypes {

  IElementType ARGUMENT = new ElvishElementType("ARGUMENT");
  IElementType ARRAY = new ElvishElementType("ARRAY");
  IElementType ASSIGNMENT = new ElvishElementType("ASSIGNMENT");
  IElementType BAREWORD = new ElvishElementType("BAREWORD");
  IElementType BRACED = new ElvishElementType("BRACED");
  IElementType BRACED_BAREWORD = new ElvishElementType("BRACED_BAREWORD");
  IElementType BRACED_SEP = new ElvishElementType("BRACED_SEP");
  IElementType BUILTIN_NAMESPACE = new ElvishElementType("BUILTIN_NAMESPACE");
  IElementType CHUNK = new ElvishElementType("CHUNK");
  IElementType COLLECTION = new ElvishElementType("COLLECTION");
  IElementType COMMAND_BAREWORD = new ElvishElementType("COMMAND_BAREWORD");
  IElementType COMMAND_EXPRESSION = new ElvishElementType("COMMAND_EXPRESSION");
  IElementType COMPOUND = new ElvishElementType("COMPOUND");
  IElementType CONDITION = new ElvishElementType("CONDITION");
  IElementType DELETE_COMMAND = new ElvishElementType("DELETE_COMMAND");
  IElementType DOUBLE_QUOTED = new ElvishElementType("DOUBLE_QUOTED");
  IElementType ELSE_BLOCK = new ElvishElementType("ELSE_BLOCK");
  IElementType EL_IF_BLOCK = new ElvishElementType("EL_IF_BLOCK");
  IElementType ENV_VAR_NAMESPACE = new ElvishElementType("ENV_VAR_NAMESPACE");
  IElementType EXCEPTION_CAPTURE = new ElvishElementType("EXCEPTION_CAPTURE");
  IElementType EXCEPT_BLOCK = new ElvishElementType("EXCEPT_BLOCK");
  IElementType EXITUS_REDIR = new ElvishElementType("EXITUS_REDIR");
  IElementType EXTERNALS_NAMESPACE = new ElvishElementType("EXTERNALS_NAMESPACE");
  IElementType FINALLY_BLOCK = new ElvishElementType("FINALLY_BLOCK");
  IElementType FN_COMMAND = new ElvishElementType("FN_COMMAND");
  IElementType FOR_COMMAND = new ElvishElementType("FOR_COMMAND");
  IElementType HEAD = new ElvishElementType("HEAD");
  IElementType IF_COMMAND = new ElvishElementType("IF_COMMAND");
  IElementType INDEX_RANGE = new ElvishElementType("INDEX_RANGE");
  IElementType INDEX_SINGLE = new ElvishElementType("INDEX_SINGLE");
  IElementType LAMBDA = new ElvishElementType("LAMBDA");
  IElementType LAMBDA_ARGUMENTS = new ElvishElementType("LAMBDA_ARGUMENTS");
  IElementType LAMBDA_BLOCK = new ElvishElementType("LAMBDA_BLOCK");
  IElementType LIB_MODULE_SPEC = new ElvishElementType("LIB_MODULE_SPEC");
  IElementType LIST = new ElvishElementType("LIST");
  IElementType LOCAL_NAMESPACE = new ElvishElementType("LOCAL_NAMESPACE");
  IElementType LOCAL_SCOPE_VARIABLE_ASSIGNMENT = new ElvishElementType("LOCAL_SCOPE_VARIABLE_ASSIGNMENT");
  IElementType LOGIC_COMMAND = new ElvishElementType("LOGIC_COMMAND");
  IElementType MAP = new ElvishElementType("MAP");
  IElementType MAP_PAIR = new ElvishElementType("MAP_PAIR");
  IElementType MODULE_ALIAS = new ElvishElementType("MODULE_ALIAS");
  IElementType NAMESPACE_COMMAND_EXPRESSION = new ElvishElementType("NAMESPACE_COMMAND_EXPRESSION");
  IElementType NAMESPACE_NAME = new ElvishElementType("NAMESPACE_NAME");
  IElementType NAMESPACE_VARIABLE_ASSIGNMENT = new ElvishElementType("NAMESPACE_VARIABLE_ASSIGNMENT");
  IElementType NAMESPACE_VARIABLE_REF = new ElvishElementType("NAMESPACE_VARIABLE_REF");
  IElementType OUTPUT_CAPTURE = new ElvishElementType("OUTPUT_CAPTURE");
  IElementType PARAMETER = new ElvishElementType("PARAMETER");
  IElementType PIPELINE_SEP = new ElvishElementType("PIPELINE_SEP");
  IElementType REDIR = new ElvishElementType("REDIR");
  IElementType RELATIVE_MODULE_SPEC = new ElvishElementType("RELATIVE_MODULE_SPEC");
  IElementType SINGLE_QUOTED = new ElvishElementType("SINGLE_QUOTED");
  IElementType SPECIAL_SCOPE_COMMAND_EXPRESSION = new ElvishElementType("SPECIAL_SCOPE_COMMAND_EXPRESSION");
  IElementType SPECIAL_SCOPE_VARIABLE_REF = new ElvishElementType("SPECIAL_SCOPE_VARIABLE_REF");
  IElementType TRY_COMMAND = new ElvishElementType("TRY_COMMAND");
  IElementType UP_NAMESPACE = new ElvishElementType("UP_NAMESPACE");
  IElementType UP_SCOPE_VARIABLE_ASSIGNMENT = new ElvishElementType("UP_SCOPE_VARIABLE_ASSIGNMENT");
  IElementType USE_COMMAND = new ElvishElementType("USE_COMMAND");
  IElementType VARIABLE = new ElvishElementType("VARIABLE");
  IElementType VARIABLE_NAME = new ElvishElementType("VARIABLE_NAME");
  IElementType VARIABLE_REF = new ElvishElementType("VARIABLE_REF");
  IElementType WHILE_COMMAND = new ElvishElementType("WHILE_COMMAND");

  IElementType AMPERSAND = new ElvishTokenType("&");
  IElementType AT_SYMBOL = new ElvishTokenType("@");
  IElementType BACKSLASH = new ElvishTokenType("/");
  IElementType BAREWORD_CHAR = new ElvishTokenType("BAREWORD_CHAR");
  IElementType CLOSE_BRACE = new ElvishTokenType("}");
  IElementType CLOSE_BRACKET = new ElvishTokenType("]");
  IElementType CLOSE_PARAN = new ElvishTokenType(")");
  IElementType COLON = new ElvishTokenType(":");
  IElementType COMMA = new ElvishTokenType(",");
  IElementType COMMAND_BAREWORD_CHAR = new ElvishTokenType("COMMAND_BAREWORD_CHAR");
  IElementType COMMENT = new ElvishTokenType("COMMENT");
  IElementType CONTINUATION = new ElvishTokenType("CONTINUATION");
  IElementType DOLLAR_SIGN = new ElvishTokenType("$");
  IElementType DOUBLE_QUOTE = new ElvishTokenType("\"");
  IElementType EOL = new ElvishTokenType("EOL");
  IElementType EQUALS = new ElvishTokenType("=");
  IElementType ESCAPED_QUOTED_TEXT = new ElvishTokenType("ESCAPED_QUOTED_TEXT");
  IElementType INLINE_WHITESPACE = new ElvishTokenType("INLINE_WHITESPACE");
  IElementType INVALID_ESCAPED_QUOTED_TEXT = new ElvishTokenType("INVALID_ESCAPED_QUOTED_TEXT");
  IElementType KEYWORD_DEL = new ElvishTokenType("del");
  IElementType KEYWORD_ELIF = new ElvishTokenType("elif");
  IElementType KEYWORD_ELSE = new ElvishTokenType("else");
  IElementType KEYWORD_EXCEPT = new ElvishTokenType("except");
  IElementType KEYWORD_FINALLY = new ElvishTokenType("finally");
  IElementType KEYWORD_FN = new ElvishTokenType("fn");
  IElementType KEYWORD_FOR = new ElvishTokenType("for");
  IElementType KEYWORD_IF = new ElvishTokenType("if");
  IElementType KEYWORD_TRY = new ElvishTokenType("try");
  IElementType KEYWORD_USE = new ElvishTokenType("use");
  IElementType KEYWORD_WHILE = new ElvishTokenType("while");
  IElementType OPEN_BRACE = new ElvishTokenType("{");
  IElementType OPEN_BRACKET = new ElvishTokenType("[");
  IElementType OPEN_PARAN = new ElvishTokenType("(");
  IElementType PIPE = new ElvishTokenType("|");
  IElementType QUESTION = new ElvishTokenType("?");
  IElementType SEMICOLON = new ElvishTokenType(";");
  IElementType SINGLE_QUOTE = new ElvishTokenType("'");
  IElementType TEXT = new ElvishTokenType("TEXT");
  IElementType TILDE = new ElvishTokenType("~");
  IElementType VARIABLE_CHAR = new ElvishTokenType("VARIABLE_CHAR");
  IElementType WILDCARD = new ElvishTokenType("*");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ARGUMENT) {
        return new ElvishArgumentImpl(node);
      }
      else if (type == ARRAY) {
        return new ElvishArrayImpl(node);
      }
      else if (type == ASSIGNMENT) {
        return new ElvishAssignmentImpl(node);
      }
      else if (type == BAREWORD) {
        return new ElvishBarewordImpl(node);
      }
      else if (type == BRACED) {
        return new ElvishBracedImpl(node);
      }
      else if (type == BRACED_BAREWORD) {
        return new ElvishBracedBarewordImpl(node);
      }
      else if (type == BRACED_SEP) {
        return new ElvishBracedSepImpl(node);
      }
      else if (type == BUILTIN_NAMESPACE) {
        return new ElvishBuiltinNamespaceImpl(node);
      }
      else if (type == CHUNK) {
        return new ElvishChunkImpl(node);
      }
      else if (type == COLLECTION) {
        return new ElvishCollectionImpl(node);
      }
      else if (type == COMMAND_BAREWORD) {
        return new ElvishCommandBarewordImpl(node);
      }
      else if (type == COMMAND_EXPRESSION) {
        return new ElvishCommandExpressionImpl(node);
      }
      else if (type == COMPOUND) {
        return new ElvishCompoundImpl(node);
      }
      else if (type == CONDITION) {
        return new ElvishConditionImpl(node);
      }
      else if (type == DELETE_COMMAND) {
        return new ElvishDeleteCommandImpl(node);
      }
      else if (type == DOUBLE_QUOTED) {
        return new ElvishDoubleQuotedImpl(node);
      }
      else if (type == ELSE_BLOCK) {
        return new ElvishElseBlockImpl(node);
      }
      else if (type == EL_IF_BLOCK) {
        return new ElvishElIfBlockImpl(node);
      }
      else if (type == ENV_VAR_NAMESPACE) {
        return new ElvishEnvVarNamespaceImpl(node);
      }
      else if (type == EXCEPTION_CAPTURE) {
        return new ElvishExceptionCaptureImpl(node);
      }
      else if (type == EXCEPT_BLOCK) {
        return new ElvishExceptBlockImpl(node);
      }
      else if (type == EXITUS_REDIR) {
        return new ElvishExitusRedirImpl(node);
      }
      else if (type == EXTERNALS_NAMESPACE) {
        return new ElvishExternalsNamespaceImpl(node);
      }
      else if (type == FINALLY_BLOCK) {
        return new ElvishFinallyBlockImpl(node);
      }
      else if (type == FN_COMMAND) {
        return new ElvishFnCommandImpl(node);
      }
      else if (type == FOR_COMMAND) {
        return new ElvishForCommandImpl(node);
      }
      else if (type == HEAD) {
        return new ElvishHeadImpl(node);
      }
      else if (type == IF_COMMAND) {
        return new ElvishIfCommandImpl(node);
      }
      else if (type == INDEX_RANGE) {
        return new ElvishIndexRangeImpl(node);
      }
      else if (type == INDEX_SINGLE) {
        return new ElvishIndexSingleImpl(node);
      }
      else if (type == LAMBDA) {
        return new ElvishLambdaImpl(node);
      }
      else if (type == LAMBDA_ARGUMENTS) {
        return new ElvishLambdaArgumentsImpl(node);
      }
      else if (type == LAMBDA_BLOCK) {
        return new ElvishLambdaBlockImpl(node);
      }
      else if (type == LIB_MODULE_SPEC) {
        return new ElvishLibModuleSpecImpl(node);
      }
      else if (type == LIST) {
        return new ElvishListImpl(node);
      }
      else if (type == LOCAL_NAMESPACE) {
        return new ElvishLocalNamespaceImpl(node);
      }
      else if (type == LOCAL_SCOPE_VARIABLE_ASSIGNMENT) {
        return new ElvishLocalScopeVariableAssignmentImpl(node);
      }
      else if (type == LOGIC_COMMAND) {
        return new ElvishLogicCommandImpl(node);
      }
      else if (type == MAP) {
        return new ElvishMapImpl(node);
      }
      else if (type == MAP_PAIR) {
        return new ElvishMapPairImpl(node);
      }
      else if (type == MODULE_ALIAS) {
        return new ElvishModuleAliasImpl(node);
      }
      else if (type == NAMESPACE_COMMAND_EXPRESSION) {
        return new ElvishNamespaceCommandExpressionImpl(node);
      }
      else if (type == NAMESPACE_NAME) {
        return new ElvishNamespaceNameImpl(node);
      }
      else if (type == NAMESPACE_VARIABLE_ASSIGNMENT) {
        return new ElvishNamespaceVariableAssignmentImpl(node);
      }
      else if (type == NAMESPACE_VARIABLE_REF) {
        return new ElvishNamespaceVariableRefImpl(node);
      }
      else if (type == OUTPUT_CAPTURE) {
        return new ElvishOutputCaptureImpl(node);
      }
      else if (type == PARAMETER) {
        return new ElvishParameterImpl(node);
      }
      else if (type == PIPELINE_SEP) {
        return new ElvishPipelineSepImpl(node);
      }
      else if (type == REDIR) {
        return new ElvishRedirImpl(node);
      }
      else if (type == RELATIVE_MODULE_SPEC) {
        return new ElvishRelativeModuleSpecImpl(node);
      }
      else if (type == SINGLE_QUOTED) {
        return new ElvishSingleQuotedImpl(node);
      }
      else if (type == SPECIAL_SCOPE_COMMAND_EXPRESSION) {
        return new ElvishSpecialScopeCommandExpressionImpl(node);
      }
      else if (type == SPECIAL_SCOPE_VARIABLE_REF) {
        return new ElvishSpecialScopeVariableRefImpl(node);
      }
      else if (type == TRY_COMMAND) {
        return new ElvishTryCommandImpl(node);
      }
      else if (type == UP_NAMESPACE) {
        return new ElvishUpNamespaceImpl(node);
      }
      else if (type == UP_SCOPE_VARIABLE_ASSIGNMENT) {
        return new ElvishUpScopeVariableAssignmentImpl(node);
      }
      else if (type == USE_COMMAND) {
        return new ElvishUseCommandImpl(node);
      }
      else if (type == VARIABLE) {
        return new ElvishVariableImpl(node);
      }
      else if (type == VARIABLE_NAME) {
        return new ElvishVariableNameImpl(node);
      }
      else if (type == VARIABLE_REF) {
        return new ElvishVariableRefImpl(node);
      }
      else if (type == WHILE_COMMAND) {
        return new ElvishWhileCommandImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
