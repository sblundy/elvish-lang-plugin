// This is a generated file. Not intended for manual editing.
package com.github.sblundy.elvish.lang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.sblundy.elvish.psi.ElvishTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

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
  // bareword | single_quoted_string | double_quoted_string | variable_ref
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = consumeToken(builder_, BAREWORD);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = variable_ref(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // argument_list_line argument_list_continuation*
  public static boolean argument_list(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument_list")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT_LIST, "<argument list>");
    result_ = argument_list_line(builder_, level_ + 1);
    result_ = result_ && argument_list_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // argument_list_continuation*
  private static boolean argument_list_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument_list_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!argument_list_continuation(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "argument_list_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CONTINUATION argument*
  public static boolean argument_list_continuation(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument_list_continuation")) return false;
    if (!nextTokenIs(builder_, CONTINUATION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CONTINUATION);
    result_ = result_ && argument_list_continuation_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, ARGUMENT_LIST_CONTINUATION, result_);
    return result_;
  }

  // argument*
  private static boolean argument_list_continuation_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument_list_continuation_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!argument(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "argument_list_continuation_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // argument*
  public static boolean argument_list_line(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument_list_line")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT_LIST_LINE, "<argument list line>");
    while (true) {
      int pos_ = current_position_(builder_);
      if (!argument(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "argument_list_line", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, true, false, null);
    return true;
  }

  /* ********************************************************** */
  // assignment_left EQUALS assignment_right
  public static boolean assignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assignment_left(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && assignment_right(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASSIGNMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // variable_name(variable_index)?
  public static boolean assignment_left(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_left")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable_name(builder_, level_ + 1);
    result_ = result_ && assignment_left_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASSIGNMENT_LEFT, result_);
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
  // output_capture | single_quoted_string | double_quoted_string |bareword
  public static boolean assignment_right(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_right")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGNMENT_RIGHT, "<assignment right>");
    result_ = output_capture(builder_, level_ + 1);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = double_quoted_string(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, BAREWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACE line* CLOSE_BRACE
  public static boolean block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && block_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, BLOCK, result_);
    return result_;
  }

  // line*
  private static boolean block_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!line(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "block_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // variable_ref | output_capture
  public static boolean collection(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "collection")) return false;
    if (!nextTokenIs(builder_, "<collection>", OPEN_PARAN, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COLLECTION, "<collection>");
    result_ = variable_ref(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // delete_statement | if_statement | while_statement | for_statement | try_statement | assignment | pipeline | ordinary_command
  public static boolean command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND, "<command>");
    result_ = delete_statement(builder_, level_ + 1);
    if (!result_) result_ = if_statement(builder_, level_ + 1);
    if (!result_) result_ = while_statement(builder_, level_ + 1);
    if (!result_) result_ = for_statement(builder_, level_ + 1);
    if (!result_) result_ = try_statement(builder_, level_ + 1);
    if (!result_) result_ = assignment(builder_, level_ + 1);
    if (!result_) result_ = pipeline(builder_, level_ + 1);
    if (!result_) result_ = ordinary_command(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_PARAN ordinary_command CLOSE_PARAN
  static boolean command_outpub_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_outpub_body")) return false;
    if (!nextTokenIs(builder_, OPEN_PARAN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_PARAN);
    result_ = result_ && ordinary_command(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PARAN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // variable_ref | output_capture
  public static boolean condition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "condition")) return false;
    if (!nextTokenIs(builder_, "<condition>", OPEN_PARAN, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONDITION, "<condition>");
    result_ = variable_ref(builder_, level_ + 1);
    if (!result_) result_ = output_capture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_DEL variable_name(variable_index)?
  public static boolean delete_statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "delete_statement")) return false;
    if (!nextTokenIs(builder_, KEYWORD_DEL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_DEL);
    result_ = result_ && variable_name(builder_, level_ + 1);
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
    if (!nextTokenIs(builder_, "<head>", BAREWORD, BUILTIN_OPERATOR_FN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, HEAD, "<head>");
    result_ = consumeToken(builder_, BAREWORD);
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
  // (command COMMENT line_terminator) | (command line_terminator) | (COMMENT line_terminator) | line_terminator | command | COMMENT
  public static boolean line(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "line")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LINE, "<line>");
    result_ = line_0(builder_, level_ + 1);
    if (!result_) result_ = line_1(builder_, level_ + 1);
    if (!result_) result_ = line_2(builder_, level_ + 1);
    if (!result_) result_ = line_terminator(builder_, level_ + 1);
    if (!result_) result_ = command(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, COMMENT);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // command COMMENT line_terminator
  private static boolean line_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "line_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = command(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMENT);
    result_ = result_ && line_terminator(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // command line_terminator
  private static boolean line_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "line_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = command(builder_, level_ + 1);
    result_ = result_ && line_terminator(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMENT line_terminator
  private static boolean line_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "line_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMENT);
    result_ = result_ && line_terminator(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EOL+
  public static boolean line_terminator(PsiBuilder builder_, int level_) {
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
    exit_section_(builder_, marker_, LINE_TERMINATOR, result_);
    return result_;
  }

  /* ********************************************************** */
  // head argument_list
  public static boolean ordinary_command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ordinary_command")) return false;
    if (!nextTokenIs(builder_, "<ordinary command>", BAREWORD, BUILTIN_OPERATOR_FN)) return false;
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
    if (!nextTokenIs(builder_, OPEN_PARAN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = pipeline_output_body(builder_, level_ + 1);
    if (!result_) result_ = command_outpub_body(builder_, level_ + 1);
    exit_section_(builder_, marker_, OUTPUT_CAPTURE, result_);
    return result_;
  }

  /* ********************************************************** */
  // pipeline_prv
  public static boolean pipeline(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline")) return false;
    if (!nextTokenIs(builder_, "<pipeline>", BAREWORD, BUILTIN_OPERATOR_FN)) return false;
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
    if (!nextTokenIs(builder_, "", BAREWORD, BUILTIN_OPERATOR_FN)) return false;
    boolean result_;
    result_ = pipeline_prv(builder_, level_ + 1);
    if (!result_) result_ = ordinary_command(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_PARAN pipeline CLOSE_PARAN
  static boolean pipeline_output_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_output_body")) return false;
    if (!nextTokenIs(builder_, OPEN_PARAN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_PARAN);
    result_ = result_ && pipeline(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PARAN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // ordinary_command PIPE pipeline_head
  static boolean pipeline_prv(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_prv")) return false;
    if (!nextTokenIs(builder_, "", BAREWORD, BUILTIN_OPERATOR_FN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = ordinary_command(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, PIPE);
    result_ = result_ && pipeline_head(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // line* <<eof>>
  static boolean script(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "script")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = script_0(builder_, level_ + 1);
    result_ = result_ && eof(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // line*
  private static boolean script_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "script_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!line(builder_, level_ + 1)) break;
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
  // TEXT_CHR*
  public static boolean string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string")) return false;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRING, "<string>");
    while (true) {
      int pos_ = current_position_(builder_);
      if (!TEXT_CHR(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "string", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, true, false, null);
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
  // variable_name
  public static boolean variable_declaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_declaration")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable_name(builder_, level_ + 1);
    exit_section_(builder_, marker_, VARIABLE_DECLARATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // (OPEN_BRACKET)bareword(CLOSE_BRACKET)
  public static boolean variable_index(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_index")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && consumeToken(builder_, BAREWORD);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, VARIABLE_INDEX, result_);
    return result_;
  }

  /* ********************************************************** */
  // bareword
  public static boolean variable_name(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_name")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BAREWORD);
    exit_section_(builder_, marker_, VARIABLE_NAME, result_);
    return result_;
  }

  /* ********************************************************** */
  // (REF_MARKER)variable_name(variable_index)?
  public static boolean variable_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref")) return false;
    if (!nextTokenIs(builder_, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REF_MARKER);
    result_ = result_ && variable_name(builder_, level_ + 1);
    result_ = result_ && variable_ref_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, VARIABLE_REF, result_);
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

}
