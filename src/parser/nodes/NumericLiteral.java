package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class NumericLiteral implements ASTNode{

    Token token;
    public NumericLiteral(Token numericLiteral) {
        this.token = numericLiteral;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitNumericLiteral();
    }
}
