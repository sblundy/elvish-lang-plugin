/* The following code was generated by JFlex 1.7.0-1 tweaked for IntelliJ platform */

package com.github.sblundy.elvish.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.IntArrayList;

import com.github.sblundy.elvish.psi.ElvishTypes;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0-1
 * from the specification file <tt>/Users/steve/Projects/elvish-lang-plugin/src/main/grammars/Elvish.flex</tt>
 */
class ElvishLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int IN_SINGLE_QUOTE_STRING = 2;
  public static final int IN_DOUBLE_QUOTE_STRING = 4;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1,  1,  2, 2
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [11, 7, 3]
   * Total runtime size is 9136 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[(ZZ_CMAP_Z[ch>>10]<<7)|((ch>>3)&0x7f)]<<3)|(ch&0x7)];
  }

  /* The ZZ_CMAP_Z table has 1088 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\6\15\1\16\23\15"+
    "\1\17\1\15\1\20\1\21\12\15\1\22\2\23\6\15\1\24\1\25\u0400\23");

  /* The ZZ_CMAP_Y table has 2816 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\2\0\1\2\1\3\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16\3"+
    "\0\133\17\1\20\1\21\1\22\2\17\1\23\61\17\1\24\3\17\1\25\2\24\4\17\1\26\1\24"+
    "\6\17\1\0\3\17\1\27\1\30\1\0\3\17\1\31\35\17\1\32\7\17\1\33\14\17\1\34\1\0"+
    "\7\17\1\27\5\17\1\35\1\17\1\25\3\17\1\36\10\0\2\17\1\31\1\35\2\0\1\21\25\17"+
    "\1\37\1\40\1\41\2\17\1\42\1\43\1\44\1\40\1\45\1\46\1\47\1\50\2\17\1\51\1\52"+
    "\1\53\1\41\2\17\1\42\1\54\1\55\1\53\1\56\1\57\1\60\1\61\1\17\1\35\1\0\1\52"+
    "\1\32\1\23\2\17\1\42\1\62\1\44\1\32\1\63\1\64\1\0\1\50\1\17\1\34\1\57\1\52"+
    "\1\40\1\41\2\17\1\42\1\62\1\44\1\40\1\56\1\61\1\47\1\50\2\17\1\0\1\65\1\66"+
    "\1\67\1\70\1\71\1\66\1\17\1\72\1\66\1\67\1\73\1\0\1\61\2\17\1\27\1\37\1\31"+
    "\1\42\2\17\1\42\1\17\1\74\1\31\1\67\1\75\1\27\1\50\1\17\1\0\1\17\1\37\1\31"+
    "\1\42\2\17\1\42\1\37\1\44\1\31\1\67\1\75\1\76\1\50\1\17\1\77\1\0\1\52\1\31"+
    "\1\42\4\17\1\33\1\31\1\42\1\21\1\17\1\50\3\17\1\65\1\17\1\25\1\20\2\17\1\23"+
    "\1\100\1\25\1\101\1\102\1\17\1\61\1\17\1\103\1\0\1\24\6\17\1\53\3\17\1\51"+
    "\4\0\1\104\1\105\1\21\1\24\1\106\1\65\1\17\1\63\1\102\1\35\1\17\1\44\4\0\11"+
    "\17\1\24\3\17\1\30\1\24\4\17\1\24\3\17\1\31\1\17\1\31\1\17\1\27\4\0\30\17"+
    "\1\32\1\107\57\17\1\67\1\25\1\67\5\17\1\67\4\17\1\67\1\25\1\67\1\17\1\25\7"+
    "\17\1\67\10\17\1\33\3\17\1\30\3\17\1\34\12\17\2\35\123\17\1\30\13\17\1\64"+
    "\1\17\1\31\1\30\1\0\2\17\1\25\1\0\2\17\1\51\1\0\1\17\1\31\1\110\1\0\13\17"+
    "\1\35\1\17\1\34\1\17\1\34\1\17\1\25\1\17\1\34\13\17\1\0\5\17\1\27\10\17\1"+
    "\35\1\0\3\17\1\25\1\17\1\51\1\17\1\51\1\111\4\17\1\35\1\30\1\0\5\17\1\51\3"+
    "\17\1\34\1\17\1\66\7\17\1\50\7\17\1\25\3\17\1\40\1\17\1\34\1\17\1\34\1\17"+
    "\1\35\1\17\1\25\10\0\11\17\1\51\5\17\1\30\16\17\1\51\1\21\7\17\1\112\1\17"+
    "\1\74\7\17\1\64\6\0\1\17\1\0\4\17\1\25\1\34\36\17\1\35\1\112\42\17\2\35\4"+
    "\17\2\35\1\17\1\113\3\17\1\35\6\17\1\31\1\17\1\31\1\17\1\50\1\37\2\17\1\114"+
    "\1\25\5\17\1\115\6\17\1\31\1\17\1\44\2\17\1\25\1\17\1\30\3\17\1\25\2\0\4\17"+
    "\1\64\1\0\21\17\1\51\115\17\1\25\4\17\1\25\3\0\1\17\1\27\2\0\342\17\1\50\3"+
    "\17\1\35\4\17\1\74\1\17\1\42\1\34\2\0\1\21\2\0\5\17\1\25\5\17\1\25\22\17\1"+
    "\51\1\24\4\17\1\32\1\107\7\17\1\46\1\64\1\46\2\17\1\25\1\0\10\25\14\17\1\30"+
    "\7\0\3\17\1\23\12\17\1\51\1\0\32\17\1\35\3\0\1\17\1\51\10\17\1\24\11\17\1"+
    "\25\1\24\14\17\1\116\4\17\1\35\1\24\12\17\1\25\5\17\1\27\4\17\1\51\1\0\5\17"+
    "\1\25\33\17\1\25\326\17\1\35\1\0\302\17\1\35\5\0\21\17\1\30\6\17\1\25\1\0"+
    "\53\17\1\51\2\0\27\17\1\0\25\17\1\25\1\17\7\0\1\46\6\17\1\51\1\17\1\34\7\17"+
    "\1\0\10\17\1\35\1\61\1\17\1\34\3\17\1\35\12\17\1\51\1\46\3\17\1\30\11\17\1"+
    "\32\1\17\1\72\3\17\1\25\6\17\1\25\1\0\1\17\1\35\1\17\1\44\14\17\1\27\2\0\1"+
    "\112\2\17\1\25\1\0\3\117\1\0\2\25\6\17\1\35\1\0\17\17\1\35\1\17\1\34\164\17"+
    "\1\51\1\0\2\17\1\25\1\112\5\17\1\51\200\0\115\17\1\35\15\17\1\34\4\0\1\25"+
    "\1\0\1\112\1\116\2\17\1\25\1\102\1\120\17\17\1\34\1\0\1\112\55\17\2\0\10\17"+
    "\1\20\6\17\5\0\1\17\1\35\3\17\1\34\6\17\1\121\1\17\1\25\1\51\1\31\20\17\1"+
    "\40\1\24\26\17\1\25\3\20\1\103\2\25\1\0\1\122");

  /* The ZZ_CMAP_A table has 664 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\51\1\1\2\66\1\50\2\0\1\51\1\36\1\61\1\2\1\64\1\41\1\62\1\60\1\56\1"+
    "\57\1\43\1\41\1\0\1\42\1\46\1\41\10\7\2\5\1\44\1\0\1\40\1\37\1\40\1\55\1\47"+
    "\6\14\16\45\1\4\5\45\1\54\1\3\1\13\1\11\1\45\1\0\1\31\1\16\1\12\1\35\1\17"+
    "\1\23\1\44\1\25\1\22\2\44\1\20\1\44\1\30\1\33\1\26\1\44\1\34\1\21\1\27\1\6"+
    "\1\15\1\24\1\10\1\32\1\44\1\52\1\65\1\53\1\63\6\0\1\66\2\0\10\44\2\0\6\44"+
    "\4\0\7\44\1\0\1\44\1\0\4\44\1\0\5\44\1\0\16\44\2\0\2\44\2\0\6\44\5\0\5\44"+
    "\3\0\5\44\1\0\10\44\1\0\4\44\2\0\5\44\6\0\6\44\2\0\4\44\2\0\1\44\1\0\4\44"+
    "\1\0\10\44\2\0\2\44\2\0\6\44\1\0\7\44\1\0\1\44\3\0\4\44\2\0\5\44\2\0\4\44"+
    "\10\0\1\44\4\0\2\44\1\0\5\44\2\0\6\44\5\0\3\44\1\0\6\44\4\0\2\44\1\0\2\44"+
    "\1\0\2\44\1\0\2\44\2\0\1\44\1\0\3\44\2\0\3\44\3\0\1\44\7\0\4\44\1\0\1\44\7"+
    "\0\3\44\1\0\2\44\1\0\5\44\1\0\3\44\2\0\1\44\11\0\2\44\1\0\6\44\3\0\3\44\1"+
    "\0\4\44\3\0\2\44\1\0\1\44\1\0\2\44\3\0\2\44\3\0\2\44\4\0\3\44\6\0\3\44\3\0"+
    "\3\44\5\0\2\44\7\0\1\44\2\0\2\44\5\0\4\44\1\0\1\44\4\0\1\44\4\0\6\44\1\0\1"+
    "\44\3\0\3\44\4\0\2\44\1\0\1\44\2\0\2\44\1\0\1\44\2\0\1\44\3\0\3\44\1\0\1\44"+
    "\1\0\1\44\5\0\1\44\2\0\1\44\1\0\2\44\4\0\1\44\3\0\4\44\3\0\5\44\1\0\1\44\1"+
    "\0\1\44\1\0\1\44\1\0\1\44\2\0\3\44\1\0\2\44\2\66\6\44\5\0\3\44\1\0\6\44\1"+
    "\0\2\44\1\0\2\44\1\0\5\44\1\0\4\44\1\0\5\44\2\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\1\1\4\1\5\1\6"+
    "\6\4\1\7\1\10\3\5\2\7\1\11\1\12\1\13"+
    "\1\14\1\15\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\26\1\25\1\0\1\27\1\3\2\30"+
    "\2\4\1\31\1\4\1\32\4\4\1\5\1\33\1\34"+
    "\1\0\1\35\6\36\4\4\1\37\1\4\1\40\1\41"+
    "\4\0\1\4\1\42\1\43\2\4\2\0\2\4\1\44"+
    "\1\0\1\45\1\4\1\0\1\46";

  private static int [] zzUnpackAction() {
    int [] result = new int[90];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\67\0\156\0\245\0\334\0\u0113\0\u014a\0\u0181"+
    "\0\245\0\245\0\u01b8\0\u01ef\0\u0226\0\u025d\0\u0294\0\u02cb"+
    "\0\u0302\0\u0339\0\u0370\0\u03a7\0\u0181\0\u03a7\0\u03de\0\245"+
    "\0\245\0\245\0\245\0\245\0\245\0\245\0\245\0\245"+
    "\0\u0181\0\u0415\0\245\0\u044c\0\u0483\0\u04ba\0\u04f1\0\245"+
    "\0\u0528\0\245\0\u055f\0\u0596\0\u05cd\0\u0181\0\u0604\0\u0181"+
    "\0\u063b\0\u0672\0\u06a9\0\u06e0\0\u0717\0\u03de\0\u074e\0\u074e"+
    "\0\245\0\245\0\u0785\0\u07bc\0\u07f3\0\u082a\0\u0861\0\u0898"+
    "\0\u08cf\0\u0906\0\u093d\0\u0181\0\u0974\0\u0181\0\u0181\0\u09ab"+
    "\0\u09e2\0\u0a19\0\u0a50\0\u0a87\0\u0181\0\u0181\0\u0abe\0\u0af5"+
    "\0\u0b2c\0\u082a\0\u0b63\0\u0b9a\0\u0181\0\u0bd1\0\u0181\0\u0c08"+
    "\0\u07bc\0\u0181";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[90];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\4\1\5\1\6\1\7\5\10\1\11\1\10\1\12"+
    "\3\10\1\13\2\10\1\14\1\15\1\16\2\10\1\17"+
    "\5\10\1\20\1\21\1\22\1\23\1\24\1\25\1\11"+
    "\2\10\1\26\1\27\2\5\1\30\1\31\1\32\1\33"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\1\42\1\43"+
    "\1\4\60\44\1\45\6\44\3\46\1\47\55\46\1\50"+
    "\5\46\70\0\1\5\46\0\2\5\15\0\1\6\1\51"+
    "\65\6\1\0\1\52\46\0\1\53\22\0\5\10\1\0"+
    "\1\10\1\0\22\10\1\26\2\0\1\26\1\10\1\0"+
    "\2\10\2\26\13\0\1\10\7\0\4\10\1\54\1\0"+
    "\1\10\1\0\4\10\1\55\15\10\1\26\2\0\1\26"+
    "\1\10\1\0\2\10\2\26\13\0\1\10\7\0\5\10"+
    "\1\0\1\10\1\0\7\10\1\56\12\10\1\26\2\0"+
    "\1\26\1\10\1\0\2\10\2\26\13\0\1\10\7\0"+
    "\5\10\1\0\1\10\1\0\6\10\1\57\5\10\1\60"+
    "\2\10\1\61\2\10\1\26\2\0\1\26\1\10\1\0"+
    "\2\10\2\26\13\0\1\10\7\0\5\10\1\0\1\10"+
    "\1\0\11\10\1\62\10\10\1\26\2\0\1\26\1\10"+
    "\1\0\2\10\2\26\13\0\1\10\7\0\5\10\1\0"+
    "\1\10\1\0\20\10\1\63\1\10\1\26\2\0\1\26"+
    "\1\10\1\0\2\10\2\26\13\0\1\10\7\0\5\10"+
    "\1\0\1\10\1\0\3\10\1\64\16\10\1\26\2\0"+
    "\1\26\1\10\1\0\2\10\2\26\13\0\1\10\7\0"+
    "\5\26\1\0\1\26\1\0\23\26\1\65\1\0\2\26"+
    "\1\0\4\26\13\0\1\26\42\0\1\65\50\0\1\11"+
    "\15\0\1\65\33\0\5\26\1\0\1\26\1\0\23\26"+
    "\2\0\2\26\1\0\4\26\13\0\1\26\7\0\5\66"+
    "\1\0\1\66\1\0\22\66\1\26\2\0\1\26\1\66"+
    "\1\0\2\66\2\26\13\0\1\66\7\0\5\67\1\0"+
    "\1\67\1\0\22\67\4\0\1\67\1\0\2\67\1\0"+
    "\1\70\13\0\1\67\3\0\60\44\1\0\6\44\60\0"+
    "\1\71\6\0\3\46\1\0\55\46\1\0\5\46\1\72"+
    "\1\0\1\72\1\71\1\73\1\72\1\74\1\75\1\76"+
    "\2\77\2\72\3\71\3\72\1\71\3\72\3\71\2\72"+
    "\1\71\13\72\1\0\10\72\1\71\4\72\2\0\1\51"+
    "\46\0\1\51\17\0\1\52\71\0\5\10\1\0\1\100"+
    "\1\0\22\10\1\26\2\0\1\26\1\10\1\0\2\10"+
    "\2\26\13\0\1\10\7\0\5\10\1\0\1\10\1\0"+
    "\5\10\1\101\1\102\13\10\1\26\2\0\1\26\1\10"+
    "\1\0\2\10\2\26\13\0\1\10\7\0\5\10\1\0"+
    "\1\10\1\0\14\10\1\103\5\10\1\26\2\0\1\26"+
    "\1\10\1\0\2\10\2\26\13\0\1\10\7\0\5\10"+
    "\1\0\1\10\1\0\20\10\1\104\1\10\1\26\2\0"+
    "\1\26\1\10\1\0\2\10\2\26\13\0\1\10\7\0"+
    "\5\10\1\0\1\10\1\0\6\10\1\105\13\10\1\26"+
    "\2\0\1\26\1\10\1\0\2\10\2\26\13\0\1\10"+
    "\7\0\5\10\1\0\1\10\1\0\16\10\1\106\3\10"+
    "\1\26\2\0\1\26\1\10\1\0\2\10\2\26\13\0"+
    "\1\10\7\0\5\10\1\0\1\10\1\0\4\10\1\107"+
    "\15\10\1\26\2\0\1\26\1\10\1\0\2\10\2\26"+
    "\13\0\1\10\24\0\1\11\51\0\5\67\1\0\1\67"+
    "\1\0\22\67\4\0\1\67\1\0\2\67\15\0\1\67"+
    "\10\0\1\110\1\0\1\110\2\0\1\110\1\0\1\110"+
    "\1\0\2\110\3\0\1\110\5\0\1\110\3\0\1\110"+
    "\36\0\1\111\1\0\1\111\2\0\1\111\1\0\1\111"+
    "\1\0\2\111\3\0\1\111\5\0\1\111\3\0\1\111"+
    "\40\0\1\112\64\0\1\113\1\0\1\113\2\0\1\113"+
    "\1\0\1\113\1\0\2\113\3\0\1\113\5\0\1\113"+
    "\3\0\1\113\34\0\2\71\4\0\1\71\1\0\2\71"+
    "\30\0\1\71\1\0\1\71\4\0\1\71\16\0\5\10"+
    "\1\0\1\10\1\0\3\10\1\114\16\10\1\26\2\0"+
    "\1\26\1\10\1\0\2\10\2\26\13\0\1\10\7\0"+
    "\5\10\1\0\1\10\1\0\3\10\1\115\16\10\1\26"+
    "\2\0\1\26\1\10\1\0\2\10\2\26\13\0\1\10"+
    "\7\0\5\10\1\0\1\10\1\0\7\10\1\116\12\10"+
    "\1\26\2\0\1\26\1\10\1\0\2\10\2\26\13\0"+
    "\1\10\7\0\5\10\1\0\1\10\1\0\15\10\1\117"+
    "\4\10\1\26\2\0\1\26\1\10\1\0\2\10\2\26"+
    "\13\0\1\10\7\0\5\10\1\0\1\10\1\0\4\10"+
    "\1\120\15\10\1\26\2\0\1\26\1\10\1\0\2\10"+
    "\2\26\13\0\1\10\10\0\1\121\1\0\1\121\2\0"+
    "\1\121\1\0\1\121\1\0\2\121\3\0\1\121\5\0"+
    "\1\121\3\0\1\121\36\0\1\122\1\0\1\122\2\0"+
    "\1\122\1\0\1\122\1\0\2\122\3\0\1\122\5\0"+
    "\1\122\3\0\1\122\40\0\1\71\64\0\1\71\1\0"+
    "\1\71\2\0\1\71\1\0\1\71\1\0\2\71\3\0"+
    "\1\71\5\0\1\71\3\0\1\71\35\0\5\10\1\0"+
    "\1\10\1\0\12\10\1\123\7\10\1\26\2\0\1\26"+
    "\1\10\1\0\2\10\2\26\13\0\1\10\7\0\5\10"+
    "\1\0\1\10\1\0\4\10\1\124\15\10\1\26\2\0"+
    "\1\26\1\10\1\0\2\10\2\26\13\0\1\10\7\0"+
    "\5\10\1\0\1\10\1\0\3\10\1\125\16\10\1\26"+
    "\2\0\1\26\1\10\1\0\2\10\2\26\13\0\1\10"+
    "\10\0\1\126\1\0\1\126\2\0\1\126\1\0\1\126"+
    "\1\0\2\126\3\0\1\126\5\0\1\126\3\0\1\126"+
    "\35\0\5\10\1\0\1\10\1\0\13\10\1\127\6\10"+
    "\1\26\2\0\1\26\1\10\1\0\2\10\2\26\13\0"+
    "\1\10\7\0\5\10\1\0\1\10\1\0\4\10\1\130"+
    "\15\10\1\26\2\0\1\26\1\10\1\0\2\10\2\26"+
    "\13\0\1\10\10\0\1\131\1\0\1\131\2\0\1\131"+
    "\1\0\1\131\1\0\2\131\3\0\1\131\5\0\1\131"+
    "\3\0\1\131\35\0\5\10\1\0\1\10\1\0\16\10"+
    "\1\132\3\10\1\26\2\0\1\26\1\10\1\0\2\10"+
    "\2\26\13\0\1\10\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3135];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\3\0\1\11\4\1\2\11\15\1\11\11\2\1\1\11"+
    "\3\1\1\0\1\11\1\1\1\11\15\1\1\0\2\11"+
    "\15\1\4\0\5\1\2\0\3\1\1\0\2\1\1\0"+
    "\1\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[90];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  final IntArrayList states = new IntArrayList();

  private void yyPushState(int newState) {
      states.add(yystate());
      yybegin(newState);
  }

  private void yyPopState() {
      int prevState = states.remove(states.size() - 1);
      yybegin(prevState);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  ElvishLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
              {
                return null;
              }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return TokenType.BAD_CHARACTER;
            } 
            // fall through
          case 39: break;
          case 2: 
            { return TokenType.WHITE_SPACE;
            } 
            // fall through
          case 40: break;
          case 3: 
            { return ElvishTypes.COMMENT;
            } 
            // fall through
          case 41: break;
          case 4: 
            { return ElvishTypes.VARIABLE;
            } 
            // fall through
          case 42: break;
          case 5: 
            { return ElvishTypes.BUILTIN_OPERATOR_FN;
            } 
            // fall through
          case 43: break;
          case 6: 
            { return ElvishTypes.CLOSE_BRACKET;
            } 
            // fall through
          case 44: break;
          case 7: 
            { return ElvishTypes.BAREWORD;
            } 
            // fall through
          case 45: break;
          case 8: 
            { return ElvishTypes.EQUALS;
            } 
            // fall through
          case 46: break;
          case 9: 
            { return ElvishTypes.OPEN_BRACE;
            } 
            // fall through
          case 47: break;
          case 10: 
            { return ElvishTypes.CLOSE_BRACE;
            } 
            // fall through
          case 48: break;
          case 11: 
            { return ElvishTypes.OPEN_BRACKET;
            } 
            // fall through
          case 49: break;
          case 12: 
            { return ElvishTypes.QUESTION;
            } 
            // fall through
          case 50: break;
          case 13: 
            { return ElvishTypes.OPEN_PARAN;
            } 
            // fall through
          case 51: break;
          case 14: 
            { return ElvishTypes.CLOSE_PARAN;
            } 
            // fall through
          case 52: break;
          case 15: 
            { yyPushState(IN_SINGLE_QUOTE_STRING);
                                return ElvishTypes.SINGLE_QUOTE;
            } 
            // fall through
          case 53: break;
          case 16: 
            { yyPushState(IN_DOUBLE_QUOTE_STRING);
                                return ElvishTypes.DOUBLE_QUOTE;
            } 
            // fall through
          case 54: break;
          case 17: 
            { return ElvishTypes.AMPERSAND;
            } 
            // fall through
          case 55: break;
          case 18: 
            { return ElvishTypes.TILDA;
            } 
            // fall through
          case 56: break;
          case 19: 
            { return ElvishTypes.REF_MARKER;
            } 
            // fall through
          case 57: break;
          case 20: 
            { return ElvishTypes.PIPE;
            } 
            // fall through
          case 58: break;
          case 21: 
            { return ElvishTypes.TEXT;
            } 
            // fall through
          case 59: break;
          case 22: 
            { yyPopState();
                                return ElvishTypes.SINGLE_QUOTE;
            } 
            // fall through
          case 60: break;
          case 23: 
            { yyPopState();
                                return ElvishTypes.DOUBLE_QUOTE;
            } 
            // fall through
          case 61: break;
          case 24: 
            { return ElvishTypes.CONTINUATION;
            } 
            // fall through
          case 62: break;
          case 25: 
            { return ElvishTypes.KEYWORD_IF;
            } 
            // fall through
          case 63: break;
          case 26: 
            { return ElvishTypes.KEYWORD_FN;
            } 
            // fall through
          case 64: break;
          case 27: 
            { return ElvishTypes.AT_VARIABLE;
            } 
            // fall through
          case 65: break;
          case 28: 
            { return ElvishTypes.VAR_REF;
            } 
            // fall through
          case 66: break;
          case 29: 
            { return ElvishTypes.ESCAPED_QUOTED_TEXT;
            } 
            // fall through
          case 67: break;
          case 30: 
            { return ElvishTypes.INVALID_ESCAPED_QUOTED_TEXT;
            } 
            // fall through
          case 68: break;
          case 31: 
            { return ElvishTypes.KEYWORD_FOR;
            } 
            // fall through
          case 69: break;
          case 32: 
            { return ElvishTypes.KEYWORD_TRY;
            } 
            // fall through
          case 70: break;
          case 33: 
            { return ElvishTypes.KEYWORD_DEL;
            } 
            // fall through
          case 71: break;
          case 34: 
            { return ElvishTypes.KEYWORD_ELSE;
            } 
            // fall through
          case 72: break;
          case 35: 
            { return ElvishTypes.KEYWORD_ELIF;
            } 
            // fall through
          case 73: break;
          case 36: 
            { return ElvishTypes.KEYWORD_WHILE;
            } 
            // fall through
          case 74: break;
          case 37: 
            { return ElvishTypes.KEYWORD_EXCEPT;
            } 
            // fall through
          case 75: break;
          case 38: 
            { return ElvishTypes.KEYWORD_FINALLY;
            } 
            // fall through
          case 76: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
