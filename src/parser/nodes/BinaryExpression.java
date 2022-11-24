package parser.nodes;


import analyzer.NodeVisitor;

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
        visitor.visitBinaryExpression(this);
    }


    public ASTNode getLeft() {
        return left;
    }

    public ASTNode getRight() {
        return right;
    }
}
