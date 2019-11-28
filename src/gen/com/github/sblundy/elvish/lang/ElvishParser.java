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
  // EOL
  static boolean ArraySpace(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, EOL);
  }

  /* ********************************************************** */
  // AssignmentTarget+ EQUALS Compound
  public static boolean Assignment(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGNMENT, "<assignment>");
    result_ = Assignment_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    pinned_ = result_; // pin = 2
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // AssignmentTarget+
  private static boolean Assignment_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Assignment_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AssignmentTarget(builder_, level_ + 1);
    while (result_) {
      int pos_ = current_position_(builder_);
      if (!AssignmentTarget(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Assignment_0", pos_)) break;
    }
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // Assignment
  static boolean AssignmentOnlyForm(PsiBuilder builder_, int level_) {
    return Assignment(builder_, level_ + 1);
  }

  /* ********************************************************** */
  // <<parseVariableName Variable Index>>
  static boolean AssignmentTarget(PsiBuilder builder_, int level_) {
    return parseVariableName(builder_, level_ + 1, ElvishParser::Variable, ElvishParser::Index);
  }

  /* ********************************************************** */
  // <<parseBareword BarewordInner>>
  public static boolean Bareword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Bareword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BAREWORD, "<bareword>");
    result_ = parseBareword(builder_, level_ + 1, ElvishParser::BarewordInner);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VARIABLE_CHAR|BAREWORD_CHAR|COMMA|<<parseKeywordAsBareword>>
  static boolean BarewordInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BarewordInner")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    if (!result_) result_ = consumeToken(builder_, BAREWORD_CHAR);
    if (!result_) result_ = consumeToken(builder_, COMMA);
    if (!result_) result_ = parseKeywordAsBareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACE EOL* BracedBareword (BracedSep* BracedBareword)* EOL* CLOSE_BRACE
  public static boolean Braced(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Braced_1(builder_, level_ + 1);
    result_ = result_ && BracedBareword(builder_, level_ + 1);
    result_ = result_ && Braced_3(builder_, level_ + 1);
    result_ = result_ && Braced_4(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, BRACED, result_);
    return result_;
  }

  // EOL*
  private static boolean Braced_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, EOL)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_1", pos_)) break;
    }
    return true;
  }

  // (BracedSep* BracedBareword)*
  private static boolean Braced_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!Braced_3_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_3", pos_)) break;
    }
    return true;
  }

  // BracedSep* BracedBareword
  private static boolean Braced_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Braced_3_0_0(builder_, level_ + 1);
    result_ = result_ && BracedBareword(builder_, level_ + 1);
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

  // EOL*
  private static boolean Braced_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Braced_4")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, EOL)) break;
      if (!empty_element_parsed_guard_(builder_, "Braced_4", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // <<parseBareword BracedBarewordInner>>
  public static boolean BracedBareword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedBareword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BRACED_BAREWORD, "<braced bareword>");
    result_ = parseBareword(builder_, level_ + 1, ElvishParser::BracedBarewordInner);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VARIABLE_CHAR|BAREWORD_CHAR|<<parseKeywordAsBareword>>
  static boolean BracedBarewordInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedBarewordInner")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    if (!result_) result_ = consumeToken(builder_, BAREWORD_CHAR);
    if (!result_) result_ = parseKeywordAsBareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EOL* COMMA EOL*
  public static boolean BracedSep(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep")) return false;
    if (!nextTokenIs(builder_, "<braced sep>", COMMA, EOL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BRACED_SEP, "<braced sep>");
    result_ = BracedSep_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA);
    result_ = result_ && BracedSep_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // EOL*
  private static boolean BracedSep_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, EOL)) break;
      if (!empty_element_parsed_guard_(builder_, "BracedSep_0", pos_)) break;
    }
    return true;
  }

  // EOL*
  private static boolean BracedSep_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "BracedSep_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!consumeToken(builder_, EOL)) break;
      if (!empty_element_parsed_guard_(builder_, "BracedSep_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PipelineSep
  static boolean ChuckSep(PsiBuilder builder_, int level_) {
    return PipelineSep(builder_, level_ + 1);
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
  // <<parseBareword CommandBarewordInner>>
  public static boolean CommandBareword(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandBareword")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMMAND_BAREWORD, "<command bareword>");
    result_ = parseBareword(builder_, level_ + 1, ElvishParser::CommandBarewordInner);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // VARIABLE_CHAR|BAREWORD_CHAR|COMMAND_BAREWORD_CHAR|WILDCARD|EQUALS
  static boolean CommandBarewordInner(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandBarewordInner")) return false;
    boolean result_;
    result_ = consumeToken(builder_, VARIABLE_CHAR);
    if (!result_) result_ = consumeToken(builder_, BAREWORD_CHAR);
    if (!result_) result_ = consumeToken(builder_, COMMAND_BAREWORD_CHAR);
    if (!result_) result_ = consumeToken(builder_, WILDCARD);
    if (!result_) result_ = consumeToken(builder_, EQUALS);
    return result_;
  }

  /* ********************************************************** */
  // head (CommandSep* argument)* AMPERSAND?
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

  // (CommandSep* argument)*
  private static boolean CommandForm_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!CommandForm_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CommandForm_1", pos_)) break;
    }
    return true;
  }

  // CommandSep* argument
  private static boolean CommandForm_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = CommandForm_1_0_0(builder_, level_ + 1);
    result_ = result_ && argument(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CommandSep*
  private static boolean CommandForm_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_1_0_0")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!CommandSep(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "CommandForm_1_0_0", pos_)) break;
    }
    return true;
  }

  // AMPERSAND?
  private static boolean CommandForm_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CommandForm_2")) return false;
    consumeToken(builder_, AMPERSAND);
    return true;
  }

  /* ********************************************************** */
  // CONTINUATION
  static boolean CommandSep(PsiBuilder builder_, int level_) {
    return consumeToken(builder_, CONTINUATION);
  }

  /* ********************************************************** */
  // Lambda | <<parseCompound TILDE? Indexing>>
  public static boolean Compound(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Compound")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPOUND, "<compound>");
    result_ = Lambda(builder_, level_ + 1);
    if (!result_) result_ = parseCompound(builder_, level_ + 1, ElvishParser::Compound_1_0, ElvishParser::Indexing);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // TILDE?
  private static boolean Compound_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Compound_1_0")) return false;
    consumeToken(builder_, TILDE);
    return true;
  }

  /* ********************************************************** */
  // Bareword | SingleQuoted | DoubleQuoted | VariableRef | WILDCARD | TILDE | ExceptionCapture | OutputCapture | List | Map | Braced
  static boolean CompoundPrimary(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "CompoundPrimary")) return false;
    boolean result_;
    result_ = Bareword(builder_, level_ + 1);
    if (!result_) result_ = SingleQuoted(builder_, level_ + 1);
    if (!result_) result_ = DoubleQuoted(builder_, level_ + 1);
    if (!result_) result_ = VariableRef(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, WILDCARD);
    if (!result_) result_ = consumeToken(builder_, TILDE);
    if (!result_) result_ = ExceptionCapture(builder_, level_ + 1);
    if (!result_) result_ = OutputCapture(builder_, level_ + 1);
    if (!result_) result_ = List(builder_, level_ + 1);
    if (!result_) result_ = Map(builder_, level_ + 1);
    if (!result_) result_ = Braced(builder_, level_ + 1);
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
  // KEYWORD_ELIF Condition OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean ElIfBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElIfBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_ELIF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_ELIF);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, EL_IF_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_ELSE OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean ElseBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ElseBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_ELSE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, KEYWORD_ELSE, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, ELSE_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // OPEN_BRACKET AMPERSAND CLOSE_BRACKET
  static boolean EmptyMap(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "EmptyMap")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, OPEN_BRACKET, AMPERSAND, CLOSE_BRACKET);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_EXCEPT Variable OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean ExceptBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExceptBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_EXCEPT)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_EXCEPT);
    result_ = result_ && Variable(builder_, level_ + 1);
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
  // QUESTION '>' Compound
  public static boolean ExitusRedir(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ExitusRedir")) return false;
    if (!nextTokenIs(builder_, QUESTION)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, QUESTION);
    result_ = result_ && consumeToken(builder_, ">");
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, EXITUS_REDIR, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_FINALLY OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean FinallyBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FinallyBlock")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FINALLY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, KEYWORD_FINALLY, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, FINALLY_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_FN Variable LambdaArguments? OPEN_BRACE Chunk CLOSE_BRACE
  public static boolean FnCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FnCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FN);
    result_ = result_ && Variable(builder_, level_ + 1);
    result_ = result_ && FnCommand_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    exit_section_(builder_, marker_, FN_COMMAND, result_);
    return result_;
  }

  // LambdaArguments?
  private static boolean FnCommand_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "FnCommand_2")) return false;
    LambdaArguments(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_FOR Variable Collection OPEN_BRACE Chunk CLOSE_BRACE ElseBlock?
  public static boolean ForCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_FOR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_FOR);
    result_ = result_ && Variable(builder_, level_ + 1);
    result_ = result_ && Collection(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && ForCommand_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, FOR_COMMAND, result_);
    return result_;
  }

  // ElseBlock?
  private static boolean ForCommand_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ForCommand_6")) return false;
    ElseBlock(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_IF Condition OPEN_BRACE Chunk CLOSE_BRACE ElIfBlock* ElseBlock?
  public static boolean IfCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_IF)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_IF);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && IfCommand_5(builder_, level_ + 1);
    result_ = result_ && IfCommand_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_COMMAND, result_);
    return result_;
  }

  // ElIfBlock*
  private static boolean IfCommand_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!ElIfBlock(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "IfCommand_5", pos_)) break;
    }
    return true;
  }

  // ElseBlock?
  private static boolean IfCommand_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "IfCommand_6")) return false;
    ElseBlock(builder_, level_ + 1);
    return true;
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
  // <<parseVariableName CompoundPrimary Index>>
  static boolean Indexing(PsiBuilder builder_, int level_) {
    return parseVariableName(builder_, level_ + 1, ElvishParser::CompoundPrimary, ElvishParser::Index);
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
  // OPEN_BRACKET ((parameter | MapPair) )* CLOSE_BRACKET
  public static boolean LambdaArguments(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments")) return false;
    if (!nextTokenIs(builder_, OPEN_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, OPEN_BRACKET);
    result_ = result_ && LambdaArguments_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACKET);
    exit_section_(builder_, marker_, LAMBDA_ARGUMENTS, result_);
    return result_;
  }

  // ((parameter | MapPair) )*
  private static boolean LambdaArguments_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!LambdaArguments_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "LambdaArguments_1", pos_)) break;
    }
    return true;
  }

  // parameter | MapPair
  private static boolean LambdaArguments_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "LambdaArguments_1_0")) return false;
    boolean result_;
    result_ = parameter(builder_, level_ + 1);
    if (!result_) result_ = MapPair(builder_, level_ + 1);
    return result_;
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
  // AMPERSAND Compound EQUALS Compound
  public static boolean MapPair(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "MapPair")) return false;
    if (!nextTokenIs(builder_, AMPERSAND)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AMPERSAND);
    result_ = result_ && Compound(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQUALS);
    result_ = result_ && Compound(builder_, level_ + 1);
    exit_section_(builder_, marker_, MAP_PAIR, result_);
    return result_;
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
  // AssignmentOnlyForm | PipelineHead PipelineExtension*
  static boolean Pipeline(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pipeline")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = AssignmentOnlyForm(builder_, level_ + 1);
    if (!result_) result_ = Pipeline_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PipelineHead PipelineExtension*
  private static boolean Pipeline_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pipeline_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = PipelineHead(builder_, level_ + 1);
    result_ = result_ && Pipeline_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // PipelineExtension*
  private static boolean Pipeline_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Pipeline_1_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!PipelineExtension(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "Pipeline_1_1", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // PIPE EOL? PipelineHead
  static boolean PipelineExtension(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension")) return false;
    if (!nextTokenIs(builder_, PIPE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, PIPE);
    result_ = result_ && PipelineExtension_1(builder_, level_ + 1);
    result_ = result_ && PipelineHead(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // EOL?
  private static boolean PipelineExtension_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineExtension_1")) return false;
    consumeToken(builder_, EOL);
    return true;
  }

  /* ********************************************************** */
  // SpecialCommand | CommandForm
  static boolean PipelineHead(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "PipelineHead")) return false;
    boolean result_;
    result_ = SpecialCommand(builder_, level_ + 1);
    if (!result_) result_ = CommandForm(builder_, level_ + 1);
    return result_;
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
  // RedirFD? RedirMode ( AMPERSAND? Compound? )
  public static boolean Redir(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, REDIR, "<redir>");
    result_ = Redir_0(builder_, level_ + 1);
    result_ = result_ && RedirMode(builder_, level_ + 1);
    result_ = result_ && Redir_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // RedirFD?
  private static boolean Redir_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_0")) return false;
    RedirFD(builder_, level_ + 1);
    return true;
  }

  // AMPERSAND? Compound?
  private static boolean Redir_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = Redir_2_0(builder_, level_ + 1);
    result_ = result_ && Redir_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // AMPERSAND?
  private static boolean Redir_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_2_0")) return false;
    consumeToken(builder_, AMPERSAND);
    return true;
  }

  // Compound?
  private static boolean Redir_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Redir_2_1")) return false;
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
  // KEYWORD_TRY OPEN_BRACE Chunk CLOSE_BRACE ExceptBlock? ElseBlock? FinallyBlock?
  public static boolean TryCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_TRY)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, KEYWORD_TRY, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && TryCommand_4(builder_, level_ + 1);
    result_ = result_ && TryCommand_5(builder_, level_ + 1);
    result_ = result_ && TryCommand_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, TRY_COMMAND, result_);
    return result_;
  }

  // ExceptBlock?
  private static boolean TryCommand_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_4")) return false;
    ExceptBlock(builder_, level_ + 1);
    return true;
  }

  // ElseBlock?
  private static boolean TryCommand_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_5")) return false;
    ElseBlock(builder_, level_ + 1);
    return true;
  }

  // FinallyBlock?
  private static boolean TryCommand_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "TryCommand_6")) return false;
    FinallyBlock(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // KEYWORD_USE Bareword
  public static boolean UseCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "UseCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_USE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_USE);
    result_ = result_ && Bareword(builder_, level_ + 1);
    exit_section_(builder_, marker_, USE_COMMAND, result_);
    return result_;
  }

  /* ********************************************************** */
  // VariableName
  public static boolean Variable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "Variable")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VARIABLE, "<variable>");
    result_ = VariableName(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // <<parseBareword VARIABLE_CHAR>>
  static boolean VariableName(PsiBuilder builder_, int level_) {
    return parseBareword(builder_, level_ + 1, VARIABLE_CHAR_parser_);
  }

  /* ********************************************************** */
  // <<parseVariableRef DOLLAR_SIGN AT_SYMBOL VariableName Index>>
  public static boolean VariableRef(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "VariableRef")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VARIABLE_REF, "<variable ref>");
    result_ = parseVariableRef(builder_, level_ + 1, DOLLAR_SIGN_parser_, AT_SYMBOL_parser_, ElvishParser::VariableName, ElvishParser::Index);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // KEYWORD_WHILE Condition OPEN_BRACE Chunk CLOSE_BRACE ElseBlock?
  public static boolean WhileCommand(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileCommand")) return false;
    if (!nextTokenIs(builder_, KEYWORD_WHILE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, KEYWORD_WHILE);
    result_ = result_ && Condition(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, OPEN_BRACE);
    result_ = result_ && Chunk(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, CLOSE_BRACE);
    result_ = result_ && WhileCommand_5(builder_, level_ + 1);
    exit_section_(builder_, marker_, WHILE_COMMAND, result_);
    return result_;
  }

  // ElseBlock?
  private static boolean WhileCommand_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "WhileCommand_5")) return false;
    ElseBlock(builder_, level_ + 1);
    return true;
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

  static final Parser AT_SYMBOL_parser_ = (builder_, level_) -> consumeToken(builder_, AT_SYMBOL);
  static final Parser DOLLAR_SIGN_parser_ = (builder_, level_) -> consumeToken(builder_, DOLLAR_SIGN);
  static final Parser VARIABLE_CHAR_parser_ = (builder_, level_) -> consumeToken(builder_, VARIABLE_CHAR);
}
