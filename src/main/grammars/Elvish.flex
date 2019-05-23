package com.github.sblundy.elvish.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.github.sblundy.elvish.psi.ElvishTypes;
import com.intellij.psi.TokenType;

%%

%class ElvishLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%unicode

LINE =                          [^\n]*
LINE_COMMENT =                  "#"{LINE}
COMMENT =                       {LINE_COMMENT}{EOL}*

CONTINUATION =                  [\\]{EOL}

BAREWORD=[a-zA-Z0-9\-_:%+,\.\/@!]+
EOL="\r"|"\n"|"\r\n"
INLINE_WHITESPACE_CHAR=[ \t]
INLINE_WHITESPACE={INLINE_WHITESPACE_CHAR}+

%xstate IN_SINGLE_QUOTE_STRING

%%

<YYINITIAL> {
  {INLINE_WHITESPACE}       { return TokenType.WHITE_SPACE; }
  {COMMENT}                 { return ElvishTypes.COMMENT; }

  "{"                       { return ElvishTypes.LEFT_BRACE; }
  "}"                       { return ElvishTypes.RIGHT_BRACE; }
  "["                       { return ElvishTypes.LEFT_BRACKET; }
  "]"                       { return ElvishTypes.RIGHT_BRACKET; }
  "("                       { return ElvishTypes.LEFT_PAREN; }
  ")"                       { return ElvishTypes.RIGHT_PAREN; }
  "'"                       {
                                yybegin(IN_SINGLE_QUOTE_STRING);
                                return ElvishTypes.SINGLE_QUOTE;
                            }
  "\""                      { return ElvishTypes.DOUBLE_QUOTE; }
  "&"                       { return ElvishTypes.AMPERSAND; }
  "~"                       { return ElvishTypes.TILDA; }
  "="                       { return ElvishTypes.EQUALS; }
  {CONTINUATION}            { return ElvishTypes.CONTINUATION; }

  {BAREWORD}                { return ElvishTypes.BAREWORD; }
  {EOL}                     { return ElvishTypes.EOL; }
}

<IN_SINGLE_QUOTE_STRING> {
  "'"                       {
                                yybegin(YYINITIAL);
                                return ElvishTypes.SINGLE_QUOTE;
                            }
  [^]                       {
                                return ElvishTypes.TEXT;
                            }
}

[^]                         { return TokenType.BAD_CHARACTER; }
<<EOF>>                     { return null; }