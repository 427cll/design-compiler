package parser.nodes;


import analyzer.NodeVisitor;
import lexer.token.Token;

public class BinaryExpression extends ASTNode {
    String operator;
    ASTNode left;
    ASTNode right;

    public BinaryExpression(Token operator, ASTNode left, ASTNode right) {
        this.operator = operator.getValue();
        this.left = left;
        this.right = right;
        this.token = left.getToken();//TODO
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBinaryExpression(this);
    }


    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
}
