package lexer;

import error.Error;
import lexer.token.Token;
import lexer.token.TokenType;
import utils.CharCheck;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String text;
    private final List<Token> tokens;
    private int position;
    private String currChar;

    List<String> allTokenValues = TokenType.listValues();

    //成员变量直接赋值和在构造方法中赋值一个样的
    public Lexer(String text) {
        this.text = text;
        tokens = new ArrayList<>();//[]
        position = 0;
        currChar = String.valueOf(text.charAt(position));
    }

    public List<Token> gatherAllTokens() {
        Token token = getNextToken();
        this.tokens.add(token);

        while (token.getType() != TokenType.TK_EOF) {
            token = getNextToken();
            this.tokens.add(token);
        }
        return tokens;
    }

    public Token getNextToken() {
        while (hasMoreToken()) {
            if (CharCheck.isBlank(currChar)) {
                handleBlank();
                continue;
            }

            if (CharCheck.isDigit(currChar)) {
                return handleDigit();
            }

            if (CharCheck.isIdent1(currChar)) {
                return handleIdentOrReserve();
            }

            //不是字母，不是数字，只剩下符号辣
            if (CharCheck.isDoublePunct(text, position)) {
                return handleDoublePunct();
            } else if (CharCheck.isSinglePunct(currChar)) {
                return handleSinglePunct();
            } else {
                System.out.println("\033[31m" + "@ Lineno: " + Error.lineno + ", Column: " + Error.column);
                System.out.println("Unexpected Character '" + currChar + "'; ");
                System.out.println();
                String[] split = text.split("\n");
                for (int i = 0; i < split.length; i++) {
                    if (i == Error.lineno) {
                        System.out.println(split[i - 1]);
                    }
                }
                for (int i = 0; i < Error.column - 1; i++) {
                    System.out.print(" ");
                }
                System.out.println("^");

                System.exit(0);
            }

        }
        return new Token(TokenType.TK_EOF, null);
    }

    private void handleBlank() {
        while (position < text.length() && CharCheck.isBlank(currChar)) {
            advance();
        }
    }

    private Token handleDigit() {
        Integer oldColumn = Error.column;
        StringBuilder value = new StringBuilder();

        while (position < text.length() && CharCheck.isDigit(currChar)) {
            value.append(currChar);
            advance();
        }

        return new Token(TokenType.TK_INTEGER_CONST, value.toString(), Error.lineno, oldColumn, Error.column - oldColumn);
    }

    private Token handleIdentOrReserve() {
        StringBuilder value = new StringBuilder();
        Integer oldColumn = Error.column;

        while (position < text.length() && CharCheck.isIdent2(currChar)) {
            value.append(currChar);
            advance();
        }

        String valueStr = value.toString();
        if (allTokenValues.contains(valueStr)) {
            return new Token(TokenType.getTypeByValue(valueStr), valueStr, Error.lineno, oldColumn, Error.column - oldColumn);
        } else {
            return new Token(TokenType.TK_IDENT, valueStr, Error.lineno, oldColumn, Error.column - oldColumn);
        }
    }

    private Token handleSinglePunct() {
        String tokenValue = this.currChar;
        TokenType type = TokenType.getTypeByValue(tokenValue);
        Token token = new Token(type, tokenValue, Error.lineno, Error.column, 1);
        advance();
        return token;
    }

    private Token handleDoublePunct() {
        String tokenValue = this.text.substring(position, position + 1);
        TokenType tokenType = TokenType.getTypeByValue(tokenValue);
        Token token = new Token(tokenType, tokenValue, Error.lineno, Error.column, 2);
        advance();
        advance();
        return token;
    }

    private void advance() {
        if (this.currChar.equals("\n")) {
            Error.lineno++;
            Error.column = 0;
        }

        position += 1;
        if (!(position >= text.length())) {
            currChar = Character.toString(text.charAt(position));
            Error.column++;
        }
    }

    private boolean hasMoreToken() {
        return this.position < this.text.length();
    }

}
