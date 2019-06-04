package com.github.sblundy.elvish.lang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import com.intellij.util.containers.IntArrayList;

import com.github.sblundy.elvish.psi.ElvishTypes;

%%

%class ElvishLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%unicode

%{
  final IntArrayList states = new IntArrayList();

  private void yyPushState(int newState) {
      states.add(yystate());
      yybegin(newState);
  }

  private void yyPopState() {
      int prevState = states.remove(states.size() - 1);
      yybegin(prevState);
  }
%}

LINE =                          [^\n]*
LINE_COMMENT =                  "#"{LINE}
COMMENT =                       {LINE_COMMENT}{EOL}*

CONTINUATION =                  [\\]{EOL}

ARBITRARY_8CHAR_STRING_ESCAPES = [\\]U[0-9a-fA-F]{8}
ARBITRARY_4CHAR_STRING_ESCAPES = [\\]u[0-9a-fA-F]{4}
ARBITRARY_3CHAR_STRING_ESCAPES = [\\][0-7]{3}
ARBITRARY_2CHAR_STRING_ESCAPES = [\\]x[0-9a-fA-F]{2}
CONTROL_STRING_ESCAPES = [\\][c\^][@-_]
BASIC_STRING_ESCAPES = [\\][abefnrtv\\]

KEYWORD_ELSE = else
KEYWORD_ELIF = elif
KEYWORD_WHILE = while
KEYWORD_EXCEPT = except
KEYWORD_FINALLY = finally
KEYWORD_FOR = for
KEYWORD_IF = if
KEYWORD_TRY = try
KEYWORD_DEL = del
KEYWORD_FN = fn
STRING_CMP_BUILTINS=(([<>=!]=)|[<>])s
NUMERIC_CMP_BUILTINS=(([<>=!]=)|[<>])
VARIABLE_CHAR=[[0-9a-zA-Z\-_:~]||[[\u0080-\uFFFF]&&\p{Print}]] // see parse/parse.go:713 (allowedInVariableName())
BAREWORD_CHAR=[\.\/@%+!]|{VARIABLE_CHAR}
LHS_BAREWORD_CHAR=[=]
BRACED_BAREWORD_CHAR=[,]
COMMAND_BAREWORD_CHAR=[<>*\^]|{BAREWORD_CHAR}
EOL="\r"|"\n"|"\r\n"
INLINE_WHITESPACE_CHAR=[ \t]
WHITESPACE=({INLINE_WHITESPACE_CHAR}|{EOL})+

%xstate IN_SINGLE_QUOTE_STRING IN_DOUBLE_QUOTE_STRING

%%

<YYINITIAL> {
  {WHITESPACE}              { return TokenType.WHITE_SPACE; }
  {COMMENT}                 { return ElvishTypes.COMMENT; }

  "{"                       { return ElvishTypes.OPEN_BRACE; }
  "}"                       { return ElvishTypes.CLOSE_BRACE; }
  "["                       { return ElvishTypes.OPEN_BRACKET; }
  "]"                       { return ElvishTypes.CLOSE_BRACKET; }
  "?"                       { return ElvishTypes.QUESTION; }
  "("                       { return ElvishTypes.OPEN_PARAN; }
  ")"                       { return ElvishTypes.CLOSE_PARAN; }
  "'"                       {
                                yyPushState(IN_SINGLE_QUOTE_STRING);
                                return ElvishTypes.SINGLE_QUOTE;
                            }
  "\""                       {
                                yyPushState(IN_DOUBLE_QUOTE_STRING);
                                return ElvishTypes.DOUBLE_QUOTE;
                            }
  "&"                       { return ElvishTypes.AMPERSAND; }
  "~"                       { return ElvishTypes.TILDA; }
  "="                       { return ElvishTypes.EQUALS; }
  "$""@"?{VARIABLE_CHAR}+   { return ElvishTypes.VAR_REF; }
  "|"                       { return ElvishTypes.PIPE; }

  {CONTINUATION}            { return ElvishTypes.CONTINUATION; }

  {KEYWORD_ELIF}            { return ElvishTypes.KEYWORD_ELIF; }
  {KEYWORD_ELSE}            { return ElvishTypes.KEYWORD_ELSE; }
  {KEYWORD_WHILE}           { return ElvishTypes.KEYWORD_WHILE; }
  {KEYWORD_EXCEPT}          { return ElvishTypes.KEYWORD_EXCEPT; }
  {KEYWORD_FINALLY}         { return ElvishTypes.KEYWORD_FINALLY; }
  {KEYWORD_FOR}             { return ElvishTypes.KEYWORD_FOR; }
  {KEYWORD_IF}              { return ElvishTypes.KEYWORD_IF; }
  {KEYWORD_FN}              { return ElvishTypes.KEYWORD_FN; }
  {KEYWORD_TRY}             { return ElvishTypes.KEYWORD_TRY; }
  {KEYWORD_DEL}             { return ElvishTypes.KEYWORD_DEL; }
  {VARIABLE_CHAR}+          { return ElvishTypes.VARIABLE; }
  "@"{VARIABLE_CHAR}+       { return ElvishTypes.AT_VARIABLE; }
  {BAREWORD_CHAR}+          { return ElvishTypes.BAREWORD; }
  {COMMAND_BAREWORD_CHAR}+  { return ElvishTypes.COMMAND_BAREWORD; }
  {STRING_CMP_BUILTINS}     { return ElvishTypes.COMMAND_BAREWORD; }
  {NUMERIC_CMP_BUILTINS}    { return ElvishTypes.COMMAND_BAREWORD; }
}

<IN_SINGLE_QUOTE_STRING> {
  "''"                      {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  "'"                       {
                                yyPopState();
                                return ElvishTypes.SINGLE_QUOTE;
                            }
  [^']+                       {
                                return ElvishTypes.TEXT;
                            }
}

<IN_DOUBLE_QUOTE_STRING> {
  {ARBITRARY_8CHAR_STRING_ESCAPES}  {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  {ARBITRARY_4CHAR_STRING_ESCAPES}  {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  {ARBITRARY_3CHAR_STRING_ESCAPES}  {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  {ARBITRARY_2CHAR_STRING_ESCAPES}  {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  {CONTROL_STRING_ESCAPES}  {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  {BASIC_STRING_ESCAPES}    {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  "\\\"" {
                                return ElvishTypes.ESCAPED_QUOTED_TEXT;
                            }
  "\""                       {
                                yyPopState();
                                return ElvishTypes.DOUBLE_QUOTE;
                            }
  [\\].                     {
                                return ElvishTypes.INVALID_ESCAPED_QUOTED_TEXT;
                            }
  [^\"\\]+                       {
                                return ElvishTypes.TEXT;
                            }
}

[^]                         { return TokenType.BAD_CHARACTER; }
<<EOF>>                     { return null; }