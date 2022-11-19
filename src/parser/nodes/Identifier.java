package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class Identifier implements ASTNode{
    String name;
    Token token;

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

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitIdentifier(this);
    }
}
