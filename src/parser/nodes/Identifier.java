package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class Identifier implements ASTNode{
    String name;

    public Identifier(Token token) {
        this.name = token.getValue();
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitIdentifier(this);
    }
}
