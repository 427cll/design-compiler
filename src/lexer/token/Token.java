package lexer.token;

public class Token {
    private TokenType type;
    private String value;
    private Integer lineno;
    private Integer column;
    private Integer width;

    public Token() {

    }

    @Override
    public String toString() {
        return "lexer.token.Token{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Token(TokenType type, String value, Integer lineno, Integer column, Integer width) {
        this.type = type;
        this.value = value;
        this.lineno = lineno;
        this.column = column;
        this.width = width;
    }

    public TokenType getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public Integer getLineno() {
        return lineno;
    }

    public Integer getColumn() {
        return column;
    }
}
