package parser.nodes;

import lexer.token.Token;
import analyzer.NodeVisitor;

public class Identifier extends ASTNode{
    String name;

    public Identifier(Token token) {
        this.token = token;
        this.name = token.getValue();
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitIdentifier(this);
    }


}
