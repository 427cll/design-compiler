package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class NumericLiteral extends ASTNode {
    Integer value;

    public NumericLiteral(Token numericLiteral) {
        this.token = numericLiteral;
        this.value = Integer.parseInt(numericLiteral.getValue());

    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumericLiteral();
    }
}
