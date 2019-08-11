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
  // ArraySpace* (Compound ArraySpace*)*
  public static boolean Array(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Array")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARRAY, "<array>");
    result_ = Array_0(builder_, level_ + 1);
    result_ = result_ && Array_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ArraySpace*
  private static boolean Array_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Array_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ArraySpace(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Array_0", pos_)) break;
    }
    return true;
  }

  // (Compound ArraySpace*)*
  private static boolean Array_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Array_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Array_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Array_1", pos_)) break;
    }
    return true;
  }

  // Compound ArraySpace*
  private static boolean Array_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Array_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Compound(builder_, level_ + 1);
    result_ = result_ && Array_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ArraySpace*
  private static boolean Array_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Array_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ArraySpace(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Array_1_0_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Space | EOL
  static boolean ArraySpace(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ArraySpace")) return false;
    if (!nextTokenIs(builder_, "", EOL, INLINE_WHITESPACE)) return false;
    boolean result_;
    result_ = Space(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, EOL);
    return result_;
  }

  /* ********************************************************** */
  // (Variable Index* Space?)+ EQUALS Space? Compound
  public static boolean Assignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment")) return false;
    if (!nextTokenIs(builder_, VARIABLE_CHAR)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGNMENT, null);
    result_ = Assignment_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    pinned_ = result_; // pin = 2
    result_ = result_ && report_error_(builder_, Assignment_2(builder_, level_ + 1));
    result_ = pinned_ && Compound(builder_, level_ + 1) && result_;
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // (Variable Index* Space?)+
  private static boolean Assignment_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Assignment_0_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!Assignment_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Assignment_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Variable Index* Space?
  private static boolean Assignment_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Variable(builder_, level_ + 1);
    result_ = result_ && Assignment_0_0_1(builder_, level_ + 1);
    result_ = result_ && Assignment_0_0_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Index*
  private static boolean Assignment_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment_0_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Index(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Assignment_0_0_1", pos_)) break;
    }
    return true;
  }

  // Space?
  private static boolean Assignment_0_0_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment_0_0_2")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // Space?
  private static boolean Assignment_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment_2")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // Space? Assignment Space?
  static boolean AssignmentOnlyForm(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssignmentOnlyForm")) return false;
    if (!nextTokenIs(builder_, "", INLINE_WHITESPACE, VARIABLE_CHAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AssignmentOnlyForm_0(builder_, level_ + 1);
    result_ = result_ && Assignment(builder_, level_ + 1);
    result_ = result_ && AssignmentOnlyForm_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Space?
  private static boolean AssignmentOnlyForm_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssignmentOnlyForm_0")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // Space?
  private static boolean AssignmentOnlyForm_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "AssignmentOnlyForm_2")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // (VARIABLE_CHAR|BAREWORD_CHAR|<<parseKeywordAsBareword>>)+
  public static boolean Bareword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Bareword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BAREWORD, "<bareword>");
    result_ = Bareword_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!Bareword_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Bareword", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // VARIABLE_CHAR|BAREWORD_CHAR|<<parseKeywordAsBareword>>
  private static boolean Bareword_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Bareword_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    if (!result_) result_ = consumeToken(builder_, BAREWORD_CHAR);
    if (!result_) result_ = parseKeywordAsBareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACE (Space | EOL)* Compound (BracedSep* Compound)* (Space | EOL)* CLOSE_BRACE
  public static boolean Braced(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Braced_1(builder_, level_ + 1);
    result_ = result_ && Compound(builder_, level_ + 1);
    result_ = result_ && Braced_3(builder_, level_ + 1);
    result_ = result_ && Braced_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, BRACED, result_);
    return result_;
  }

  // (Space | EOL)*
  private static boolean Braced_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Braced_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_1", pos_)) break;
    }
    return true;
  }

  // Space | EOL
  private static boolean Braced_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_1_0")) return false;
    boolean result_;
    result_ = Space(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, EOL);
    return result_;
  }

  // (BracedSep* Compound)*
  private static boolean Braced_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Braced_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_3", pos_)) break;
    }
    return true;
  }

  // BracedSep* Compound
  private static boolean Braced_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Braced_3_0_0(builder_, level_ + 1);
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // BracedSep*
  private static boolean Braced_3_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_3_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!BracedSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_3_0_0", pos_)) break;
    }
    return true;
  }

  // (Space | EOL)*
  private static boolean Braced_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Braced_4_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_4", pos_)) break;
    }
    return true;
  }

  // Space | EOL
  private static boolean Braced_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_4_0")) return false;
    boolean result_;
    result_ = Space(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, EOL);
    return result_;
  }

  /* ********************************************************** */
  // (Space | EOL)* ',' (Space | EOL)*
  public static boolean BracedSep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BRACED_SEP, "<braced sep>");
    result_ = BracedSep_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ",");
    result_ = result_ && BracedSep_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (Space | EOL)*
  private static boolean BracedSep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!BracedSep_0_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "BracedSep_0", pos_)) break;
    }
    return true;
  }

  // Space | EOL
  private static boolean BracedSep_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep_0_0")) return false;
    boolean result_;
    result_ = Space(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, EOL);
    return result_;
  }

  // (Space | EOL)*
  private static boolean BracedSep_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!BracedSep_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "BracedSep_2", pos_)) break;
    }
    return true;
  }

  // Space | EOL
  private static boolean BracedSep_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep_2_0")) return false;
    boolean result_;
    result_ = Space(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, EOL);
    return result_;
  }

  /* ********************************************************** */
  // PipelineSep | Space
  static boolean ChuckSep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ChuckSep")) return false;
    boolean result_;
    result_ = PipelineSep(builder_, level_ + 1);
    if (!result_) result_ = Space(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // ChuckSep* ( Pipeline ChuckSep* )*
  public static boolean Chunk(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Chunk")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CHUNK, "<chunk>");
    result_ = Chunk_0(builder_, level_ + 1);
    result_ = result_ && Chunk_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ChuckSep*
  private static boolean Chunk_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Chunk_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ChuckSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Chunk_0", pos_)) break;
    }
    return true;
  }

  // ( Pipeline ChuckSep* )*
  private static boolean Chunk_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Chunk_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Chunk_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Chunk_1", pos_)) break;
    }
    return true;
  }

  // Pipeline ChuckSep*
  private static boolean Chunk_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Chunk_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Pipeline(builder_, level_ + 1);
    result_ = result_ && Chunk_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ChuckSep*
  private static boolean Chunk_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Chunk_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ChuckSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Chunk_1_0_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // CommandBareword
  static boolean CmdExpr(PsiBuilder builder_, int level_) {
    return CommandBareword(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // List | Map | VariableRef | ExceptionCapture | OutputCapture
  public static boolean Collection(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Collection")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COLLECTION, "<collection>");
    result_ = List(builder_, level_ + 1);
    if (!result_) result_ = Map(builder_, level_ + 1);
    if (!result_) result_ = VariableRef(builder_, level_ + 1);
    if (!result_) result_ = ExceptionCapture(builder_, level_ + 1);
    if (!result_) result_ = OutputCapture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (VARIABLE_CHAR|BAREWORD_CHAR|COMMAND_BAREWORD_CHAR|WILDCARD|EQUALS)+
  public static boolean CommandBareword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandBareword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND_BAREWORD, "<command bareword>");
    result_ = CommandBareword_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!CommandBareword_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CommandBareword", pos_)) break;
    }
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // VARIABLE_CHAR|BAREWORD_CHAR|COMMAND_BAREWORD_CHAR|WILDCARD|EQUALS
  private static boolean CommandBareword_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandBareword_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    if (!result_) result_ = consumeToken(builder_, BAREWORD_CHAR);
    if (!result_) result_ = consumeToken(builder_, COMMAND_BAREWORD_CHAR);
    if (!result_) result_ = consumeToken(builder_, WILDCARD);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    return result_;
  }

  /* ********************************************************** */
  // head (CommandSep+ argument)* AMPERSAND?
  static boolean CommandForm(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = head(builder_, level_ + 1);
    result_ = result_ && CommandForm_1(builder_, level_ + 1);
    result_ = result_ && CommandForm_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (CommandSep+ argument)*
  private static boolean CommandForm_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!CommandForm_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CommandForm_1", pos_)) break;
    }
    return true;
  }

  // CommandSep+ argument
  private static boolean CommandForm_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = CommandForm_1_0_0(builder_, level_ + 1);
    result_ = result_ && argument(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CommandSep+
  private static boolean CommandForm_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = CommandSep(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!CommandSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CommandForm_1_0_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AMPERSAND?
  private static boolean CommandForm_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_2")) return false;
    consumeToken(builder_, AMPERSAND);
    return true;
  }

  /* ********************************************************** */
  // Space | CONTINUATION
  static boolean CommandSep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandSep")) return false;
    if (!nextTokenIs(builder_, "", CONTINUATION, INLINE_WHITESPACE)) return false;
    boolean result_;
    result_ = Space(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, CONTINUATION);
    return result_;
  }

  /* ********************************************************** */
  // TILDE? Indexing+
  public static boolean Compound(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Compound")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPOUND, "<compound>");
    result_ = Compound_0(builder_, level_ + 1);
    result_ = result_ && Compound_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // TILDE?
  private static boolean Compound_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Compound_0")) return false;
    consumeToken(builder_, TILDE);
    return true;
  }

  // Indexing+
  private static boolean Compound_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Compound_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Indexing(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!Indexing(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Compound_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // VariableRef | ExceptionCapture | OutputCapture
  public static boolean Condition(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Condition")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONDITION, "<condition>");
    result_ = VariableRef(builder_, level_ + 1);
    if (!result_) result_ = ExceptionCapture(builder_, level_ + 1);
    if (!result_) result_ = OutputCapture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_DEL CommandSep* Variable Index*
  public static boolean DeleteCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DeleteCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_DEL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_DEL);
    result_ = result_ && DeleteCommand_1(builder_, level_ + 1);
    result_ = result_ && Variable(builder_, level_ + 1);
    result_ = result_ && DeleteCommand_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, DELETE_COMMAND, result_);
    return result_;
  }

  // CommandSep*
  private static boolean DeleteCommand_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DeleteCommand_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!CommandSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DeleteCommand_1", pos_)) break;
    }
    return true;
  }

  // Index*
  private static boolean DeleteCommand_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DeleteCommand_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Index(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DeleteCommand_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // DOUBLE_QUOTE (TEXT|ESCAPED_QUOTED_TEXT|INVALID_ESCAPED_QUOTED_TEXT)* DOUBLE_QUOTE
  public static boolean DoubleQuoted(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DoubleQuoted")) return false;
    if (!nextTokenIs(builder_, DOUBLE_QUOTE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOUBLE_QUOTE);
    result_ = result_ && DoubleQuoted_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, DOUBLE_QUOTE);
    exit_section_(builder_, marker_, DOUBLE_QUOTED, result_);
    return result_;
  }

  // (TEXT|ESCAPED_QUOTED_TEXT|INVALID_ESCAPED_QUOTED_TEXT)*
  private static boolean DoubleQuoted_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DoubleQuoted_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!DoubleQuoted_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "DoubleQuoted_1", pos_)) break;
    }
    return true;
  }

  // TEXT|ESCAPED_QUOTED_TEXT|INVALID_ESCAPED_QUOTED_TEXT
  private static boolean DoubleQuoted_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "DoubleQuoted_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, TEXT);
    if (!result_) result_ = consumeToken(builder_, ESCAPED_QUOTED_TEXT);
    if (!result_) result_ = consumeToken(builder_, INVALID_ESCAPED_QUOTED_TEXT);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_ELIF Space Condition Space OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean ElIfBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElIfBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_ELIF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_ELIF);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, EL_IF_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_ELSE Space OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean ElseBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElseBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_ELSE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_ELSE);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, ELSE_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACKET Space? AMPERSAND Space? CLOSE_BRACKET
  static boolean EmptyMap(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyMap")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && EmptyMap_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, AMPERSAND);
    result_ = result_ && EmptyMap_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Space?
  private static boolean EmptyMap_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyMap_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // Space?
  private static boolean EmptyMap_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyMap_3")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_EXCEPT Space Variable Space OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean ExceptBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExceptBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_EXCEPT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_EXCEPT);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Variable(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, EXCEPT_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // QUESTION OPEN_PARAN Chunk CLOSE_PARAN
  public static boolean ExceptionCapture(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExceptionCapture")) return false;
    if (!nextTokenIs(builder_, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, QUESTION, OPEN_PARAN);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PARAN);
    exit_section_(builder_, marker_, EXCEPTION_CAPTURE, result_);
    return result_;
  }

  /* ********************************************************** */
  // QUESTION '>' Space? Compound
  public static boolean ExitusRedir(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExitusRedir")) return false;
    if (!nextTokenIs(builder_, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, QUESTION);
    result_ = result_ && consumeToken(builder_, ">");
    result_ = result_ && ExitusRedir_2(builder_, level_ + 1);
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXITUS_REDIR, result_);
    return result_;
  }

  // Space?
  private static boolean ExitusRedir_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExitusRedir_2")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_FINALLY Space OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean FinallyBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FinallyBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FINALLY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FINALLY);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, FINALLY_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_FN Space Variable Space LambdaArguments? OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean FnCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FnCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FN);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Variable(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && FnCommand_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, FN_COMMAND, result_);
    return result_;
  }

  // LambdaArguments?
  private static boolean FnCommand_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FnCommand_4")) return false;
    LambdaArguments(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_FOR Space Variable Space Collection Space OPEN_BRACE Chunk CLOSE_BRACE (Space ElseBlock)?
  public static boolean ForCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FOR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FOR);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Variable(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Collection(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && ForCommand_9(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_COMMAND, result_);
    return result_;
  }

  // (Space ElseBlock)?
  private static boolean ForCommand_9(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForCommand_9")) return false;
    ForCommand_9_0(builder_, level_ + 1);
    return true;
  }

  // Space ElseBlock
  private static boolean ForCommand_9_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForCommand_9_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && ElseBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // AssignmentOnlyForm | SpecialCommand | CommandForm
  static boolean Form(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Form")) return false;
    boolean result_;
    result_ = AssignmentOnlyForm(builder_, level_ + 1);
    if (!result_) result_ = SpecialCommand(builder_, level_ + 1);
    if (!result_) result_ = CommandForm(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_IF Space Condition Space OPEN_BRACE Chunk CLOSE_BRACE (Space ElIfBlock)* (Space ElseBlock)?
  public static boolean IfCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_IF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_IF);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && IfCommand_7(builder_, level_ + 1);
    result_ = result_ && IfCommand_8(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_COMMAND, result_);
    return result_;
  }

  // (Space ElIfBlock)*
  private static boolean IfCommand_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand_7")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!IfCommand_7_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "IfCommand_7", pos_)) break;
    }
    return true;
  }

  // Space ElIfBlock
  private static boolean IfCommand_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand_7_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && ElIfBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (Space ElseBlock)?
  private static boolean IfCommand_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand_8")) return false;
    IfCommand_8_0(builder_, level_ + 1);
    return true;
  }

  // Space ElseBlock
  private static boolean IfCommand_8_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand_8_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && ElseBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACKET Primary+ CLOSE_BRACKET
  static boolean Index(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Index")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && Index_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Primary+
  private static boolean Index_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Index_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Primary(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!Primary(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Index_1", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Primary Index*
  static boolean Indexing(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Indexing")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Primary(builder_, level_ + 1);
    result_ = result_ && Indexing_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Index*
  private static boolean Indexing_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Indexing_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Index(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Indexing_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // LambdaArguments? OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean Lambda(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Lambda")) return false;
    if (!nextTokenIs(builder_, "<lambda>", OPEN_BRACE, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LAMBDA, "<lambda>");
    result_ = Lambda_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // LambdaArguments?
  private static boolean Lambda_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Lambda_0")) return false;
    LambdaArguments(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_BRACKET Space? ((parameter | MapPair) Space?)* CLOSE_BRACKET
  public static boolean LambdaArguments(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && LambdaArguments_1(builder_, level_ + 1);
    result_ = result_ && LambdaArguments_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, LAMBDA_ARGUMENTS, result_);
    return result_;
  }

  // Space?
  private static boolean LambdaArguments_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // ((parameter | MapPair) Space?)*
  private static boolean LambdaArguments_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!LambdaArguments_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LambdaArguments_2", pos_)) break;
    }
    return true;
  }

  // (parameter | MapPair) Space?
  private static boolean LambdaArguments_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = LambdaArguments_2_0_0(builder_, level_ + 1);
    result_ = result_ && LambdaArguments_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // parameter | MapPair
  private static boolean LambdaArguments_2_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_2_0_0")) return false;
    boolean result_;
    result_ = parameter(builder_, level_ + 1);
    if (!result_) result_ = MapPair(builder_, level_ + 1);
    return result_;
  }

  // Space?
  private static boolean LambdaArguments_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_2_0_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_BRACKET Array CLOSE_BRACKET
  public static boolean List(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "List")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && Array(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, LIST, result_);
    return result_;
  }

  /* ********************************************************** */
  // 'or' | 'and'
  static boolean LogicCMD(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LogicCMD")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "or");
    if (!result_) result_ = consumeToken(builder_, "and");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LogicCMD (CommandSep* argument)* CommandSep*
  public static boolean LogicCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LogicCommand")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LOGIC_COMMAND, "<logic command>");
    result_ = LogicCMD(builder_, level_ + 1);
    result_ = result_ && LogicCommand_1(builder_, level_ + 1);
    result_ = result_ && LogicCommand_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (CommandSep* argument)*
  private static boolean LogicCommand_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LogicCommand_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!LogicCommand_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LogicCommand_1", pos_)) break;
    }
    return true;
  }

  // CommandSep* argument
  private static boolean LogicCommand_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LogicCommand_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = LogicCommand_1_0_0(builder_, level_ + 1);
    result_ = result_ && argument(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CommandSep*
  private static boolean LogicCommand_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LogicCommand_1_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!CommandSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LogicCommand_1_0_0", pos_)) break;
    }
    return true;
  }

  // CommandSep*
  private static boolean LogicCommand_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LogicCommand_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!CommandSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LogicCommand_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // EmptyMap | MapWithValues
  public static boolean Map(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Map")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = EmptyMap(builder_, level_ + 1);
    if (!result_) result_ = MapWithValues(builder_, level_ + 1);
    exit_section_(builder_, marker_, MAP, result_);
    return result_;
  }

  /* ********************************************************** */
  // AMPERSAND Space? Compound EQUALS Space? Compound
  public static boolean MapPair(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapPair")) return false;
    if (!nextTokenIs(builder_, AMPERSAND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AMPERSAND);
    result_ = result_ && MapPair_1(builder_, level_ + 1);
    result_ = result_ && Compound(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && MapPair_4(builder_, level_ + 1);
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, MAP_PAIR, result_);
    return result_;
  }

  // Space?
  private static boolean MapPair_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapPair_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // Space?
  private static boolean MapPair_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapPair_4")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // OPEN_BRACKET ArraySpace* (MapPair ArraySpace*)+ CLOSE_BRACKET
  static boolean MapWithValues(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapWithValues")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && MapWithValues_1(builder_, level_ + 1);
    result_ = result_ && MapWithValues_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ArraySpace*
  private static boolean MapWithValues_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapWithValues_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ArraySpace(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MapWithValues_1", pos_)) break;
    }
    return true;
  }

  // (MapPair ArraySpace*)+
  private static boolean MapWithValues_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapWithValues_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = MapWithValues_2_0(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!MapWithValues_2_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MapWithValues_2", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // MapPair ArraySpace*
  private static boolean MapWithValues_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapWithValues_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = MapPair(builder_, level_ + 1);
    result_ = result_ && MapWithValues_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ArraySpace*
  private static boolean MapWithValues_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapWithValues_2_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ArraySpace(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "MapWithValues_2_0_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // OPEN_PARAN Chunk CLOSE_PARAN
  public static boolean OutputCapture(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "OutputCapture")) return false;
    if (!nextTokenIs(builder_, OPEN_PARAN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_PARAN);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_PARAN);
    exit_section_(builder_, marker_, OUTPUT_CAPTURE, result_);
    return result_;
  }

  /* ********************************************************** */
  // Form Space? PipelineExtension*
  static boolean Pipeline(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pipeline")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Form(builder_, level_ + 1);
    result_ = result_ && Pipeline_1(builder_, level_ + 1);
    result_ = result_ && Pipeline_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Space?
  private static boolean Pipeline_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pipeline_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // PipelineExtension*
  private static boolean Pipeline_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pipeline_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!PipelineExtension(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Pipeline_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PIPE Space? (EOL Space?)? Form Space?
  static boolean PipelineExtension(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension")) return false;
    if (!nextTokenIs(builder_, PIPE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && PipelineExtension_1(builder_, level_ + 1);
    result_ = result_ && PipelineExtension_2(builder_, level_ + 1);
    result_ = result_ && Form(builder_, level_ + 1);
    result_ = result_ && PipelineExtension_4(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Space?
  private static boolean PipelineExtension_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // (EOL Space?)?
  private static boolean PipelineExtension_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension_2")) return false;
    PipelineExtension_2_0(builder_, level_ + 1);
    return true;
  }

  // EOL Space?
  private static boolean PipelineExtension_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EOL);
    result_ = result_ && PipelineExtension_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Space?
  private static boolean PipelineExtension_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension_2_0_1")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // Space?
  private static boolean PipelineExtension_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension_4")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // EOL | SEMICOLON
  public static boolean PipelineSep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineSep")) return false;
    if (!nextTokenIs(builder_, "<pipeline sep>", EOL, SEMICOLON)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PIPELINE_SEP, "<pipeline sep>");
    result_ = consumeToken(builder_, EOL);
    if (!result_) result_ = consumeToken(builder_, SEMICOLON);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // Bareword | SingleQuoted | DoubleQuoted | VariableRef | WILDCARD | TILDE | ExceptionCapture | OutputCapture | Lambda | List | Map | Braced
  static boolean Primary(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Primary")) return false;
    boolean result_;
    result_ = Bareword(builder_, level_ + 1);
    if (!result_) result_ = SingleQuoted(builder_, level_ + 1);
    if (!result_) result_ = DoubleQuoted(builder_, level_ + 1);
    if (!result_) result_ = VariableRef(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, WILDCARD);
    if (!result_) result_ = consumeToken(builder_, TILDE);
    if (!result_) result_ = ExceptionCapture(builder_, level_ + 1);
    if (!result_) result_ = OutputCapture(builder_, level_ + 1);
    if (!result_) result_ = Lambda(builder_, level_ + 1);
    if (!result_) result_ = List(builder_, level_ + 1);
    if (!result_) result_ = Map(builder_, level_ + 1);
    if (!result_) result_ = Braced(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // RedirFD? RedirMode Space? ( AMPERSAND? Compound? )
  public static boolean Redir(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REDIR, "<redir>");
    result_ = Redir_0(builder_, level_ + 1);
    result_ = result_ && RedirMode(builder_, level_ + 1);
    result_ = result_ && Redir_2(builder_, level_ + 1);
    result_ = result_ && Redir_3(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // RedirFD?
  private static boolean Redir_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_0")) return false;
    RedirFD(builder_, level_ + 1);
    return true;
  }

  // Space?
  private static boolean Redir_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_2")) return false;
    Space(builder_, level_ + 1);
    return true;
  }

  // AMPERSAND? Compound?
  private static boolean Redir_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Redir_3_0(builder_, level_ + 1);
    result_ = result_ && Redir_3_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AMPERSAND?
  private static boolean Redir_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_3_0")) return false;
    consumeToken(builder_, AMPERSAND);
    return true;
  }

  // Compound?
  private static boolean Redir_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_3_1")) return false;
    Compound(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // '0' | '1' | '2'
  static boolean RedirFD(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RedirFD")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "0");
    if (!result_) result_ = consumeToken(builder_, "1");
    if (!result_) result_ = consumeToken(builder_, "2");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // '<' | '>' | '<>' | '>>'
  static boolean RedirMode(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "RedirMode")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, "<");
    if (!result_) result_ = consumeToken(builder_, ">");
    if (!result_) result_ = consumeToken(builder_, "<>");
    if (!result_) result_ = consumeToken(builder_, ">>");
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SINGLE_QUOTE (TEXT|ESCAPED_QUOTED_TEXT|INVALID_ESCAPED_QUOTED_TEXT)* SINGLE_QUOTE
  public static boolean SingleQuoted(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleQuoted")) return false;
    if (!nextTokenIs(builder_, SINGLE_QUOTE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SINGLE_QUOTE);
    result_ = result_ && SingleQuoted_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SINGLE_QUOTE);
    exit_section_(builder_, marker_, SINGLE_QUOTED, result_);
    return result_;
  }

  // (TEXT|ESCAPED_QUOTED_TEXT|INVALID_ESCAPED_QUOTED_TEXT)*
  private static boolean SingleQuoted_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleQuoted_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!SingleQuoted_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "SingleQuoted_1", pos_)) break;
    }
    return true;
  }

  // TEXT|ESCAPED_QUOTED_TEXT|INVALID_ESCAPED_QUOTED_TEXT
  private static boolean SingleQuoted_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SingleQuoted_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, TEXT);
    if (!result_) result_ = consumeToken(builder_, ESCAPED_QUOTED_TEXT);
    if (!result_) result_ = consumeToken(builder_, INVALID_ESCAPED_QUOTED_TEXT);
    return result_;
  }

  /* ********************************************************** */
  // INLINE_WHITESPACE
  static boolean Space(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, INLINE_WHITESPACE);
  }

  /* ********************************************************** */
  // DeleteCommand | UseCommand | LogicCommand | IfCommand | WhileCommand | ForCommand | TryCommand | FnCommand
  static boolean SpecialCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "SpecialCommand")) return false;
    boolean result_;
    result_ = DeleteCommand(builder_, level_ + 1);
    if (!result_) result_ = UseCommand(builder_, level_ + 1);
    if (!result_) result_ = LogicCommand(builder_, level_ + 1);
    if (!result_) result_ = IfCommand(builder_, level_ + 1);
    if (!result_) result_ = WhileCommand(builder_, level_ + 1);
    if (!result_) result_ = ForCommand(builder_, level_ + 1);
    if (!result_) result_ = TryCommand(builder_, level_ + 1);
    if (!result_) result_ = FnCommand(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_TRY Space OPEN_BRACE Chunk CLOSE_BRACE (Space ExceptBlock)? (Space ElseBlock)? (Space FinallyBlock)?
  public static boolean TryCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_TRY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_TRY);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && TryCommand_5(builder_, level_ + 1);
    result_ = result_ && TryCommand_6(builder_, level_ + 1);
    result_ = result_ && TryCommand_7(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRY_COMMAND, result_);
    return result_;
  }

  // (Space ExceptBlock)?
  private static boolean TryCommand_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_5")) return false;
    TryCommand_5_0(builder_, level_ + 1);
    return true;
  }

  // Space ExceptBlock
  private static boolean TryCommand_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && ExceptBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (Space ElseBlock)?
  private static boolean TryCommand_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_6")) return false;
    TryCommand_6_0(builder_, level_ + 1);
    return true;
  }

  // Space ElseBlock
  private static boolean TryCommand_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && ElseBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (Space FinallyBlock)?
  private static boolean TryCommand_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_7")) return false;
    TryCommand_7_0(builder_, level_ + 1);
    return true;
  }

  // Space FinallyBlock
  private static boolean TryCommand_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_7_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && FinallyBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_USE Space Bareword
  public static boolean UseCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_USE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_USE);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Bareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, USE_COMMAND, result_);
    return result_;
  }

  /* ********************************************************** */
  // VARIABLE_CHAR+
  public static boolean Variable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Variable")) return false;
    if (!nextTokenIs(builder_, VARIABLE_CHAR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, VARIABLE_CHAR)) break;
      if (!empty_element_parsed_guard_(builder_, "Variable", pos_)) break;
    }
    exit_section_(builder_, marker_, VARIABLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // DOLLAR_SIGN AT_SYMBOL? VARIABLE_CHAR+ Index*
  public static boolean VariableRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariableRef")) return false;
    if (!nextTokenIs(builder_, DOLLAR_SIGN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOLLAR_SIGN);
    result_ = result_ && VariableRef_1(builder_, level_ + 1);
    result_ = result_ && VariableRef_2(builder_, level_ + 1);
    result_ = result_ && VariableRef_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, VARIABLE_REF, result_);
    return result_;
  }

  // AT_SYMBOL?
  private static boolean VariableRef_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariableRef_1")) return false;
    consumeToken(builder_, AT_SYMBOL);
    return true;
  }

  // VARIABLE_CHAR+
  private static boolean VariableRef_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariableRef_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, VARIABLE_CHAR)) break;
      if (!empty_element_parsed_guard_(builder_, "VariableRef_2", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // Index*
  private static boolean VariableRef_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariableRef_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Index(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "VariableRef_3", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_WHILE Space Condition Space OPEN_BRACE Chunk CLOSE_BRACE (Space ElseBlock)?
  public static boolean WhileCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_WHILE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_WHILE);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && Space(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && WhileCommand_7(builder_, level_ + 1);
    exit_section_(builder_, marker_, WHILE_COMMAND, result_);
    return result_;
  }

  // (Space ElseBlock)?
  private static boolean WhileCommand_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileCommand_7")) return false;
    WhileCommand_7_0(builder_, level_ + 1);
    return true;
  }

  // Space ElseBlock
  private static boolean WhileCommand_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileCommand_7_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Space(builder_, level_ + 1);
    result_ = result_ && ElseBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Redir | MapPair | Compound | ExitusRedir
  public static boolean argument(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "argument")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ARGUMENT, "<argument>");
    result_ = Redir(builder_, level_ + 1);
    if (!result_) result_ = MapPair(builder_, level_ + 1);
    if (!result_) result_ = Compound(builder_, level_ + 1);
    if (!result_) result_ = ExitusRedir(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // CmdExpr | SingleQuoted | DoubleQuoted | VariableRef | ExceptionCapture | OutputCapture
  public static boolean head(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "head")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, HEAD, "<head>");
    result_ = CmdExpr(builder_, level_ + 1);
    if (!result_) result_ = SingleQuoted(builder_, level_ + 1);
    if (!result_) result_ = DoubleQuoted(builder_, level_ + 1);
    if (!result_) result_ = VariableRef(builder_, level_ + 1);
    if (!result_) result_ = ExceptionCapture(builder_, level_ + 1);
    if (!result_) result_ = OutputCapture(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // AT_SYMBOL? Compound
  public static boolean parameter(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARAMETER, "<parameter>");
    result_ = parameter_0(builder_, level_ + 1);
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // AT_SYMBOL?
  private static boolean parameter_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameter_0")) return false;
    consumeToken(builder_, AT_SYMBOL);
    return true;
  }

  /* ********************************************************** */
  // Chunk
  static boolean script(PsiBuilder builder_, int level_) {
    return Chunk(builder_, level_ + 1);
  }

}
