package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class NumericLiteral extends ASTNode {
    Integer value;

    public NumericLiteral(Token token) {
        this.token = token;
        this.value = Integer.parseInt(token.getValue());

    }

    public Integer getValue() {
        return value;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumericLiteral(this);
    }

}
