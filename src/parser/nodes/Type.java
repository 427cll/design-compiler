package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class Type implements ASTNode {
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

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitType(this);
    }
}
