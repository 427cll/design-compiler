package parser.nodes;

import lexer.token.Token;
import analyzer.NodeVisitor;

public class Identifier implements ASTNode{
    String name;
    Token token;
    public Identifier(Token token) {
        this.token = token;
        this.name = token.getValue();
    }

    public String getName() {
        return name;
    }

    public Token getToken() {
        return token;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitIdentifier(this);
    }


}
