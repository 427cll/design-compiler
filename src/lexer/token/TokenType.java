package lexer.token;

import java.util.ArrayList;
import java.util.List;

public enum TokenType {

    TK_PLUS("+"),
    TK_MINUS("-"),
    TK_MUL("*"),
    TK_DIV("/"),
    TK_LT("<"),
    TK_GT(">"),

    TK_LPAREN("("),
    TK_RPAREN(")"),
    TK_LBRACK("["),
    TK_RBRACK("["),
    TK_LBRACE("{"),
    TK_RBRACE("}"),

    TK_COMMA(","),
    TK_SEMICOLON(";"),
    TK_ASSIGN("="),

    TK_EQ("=="),
    TK_NE("!="),
    TK_GE(">="),
    TK_LE("<="),

    TK_INT("int"),
    TK_BOOL("bool"),

    TK_RETURN("return"),
    TK_IF("if"),
    TK_THEN("then"),
    TK_ELSE("else"),
    TK_WHILE("while"),


    TK_IDENT("IDENT"),

    TK_INTEGER_CONST("INTEGER_CONST"),
    TK_BOOL_CONST_TRUE("true"),
    TK_BOOL_CONST_FALSE("false"),
    TK_EOF("EOF");

    private final String value;

    TokenType(String value) {
        this.value = value;
    }

    private String getValue() {
        return value;
    }

    /**
     * 判断标识符是否是保留字
     *
     * @return 类中所有枚举变量的值的集合
     */
    public static List<String> listValues() {
        List<String> tokenValueList = new ArrayList<>();
        TokenType[] tokenList = values();
        for (TokenType token : tokenList) {
            tokenValueList.add(token.getValue());
        }
        return tokenValueList;
    }

    public static TokenType[] listTypes() {
        return values();
    }

    /**
     * 根据 token 的值获取 token 的类型
     *
     * @param value
     * @return
     */
    public static TokenType getTypeByValue(String value) {
        TokenType[] values = values();
        for (TokenType tokenType : values) {
            if (tokenType.value.equals(value)) {
                return tokenType;
            }
        }
        return null;
    }


}
