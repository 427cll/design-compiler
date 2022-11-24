package parser.nodes;

import analyzer.NodeVisitor;
import lexer.token.Token;

public class BooleanLiteral implements ASTNode{
    Boolean value;
    Token token;

    public BooleanLiteral(Token token) {
        this.value = token.getValue().equals("true");
        this.token = token;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBooleanLiteral();
    }
}
