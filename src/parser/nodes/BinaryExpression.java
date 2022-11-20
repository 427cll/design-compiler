package parser.nodes;


import visitor.NodeVisitor;

public class BinaryExpression implements ASTNode{
    String operator;
    ASTNode left;
    ASTNode right;

    public BinaryExpression(String operator, ASTNode left, ASTNode right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAdditiveExpression(this);
    }
}
