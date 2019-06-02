// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import static com.github.sblundy.elvish.lang.ElvishParserUtils.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ElvishParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, null);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    if (root_ instanceof IFileElementType) {
      result_ = parse_root_(root_, builder_, 0);
    }
    else {
      result_ = false;
    }
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return script(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // TEXT | invalid_escape_sequence | escaped_sequence
  static boolean TEXT_CHR(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TEXT_CHR")) return false;
    boolean result_;
    result_ = consumeToken(builder_, TEXT);
    if (!result_) result_ = invalid_escape_sequence(builder_, level_ + 1);
    if (!result_) result_ = escaped_sequence(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACKET parameter* AT_VARIABLE? CLOSE_BRACKET(no_arg_lambda)
  static boolean arg_lambda(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_lambda")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && arg_lambda_1(builder_, level_ + 1);
    result_ = result_ && arg_lambda_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    result_ = result_ && arg_lambda_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // parameter*
  private static boolean arg_lambda_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_lambda_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!parameter(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "arg_lambda_1", pos_)) break;
    }
    return true;
  }

  // AT_VARIABLE?
  private static boolean arg_lambda_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_lambda_2")) return false;
    consumeToken(builder_, AT_VARIABLE);
    return true;
  }

  // (no_arg_lambda)
  private static boolean arg_lambda_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arg_lambda_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = no_arg_lambda(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // assignment_right
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = assignment_right(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // option_value | argument
  static boolean argument_inner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument_inner")) return false;
    boolean result_;
    result_ = option_value(builder_, level_ + 1);
    if (!result_) result_ = argument(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // <<parseArgList argument_inner line_terminator>>
  static boolean argument_list(PsiBuilder builder_, int level_) {
    return parseArgList(builder_, level_ + 1, argument_inner_parser_, line_terminator_parser_);
  }

  /* ********************************************************** */
  // compound_expression | variable_ref | single_quoted_string | double_quoted_string | bareword
  static boolean assignable_string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignable_string")) return false;
    boolean result_;
    result_ = compound_expression(builder_, level_ + 1);
    if (!result_) result_ = variable_ref(builder_, level_ + 1);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = bareword(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // assignment_left EQUALS assignment_right
  public static boolean assignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment")) return false;
    if (!nextTokenIs(builder_, VARIABLE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assignment_left(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && assignment_right(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASSIGNMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // VARIABLE(variable_index)?
  static boolean assignment_left(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_left")) return false;
    if (!nextTokenIs(builder_, VARIABLE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE);
    result_ = result_ && assignment_left_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (variable_index)?
  private static boolean assignment_left_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_left_1")) return false;
    assignment_left_1_0(builder_, level_ + 1);
    return true;
  }

  // (variable_index)
  private static boolean assignment_left_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_left_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable_index(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // output_capture | lambda | map_or_list | empty_map | assignable_string
  static boolean assignment_right(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_right")) return false;
    boolean result_;
    result_ = output_capture(builder_, level_ + 1);
    if (!result_) result_ = lambda(builder_, level_ + 1);
    if (!result_) result_ = map_or_list(builder_, level_ + 1);
    if (!result_) result_ = empty_map(builder_, level_ + 1);
    if (!result_) result_ = assignable_string(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // AT_VARIABLE | VARIABLE | BAREWORD
  static boolean bareword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "bareword")) return false;
    boolean result_;
    result_ = consumeToken(builder_, AT_VARIABLE);
    if (!result_) result_ = consumeToken(builder_, VARIABLE);
    if (!result_) result_ = consumeToken(builder_, BAREWORD);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACE block_body CLOSE_BRACE
  public static boolean block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && block_body(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // <<parseBlockBody command>>
  static boolean block_body(PsiBuilder builder_, int level_) {
    return parseBlockBody(builder_, level_ + 1, command_parser_);
  }

  /* ********************************************************** */
  // variable_ref | output_capture
  public static boolean collection(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "collection")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COLLECTION, "<collection>");
    result_ = variable_ref(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // special_command | assignment | pipeline | ordinary_command
  static boolean command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command")) return false;
    boolean result_;
    result_ = special_command(builder_, level_ + 1);
    if (!result_) result_ = assignment(builder_, level_ + 1);
    if (!result_) result_ = pipeline(builder_, level_ + 1);
    if (!result_) result_ = ordinary_command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // QUESTION?OPEN_PARAN ordinary_command CLOSE_PARAN
  static boolean command_outpub_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_outpub_body")) return false;
    if (!nextTokenIs(builder_, "", OPEN_PARAN, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = command_outpub_body_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_PARAN);
    result_ = result_ && ordinary_command(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PARAN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // QUESTION?
  private static boolean command_outpub_body_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_outpub_body_0")) return false;
    consumeToken(builder_, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // compound_expression_capture | compound_expression_var | compound_expression_single_quoted | compound_expression_double_quoted
  public static boolean compound_expression(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPOUND_EXPRESSION, "<compound expression>");
    result_ = compound_expression_capture(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_var(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_single_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_double_quoted(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // output_capture(compound_expression_capture | compound_expression_single_quoted | compound_expression_double_quoted | compound_expression_var | output_capture | single_quoted_string | double_quoted_string | variable_ref)
  static boolean compound_expression_capture(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_capture")) return false;
    if (!nextTokenIs(builder_, "", OPEN_PARAN, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = output_capture(builder_, level_ + 1);
    result_ = result_ && compound_expression_capture_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // compound_expression_capture | compound_expression_single_quoted | compound_expression_double_quoted | compound_expression_var | output_capture | single_quoted_string | double_quoted_string | variable_ref
  private static boolean compound_expression_capture_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_capture_1")) return false;
    boolean result_;
    result_ = compound_expression_capture(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_single_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_double_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_var(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = variable_ref(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // double_quoted_string(compound_expression_capture | compound_expression_single_quoted | compound_expression_double_quoted | compound_expression_var | output_capture | single_quoted_string | double_quoted_string | variable_ref)
  static boolean compound_expression_double_quoted(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_double_quoted")) return false;
    if (!nextTokenIs(builder_, DOUBLE_QUOTE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = double_quoted_string(builder_, level_ + 1);
    result_ = result_ && compound_expression_double_quoted_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // compound_expression_capture | compound_expression_single_quoted | compound_expression_double_quoted | compound_expression_var | output_capture | single_quoted_string | double_quoted_string | variable_ref
  private static boolean compound_expression_double_quoted_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_double_quoted_1")) return false;
    boolean result_;
    result_ = compound_expression_capture(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_single_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_double_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_var(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = variable_ref(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // single_quoted_string(compound_expression_capture | compound_expression_double_quoted | compound_expression_var | output_capture | double_quoted_string | variable_ref)
  static boolean compound_expression_single_quoted(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_single_quoted")) return false;
    if (!nextTokenIs(builder_, SINGLE_QUOTE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = single_quoted_string(builder_, level_ + 1);
    result_ = result_ && compound_expression_single_quoted_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // compound_expression_capture | compound_expression_double_quoted | compound_expression_var | output_capture | double_quoted_string | variable_ref
  private static boolean compound_expression_single_quoted_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_single_quoted_1")) return false;
    boolean result_;
    result_ = compound_expression_capture(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_double_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_var(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = variable_ref(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // variable_ref(compound_expression_capture | compound_expression_single_quoted | compound_expression_double_quoted | compound_expression_var | output_capture | single_quoted_string | double_quoted_string | variable_ref)
  static boolean compound_expression_var(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_var")) return false;
    if (!nextTokenIs(builder_, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable_ref(builder_, level_ + 1);
    result_ = result_ && compound_expression_var_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // compound_expression_capture | compound_expression_single_quoted | compound_expression_double_quoted | compound_expression_var | output_capture | single_quoted_string | double_quoted_string | variable_ref
  private static boolean compound_expression_var_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compound_expression_var_1")) return false;
    boolean result_;
    result_ = compound_expression_capture(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_single_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_double_quoted(builder_, level_ + 1);
    if (!result_) result_ = compound_expression_var(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = variable_ref(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // variable_ref | output_capture
  public static boolean condition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "condition")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONDITION, "<condition>");
    result_ = variable_ref(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_DEL VARIABLE(variable_index)?
  public static boolean delete_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "delete_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_DEL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, KEYWORD_DEL, VARIABLE);
    result_ = result_ && delete_statement_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, DELETE_STATEMENT, result_);
    return result_;
  }

  // (variable_index)?
  private static boolean delete_statement_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "delete_statement_2")) return false;
    delete_statement_2_0(builder_, level_ + 1);
    return true;
  }

  // (variable_index)
  private static boolean delete_statement_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "delete_statement_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable_index(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DOUBLE_QUOTE string DOUBLE_QUOTE
  public static boolean double_quoted_string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "double_quoted_string")) return false;
    if (!nextTokenIs(builder_, DOUBLE_QUOTE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_QUOTE);
    result_ = result_ && string(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DOUBLE_QUOTE);
    exit_section_(builder_, marker_, DOUBLE_QUOTED_STRING, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_ELIF condition block
  public static boolean elif_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "elif_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_ELIF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_ELIF);
    result_ = result_ && condition(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, ELIF_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_ELSE block
  public static boolean else_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "else_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_ELSE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_ELSE);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, ELSE_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACKET(AMPERSAND)CLOSE_BRACKET
  public static boolean empty_map(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "empty_map")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && consumeToken(builder_, AMPERSAND);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, EMPTY_MAP, result_);
    return result_;
  }

  /* ********************************************************** */
  // ESCAPED_QUOTED_TEXT
  public static boolean escaped_sequence(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "escaped_sequence")) return false;
    if (!nextTokenIs(builder_, ESCAPED_QUOTED_TEXT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ESCAPED_QUOTED_TEXT);
    exit_section_(builder_, marker_, ESCAPED_SEQUENCE, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_EXCEPT variable_declaration block
  public static boolean except_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "except_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_EXCEPT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_EXCEPT);
    result_ = result_ && variable_declaration(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXCEPT_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_FINALLY block
  public static boolean finally_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "finally_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FINALLY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FINALLY);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, FINALLY_STATEMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_FN VARIABLE (no_arg_lambda | arg_lambda)
  public static boolean fn_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fn_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, KEYWORD_FN, VARIABLE);
    result_ = result_ && fn_statement_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, FN_STATEMENT, result_);
    return result_;
  }

  // no_arg_lambda | arg_lambda
  private static boolean fn_statement_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fn_statement_2")) return false;
    boolean result_;
    result_ = no_arg_lambda(builder_, level_ + 1);
    if (!result_) result_ = arg_lambda(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_FOR variable_declaration collection block else_statement?
  public static boolean for_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FOR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FOR);
    result_ = result_ && variable_declaration(builder_, level_ + 1);
    result_ = result_ && collection(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && for_statement_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_STATEMENT, result_);
    return result_;
  }

  // else_statement?
  private static boolean for_statement_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "for_statement_4")) return false;
    else_statement(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // bareword | BUILTIN_OPERATOR_FN
  public static boolean head(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "head")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, HEAD, "<head>");
    result_ = bareword(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BUILTIN_OPERATOR_FN);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_IF condition block elif_statement* else_statement?
  public static boolean if_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_IF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_IF);
    result_ = result_ && condition(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && if_statement_3(builder_, level_ + 1);
    result_ = result_ && if_statement_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_STATEMENT, result_);
    return result_;
  }

  // elif_statement*
  private static boolean if_statement_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_statement_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!elif_statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "if_statement_3", pos_)) break;
    }
    return true;
  }

  // else_statement?
  private static boolean if_statement_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "if_statement_4")) return false;
    else_statement(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // INVALID_ESCAPED_QUOTED_TEXT
  public static boolean invalid_escape_sequence(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "invalid_escape_sequence")) return false;
    if (!nextTokenIs(builder_, INVALID_ESCAPED_QUOTED_TEXT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, INVALID_ESCAPED_QUOTED_TEXT);
    exit_section_(builder_, marker_, INVALID_ESCAPE_SEQUENCE, result_);
    return result_;
  }

  /* ********************************************************** */
  // assignment_right | map_entry
  static boolean item(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "item")) return false;
    boolean result_;
    result_ = assignment_right(builder_, level_ + 1);
    if (!result_) result_ = map_entry(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // arg_lambda | no_arg_lambda
  public static boolean lambda(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "lambda")) return false;
    if (!nextTokenIs(builder_, "<lambda>", OPEN_BRACE, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA, "<lambda>");
    result_ = arg_lambda(builder_, level_ + 1);
    if (!result_) result_ = no_arg_lambda(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // EOL+
  static boolean line_terminator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "line_terminator")) return false;
    if (!nextTokenIs(builder_, EOL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EOL);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, EOL)) break;
      if (!empty_element_parsed_guard_(builder_, "line_terminator", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AMPERSAND(bareword|single_quoted_string|double_quoted_string)EQUALS(assignment_right)?
  public static boolean map_entry(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry")) return false;
    if (!nextTokenIs(builder_, AMPERSAND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AMPERSAND);
    result_ = result_ && map_entry_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && map_entry_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, MAP_ENTRY, result_);
    return result_;
  }

  // bareword|single_quoted_string|double_quoted_string
  private static boolean map_entry_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry_1")) return false;
    boolean result_;
    result_ = bareword(builder_, level_ + 1);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    return result_;
  }

  // (assignment_right)?
  private static boolean map_entry_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry_3")) return false;
    map_entry_3_0(builder_, level_ + 1);
    return true;
  }

  // (assignment_right)
  private static boolean map_entry_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_entry_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assignment_right(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACKET item* CLOSE_BRACKET
  public static boolean map_or_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_or_list")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && map_or_list_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, MAP_OR_LIST, result_);
    return result_;
  }

  // item*
  private static boolean map_or_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "map_or_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!item(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "map_or_list_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // OPEN_BRACE block_body CLOSE_BRACE
  static boolean no_arg_lambda(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "no_arg_lambda")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && block_body(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AMPERSAND(bareword)EQUALS(bareword)
  static boolean option(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "option")) return false;
    if (!nextTokenIs(builder_, AMPERSAND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AMPERSAND);
    result_ = result_ && option_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && option_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (bareword)
  private static boolean option_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "option_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = bareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (bareword)
  private static boolean option_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "option_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = bareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AMPERSAND(VARIABLE)EQUALS(assignment_right)
  public static boolean option_value(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "option_value")) return false;
    if (!nextTokenIs(builder_, AMPERSAND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AMPERSAND);
    result_ = result_ && consumeToken(builder_, VARIABLE);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && option_value_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, OPTION_VALUE, result_);
    return result_;
  }

  // (assignment_right)
  private static boolean option_value_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "option_value_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assignment_right(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // head argument_list
  public static boolean ordinary_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ordinary_command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ORDINARY_COMMAND, "<ordinary command>");
    result_ = head(builder_, level_ + 1);
    result_ = result_ && argument_list(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // pipeline_output_body | command_outpub_body
  public static boolean output_capture(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "output_capture")) return false;
    if (!nextTokenIs(builder_, "<output capture>", OPEN_PARAN, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, OUTPUT_CAPTURE, "<output capture>");
    result_ = pipeline_output_body(builder_, level_ + 1);
    if (!result_) result_ = command_outpub_body(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VARIABLE | AT_VARIABLE | option
  public static boolean parameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARAMETER, "<parameter>");
    result_ = consumeToken(builder_, VARIABLE);
    if (!result_) result_ = consumeToken(builder_, AT_VARIABLE);
    if (!result_) result_ = option(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // pipeline_prv
  public static boolean pipeline(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PIPELINE, "<pipeline>");
    result_ = pipeline_prv(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // pipeline_prv | ordinary_command
  static boolean pipeline_head(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_head")) return false;
    boolean result_;
    result_ = pipeline_prv(builder_, level_ + 1);
    if (!result_) result_ = ordinary_command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // QUESTION?OPEN_PARAN pipeline CLOSE_PARAN
  static boolean pipeline_output_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_output_body")) return false;
    if (!nextTokenIs(builder_, "", OPEN_PARAN, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = pipeline_output_body_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_PARAN);
    result_ = result_ && pipeline(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PARAN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // QUESTION?
  private static boolean pipeline_output_body_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_output_body_0")) return false;
    consumeToken(builder_, QUESTION);
    return true;
  }

  /* ********************************************************** */
  // ordinary_command PIPE pipeline_head
  static boolean pipeline_prv(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_prv")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ordinary_command(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PIPE);
    result_ = result_ && pipeline_head(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // command* <<eof>>
  static boolean script(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "script")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = script_0(builder_, level_ + 1);
    result_ = result_ && eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // command*
  private static boolean script_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "script_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!command(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "script_0", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // SINGLE_QUOTE string SINGLE_QUOTE
  public static boolean single_quoted_string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "single_quoted_string")) return false;
    if (!nextTokenIs(builder_, SINGLE_QUOTE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SINGLE_QUOTE);
    result_ = result_ && string(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SINGLE_QUOTE);
    exit_section_(builder_, marker_, SINGLE_QUOTED_STRING, result_);
    return result_;
  }

  /* ********************************************************** */
  // fn_statement | delete_statement | if_statement | while_statement | for_statement | try_statement
  static boolean special_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "special_command")) return false;
    boolean result_;
    result_ = fn_statement(builder_, level_ + 1);
    if (!result_) result_ = delete_statement(builder_, level_ + 1);
    if (!result_) result_ = if_statement(builder_, level_ + 1);
    if (!result_) result_ = while_statement(builder_, level_ + 1);
    if (!result_) result_ = for_statement(builder_, level_ + 1);
    if (!result_) result_ = try_statement(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // TEXT_CHR*
  static boolean string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TEXT_CHR(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "string", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_TRY block except_statement? else_statement? finally_statement?
  public static boolean try_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_TRY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_TRY);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && try_statement_2(builder_, level_ + 1);
    result_ = result_ && try_statement_3(builder_, level_ + 1);
    result_ = result_ && try_statement_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRY_STATEMENT, result_);
    return result_;
  }

  // except_statement?
  private static boolean try_statement_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_statement_2")) return false;
    except_statement(builder_, level_ + 1);
    return true;
  }

  // else_statement?
  private static boolean try_statement_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_statement_3")) return false;
    else_statement(builder_, level_ + 1);
    return true;
  }

  // finally_statement?
  private static boolean try_statement_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "try_statement_4")) return false;
    finally_statement(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // VARIABLE
  public static boolean variable_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_declaration")) return false;
    if (!nextTokenIs(builder_, VARIABLE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE);
    exit_section_(builder_, marker_, VARIABLE_DECLARATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // (OPEN_BRACKET)bareword(CLOSE_BRACKET)
  static boolean variable_index(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_index")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && bareword(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (REF_MARKER)(AT_VARIABLE|VARIABLE)(variable_index)?
  public static boolean variable_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref")) return false;
    if (!nextTokenIs(builder_, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REF_MARKER);
    result_ = result_ && variable_ref_1(builder_, level_ + 1);
    result_ = result_ && variable_ref_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, VARIABLE_REF, result_);
    return result_;
  }

  // AT_VARIABLE|VARIABLE
  private static boolean variable_ref_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref_1")) return false;
    boolean result_;
    result_ = consumeToken(builder_, AT_VARIABLE);
    if (!result_) result_ = consumeToken(builder_, VARIABLE);
    return result_;
  }

  // (variable_index)?
  private static boolean variable_ref_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref_2")) return false;
    variable_ref_2_0(builder_, level_ + 1);
    return true;
  }

  // (variable_index)
  private static boolean variable_ref_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable_index(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_WHILE condition block else_statement?
  public static boolean while_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "while_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_WHILE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_WHILE);
    result_ = result_ && condition(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && while_statement_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, WHILE_STATEMENT, result_);
    return result_;
  }

  // else_statement?
  private static boolean while_statement_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "while_statement_3")) return false;
    else_statement(builder_, level_ + 1);
    return true;
  }

  static final Parser argument_inner_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return argument_inner(builder_, level_ + 1);
    }
  };
  static final Parser command_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return command(builder_, level_ + 1);
    }
  };
  static final Parser line_terminator_parser_ = new Parser() {
    public boolean parse(PsiBuilder builder_, int level_) {
      return line_terminator(builder_, level_ + 1);
    }
  };
}
