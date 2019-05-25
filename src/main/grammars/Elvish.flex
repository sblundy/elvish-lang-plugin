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

KEYWORD_ELSE = else
KEYWORD_ELIF = elif
/*
KEYWORD_EXCEPT = except
KEYWORD_FINALLY = finally
KEYWORD_FOR = for //else|elif|except|finally|for|if|try
 */
KEYWORD_IF = if
/*
KEYWORD_TRY = try
 */
STRING_CMP_BUILTINS=(([<>=!]=)|[<>])s
NUMERIC_CMP_BUILTINS=(([<>=!]=)|[<>])
NUMERIC_BUILTINS=\+|-|\*|\/|%|\^
BAREWORD=[a-zA-Z0-9\-_:%+,\.\/@!]+
EOL="\r"|"\n"|"\r\n"
INLINE_WHITESPACE_CHAR=[ \t]
INLINE_WHITESPACE={INLINE_WHITESPACE_CHAR}+

%xstate IN_SINGLE_QUOTE_STRING

%%

<YYINITIAL> {
  {INLINE_WHITESPACE}       { return TokenType.WHITE_SPACE; }
  {COMMENT}                 { return ElvishTypes.COMMENT; }

  "{"                       { return ElvishTypes.OPEN_BRACE; }
  "}"                       { return ElvishTypes.CLOSE_BRACE; }
  "["                       { return ElvishTypes.OPEN_BRACKET; }
  "]"                       { return ElvishTypes.CLOSE_BRACKET; }
  "("                       { return ElvishTypes.OPEN_PARAN; }
  ")"                       { return ElvishTypes.CLOSE_PARAN; }
  "'"                       {
                                yybegin(IN_SINGLE_QUOTE_STRING);
                                return ElvishTypes.SINGLE_QUOTE;
                            }
  {STRING_CMP_BUILTINS}     { return ElvishTypes.BUILTIN_OPERATOR_FN; }
  {NUMERIC_CMP_BUILTINS}    { return ElvishTypes.BUILTIN_OPERATOR_FN; }
  {NUMERIC_BUILTINS}        { return ElvishTypes.BUILTIN_OPERATOR_FN; }
  "\""                      { return ElvishTypes.DOUBLE_QUOTE; }
  "&"                       { return ElvishTypes.AMPERSAND; }
  "~"                       { return ElvishTypes.TILDA; }
  "="                       { return ElvishTypes.EQUALS; }
  "$"                       { return ElvishTypes.REF_MARKER; }
  "|"                       { return ElvishTypes.PIPE; }

  {CONTINUATION}            { return ElvishTypes.CONTINUATION; }

  {KEYWORD_ELIF}            { return ElvishTypes.KEYWORD_ELIF; }
  {KEYWORD_ELSE}            { return ElvishTypes.KEYWORD_ELSE; }
/*
  {KEYWORD_EXCEPT}          { return ElvishTypes.KEYWORD_EXCEPT; }
  {KEYWORD_FINALLY}         { return ElvishTypes.KEYWORD_FINALLY; }
  {KEYWORD_FOR}             { return ElvishTypes.KEYWORD_FOR; }
 */
  {KEYWORD_IF}              { return ElvishTypes.KEYWORD_IF; }
/*
  {KEYWORD_TRY}             { return ElvishTypes.KEYWORD_TRY; }
 */
  {BAREWORD}                { return ElvishTypes.BAREWORD; }
  {EOL}                     { return ElvishTypes.EOL; }
}

<IN_SINGLE_QUOTE_STRING> {
  "''"                      {
                                return ElvishTypes.ESCAPED_SINGLE_QUOTED_TEXT;
                            }
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