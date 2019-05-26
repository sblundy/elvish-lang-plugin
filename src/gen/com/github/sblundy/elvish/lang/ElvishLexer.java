/* The following code was generated by JFlex 1.7.0-1 tweaked for IntelliJ platform */

package com.github.sblundy.elvish.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.sblundy.elvish.psi.ElvishTypes;
import com.intellij.psi.TokenType;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0-1
 * from the specification file <tt>/Users/steve/Projects/elivish-lang-plugin/src/main/grammars/Elvish.flex</tt>
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
   * Chosen bits are [8, 6, 7]
   * Total runtime size is 1040 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>13]|((ch>>7)&0x3f)]|(ch&0x7f)];
  }

  /* The ZZ_CMAP_Z table has 136 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\207\100");

  /* The ZZ_CMAP_Y table has 128 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\177\200");

  /* The ZZ_CMAP_A table has 256 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\35\1\1\2\0\1\34\22\0\1\35\1\26\1\45\1\2\1\50\1\31\1\46\1\44\1\42\1"+
    "\43\1\32\1\31\1\33\1\31\1\33\1\31\13\33\1\0\1\30\1\27\1\30\1\0\33\33\1\40"+
    "\1\3\1\41\1\32\1\33\1\0\1\21\1\4\1\15\1\25\1\5\1\11\1\33\1\13\1\10\2\33\1"+
    "\6\1\33\1\20\1\23\1\16\1\33\1\24\1\7\1\17\1\33\1\4\1\12\1\14\1\22\1\33\1\36"+
    "\1\51\1\37\1\47\201\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\3\0\1\1\1\2\1\3\1\1\10\4\1\5\3\6"+
    "\1\2\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\24\1\26\1\3\2\27\2\4\1\30\5\4\1\6"+
    "\1\31\4\4\1\32\1\4\1\33\1\34\1\35\1\36"+
    "\5\4\1\37\1\40\1\4\1\41";

  private static int [] zzUnpackAction() {
    int [] result = new int[69];
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
    "\0\0\0\52\0\124\0\176\0\176\0\250\0\322\0\374"+
    "\0\u0126\0\u0150\0\u017a\0\u01a4\0\u01ce\0\u01f8\0\u0222\0\u024c"+
    "\0\u0276\0\374\0\176\0\u02a0\0\u02ca\0\176\0\176\0\176"+
    "\0\176\0\176\0\176\0\176\0\176\0\176\0\176\0\176"+
    "\0\176\0\176\0\u02f4\0\u031e\0\176\0\u0348\0\176\0\u0372"+
    "\0\u039c\0\u03c6\0\374\0\u03f0\0\u041a\0\u0444\0\u046e\0\u0498"+
    "\0\u04c2\0\176\0\u04ec\0\u0516\0\u0540\0\u056a\0\374\0\u0594"+
    "\0\374\0\374\0\374\0\374\0\u05be\0\u05e8\0\u0612\0\u063c"+
    "\0\u0666\0\374\0\374\0\u0690\0\374";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[69];
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
    "\1\4\1\5\1\6\1\7\1\10\1\11\2\10\1\12"+
    "\1\13\1\14\4\10\1\15\5\10\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\10\1\24\1\25\1\26\1\27"+
    "\1\30\1\31\1\32\1\33\1\34\1\35\1\36\1\37"+
    "\1\40\1\41\44\42\1\43\10\42\1\44\41\42\1\45"+
    "\4\42\52\0\1\6\1\46\50\6\1\0\1\47\32\0"+
    "\1\50\21\0\23\10\2\0\1\10\1\0\1\10\22\0"+
    "\2\10\1\51\5\10\1\52\12\10\2\0\1\10\1\0"+
    "\1\10\22\0\5\10\1\53\15\10\2\0\1\10\1\0"+
    "\1\10\22\0\4\10\1\54\12\10\1\55\3\10\2\0"+
    "\1\10\1\0\1\10\22\0\7\10\1\56\13\10\2\0"+
    "\1\10\1\0\1\10\22\0\20\10\1\57\2\10\2\0"+
    "\1\10\1\0\1\10\22\0\1\10\1\60\21\10\2\0"+
    "\1\10\1\0\1\10\22\0\23\10\1\61\1\0\1\10"+
    "\1\0\1\10\45\0\1\61\31\0\1\23\17\0\1\61"+
    "\23\0\1\5\105\0\1\25\60\0\1\62\10\0\3\62"+
    "\3\0\1\62\5\0\3\62\2\0\1\62\20\0\1\62"+
    "\5\0\1\46\32\0\1\46\16\0\1\47\54\0\3\10"+
    "\1\63\1\64\16\10\2\0\1\10\1\0\1\10\22\0"+
    "\11\10\1\65\11\10\2\0\1\10\1\0\1\10\22\0"+
    "\14\10\1\66\6\10\2\0\1\10\1\0\1\10\22\0"+
    "\20\10\1\67\2\10\2\0\1\10\1\0\1\10\22\0"+
    "\4\10\1\70\16\10\2\0\1\10\1\0\1\10\22\0"+
    "\16\10\1\71\4\10\2\0\1\10\1\0\1\10\22\0"+
    "\2\10\1\72\20\10\2\0\1\10\1\0\1\10\25\0"+
    "\1\23\46\0\1\10\1\73\21\10\2\0\1\10\1\0"+
    "\1\10\22\0\5\10\1\74\15\10\2\0\1\10\1\0"+
    "\1\10\22\0\1\10\1\75\21\10\2\0\1\10\1\0"+
    "\1\10\22\0\15\10\1\76\5\10\2\0\1\10\1\0"+
    "\1\10\22\0\2\10\1\77\20\10\2\0\1\10\1\0"+
    "\1\10\22\0\12\10\1\100\10\10\2\0\1\10\1\0"+
    "\1\10\22\0\2\10\1\101\20\10\2\0\1\10\1\0"+
    "\1\10\22\0\1\10\1\102\21\10\2\0\1\10\1\0"+
    "\1\10\22\0\13\10\1\103\7\10\2\0\1\10\1\0"+
    "\1\10\22\0\2\10\1\104\20\10\2\0\1\10\1\0"+
    "\1\10\22\0\16\10\1\105\4\10\2\0\1\10\1\0"+
    "\1\10\16\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[1722];
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
    "\3\0\2\11\15\1\1\11\2\1\15\11\2\1\1\11"+
    "\1\1\1\11\12\1\1\11\23\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[69];
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
          case 34: break;
          case 2: 
            { return ElvishTypes.EOL;
            } 
            // fall through
          case 35: break;
          case 3: 
            { return ElvishTypes.COMMENT;
            } 
            // fall through
          case 36: break;
          case 4: 
            { return ElvishTypes.BAREWORD;
            } 
            // fall through
          case 37: break;
          case 5: 
            { return ElvishTypes.EQUALS;
            } 
            // fall through
          case 38: break;
          case 6: 
            { return ElvishTypes.BUILTIN_OPERATOR_FN;
            } 
            // fall through
          case 39: break;
          case 7: 
            { return TokenType.WHITE_SPACE;
            } 
            // fall through
          case 40: break;
          case 8: 
            { return ElvishTypes.OPEN_BRACE;
            } 
            // fall through
          case 41: break;
          case 9: 
            { return ElvishTypes.CLOSE_BRACE;
            } 
            // fall through
          case 42: break;
          case 10: 
            { return ElvishTypes.OPEN_BRACKET;
            } 
            // fall through
          case 43: break;
          case 11: 
            { return ElvishTypes.CLOSE_BRACKET;
            } 
            // fall through
          case 44: break;
          case 12: 
            { return ElvishTypes.OPEN_PARAN;
            } 
            // fall through
          case 45: break;
          case 13: 
            { return ElvishTypes.CLOSE_PARAN;
            } 
            // fall through
          case 46: break;
          case 14: 
            { yybegin(IN_SINGLE_QUOTE_STRING);
                                return ElvishTypes.SINGLE_QUOTE;
            } 
            // fall through
          case 47: break;
          case 15: 
            { yybegin(IN_DOUBLE_QUOTE_STRING);
                                return ElvishTypes.DOUBLE_QUOTE;
            } 
            // fall through
          case 48: break;
          case 16: 
            { return ElvishTypes.AMPERSAND;
            } 
            // fall through
          case 49: break;
          case 17: 
            { return ElvishTypes.TILDA;
            } 
            // fall through
          case 50: break;
          case 18: 
            { return ElvishTypes.REF_MARKER;
            } 
            // fall through
          case 51: break;
          case 19: 
            { return ElvishTypes.PIPE;
            } 
            // fall through
          case 52: break;
          case 20: 
            { return ElvishTypes.TEXT;
            } 
            // fall through
          case 53: break;
          case 21: 
            { yybegin(YYINITIAL);
                                return ElvishTypes.SINGLE_QUOTE;
            } 
            // fall through
          case 54: break;
          case 22: 
            { yybegin(YYINITIAL);
                                return ElvishTypes.DOUBLE_QUOTE;
            } 
            // fall through
          case 55: break;
          case 23: 
            { return ElvishTypes.CONTINUATION;
            } 
            // fall through
          case 56: break;
          case 24: 
            { return ElvishTypes.KEYWORD_IF;
            } 
            // fall through
          case 57: break;
          case 25: 
            { return ElvishTypes.ESCAPED_QUOTED_TEXT;
            } 
            // fall through
          case 58: break;
          case 26: 
            { return ElvishTypes.KEYWORD_FOR;
            } 
            // fall through
          case 59: break;
          case 27: 
            { return ElvishTypes.KEYWORD_TRY;
            } 
            // fall through
          case 60: break;
          case 28: 
            { return ElvishTypes.KEYWORD_DEL;
            } 
            // fall through
          case 61: break;
          case 29: 
            { return ElvishTypes.KEYWORD_ELSE;
            } 
            // fall through
          case 62: break;
          case 30: 
            { return ElvishTypes.KEYWORD_ELIF;
            } 
            // fall through
          case 63: break;
          case 31: 
            { return ElvishTypes.KEYWORD_WHILE;
            } 
            // fall through
          case 64: break;
          case 32: 
            { return ElvishTypes.KEYWORD_EXCEPT;
            } 
            // fall through
          case 65: break;
          case 33: 
            { return ElvishTypes.KEYWORD_FINALLY;
            } 
            // fall through
          case 66: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
