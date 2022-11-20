package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class NumericLiteral implements ASTNode{
    Integer value;
    public NumericLiteral(Token numericLiteral) {
        this.value = Integer.parseInt(numericLiteral.getValue());
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumericLiteral();
    }
}
