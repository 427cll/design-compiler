package parser.nodes;

import lexer.token.Token;

public class Type {
    Token token;

    public Type(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "Type{" +
                "token=" + token +
                '}';
    }
}
