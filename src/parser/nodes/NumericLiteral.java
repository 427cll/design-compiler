package parser.nodes;

import lexer.token.Token;
import analyzer.NodeVisitor;

public class NumericLiteral implements ASTNode {
    Integer value;
    Token token;

    public NumericLiteral(Token numericLiteral) {
        this.token = numericLiteral;
        this.value = Integer.parseInt(numericLiteral.getValue());

    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumericLiteral();
    }
}
