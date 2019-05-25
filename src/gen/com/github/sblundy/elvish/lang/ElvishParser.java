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
  // TEXT | escaped_sequence
  static boolean TEXT_CHR(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TEXT_CHR")) return false;
    if (!nextTokenIs(builder_, "", ESCAPED_SINGLE_QUOTED_TEXT, TEXT)) return false;
    boolean result_;
    result_ = consumeToken(builder_, TEXT);
    if (!result_) result_ = escaped_sequence(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // bareword | single_quoted_string | variable_ref
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = consumeToken(builder_, BAREWORD);
    if (!result_) result_ = single_quoted_string(builder_, level_ + 1);
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
  // assignment_start single_quoted_string
  public static boolean assignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = assignment_start(builder_, level_ + 1);
    result_ = result_ && single_quoted_string(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASSIGNMENT, result_);
    return result_;
  }

  /* ********************************************************** */
  // variable EQUALS
  public static boolean assignment_start(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignment_start")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = variable(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    exit_section_(builder_, marker_, ASSIGNMENT_START, result_);
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
  // if_statement | assignment | pipeline | ordinary_command
  public static boolean command(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND, "<command>");
    result_ = if_statement(builder_, level_ + 1);
    if (!result_) result_ = assignment(builder_, level_ + 1);
    if (!result_) result_ = pipeline(builder_, level_ + 1);
    if (!result_) result_ = ordinary_command(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_PARAN ordinary_command CLOSE_PARAN
  static boolean command_condition_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "command_condition_body")) return false;
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
  // variable_ref | pipeline_condition_body | command_condition_body
  public static boolean condition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "condition")) return false;
    if (!nextTokenIs(builder_, "<condition>", OPEN_PARAN, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONDITION, "<condition>");
    result_ = variable_ref(builder_, level_ + 1);
    if (!result_) result_ = pipeline_condition_body(builder_, level_ + 1);
    if (!result_) result_ = command_condition_body(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
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
  // ESCAPED_SINGLE_QUOTED_TEXT
  public static boolean escaped_sequence(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "escaped_sequence")) return false;
    if (!nextTokenIs(builder_, ESCAPED_SINGLE_QUOTED_TEXT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ESCAPED_SINGLE_QUOTED_TEXT);
    exit_section_(builder_, marker_, ESCAPED_SEQUENCE, result_);
    return result_;
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
  // OPEN_PARAN pipeline CLOSE_PARAN
  static boolean pipeline_condition_body(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pipeline_condition_body")) return false;
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
  // bareword
  public static boolean variable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable")) return false;
    if (!nextTokenIs(builder_, BAREWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, BAREWORD);
    exit_section_(builder_, marker_, VARIABLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // REF_MARKER(bareword)
  public static boolean variable_ref(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variable_ref")) return false;
    if (!nextTokenIs(builder_, REF_MARKER)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, REF_MARKER);
    result_ = result_ && consumeToken(builder_, BAREWORD);
    exit_section_(builder_, marker_, VARIABLE_REF, result_);
    return result_;
  }

}
