package parser.nodes;

import visitor.NodeVisitor;
import lexer.token.Token;

public class BooleanLiteral extends ASTNode{
    Boolean value;

    public BooleanLiteral(Token token) {
        this.token = token;
        this.value = token.getValue().equals("true");
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBooleanLiteral(this);
    }
}
