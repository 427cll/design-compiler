package parser.nodes;

import lexer.token.Token;

public class Identifier {
    Token token;
    String name;

    public Identifier(Token token) {
        this.token = token;
        this.name = token.getValue();
    }

    @Override
    public String toString() {
        return "Identifier{" +
                "token=" + token +
                ", name='" + name + '\'' +
                '}';
    }
}
