package parser.nodes;

import visitor.NodeVisitor;

public class AssignmentExpression extends ASTNode{
    ASTNode left;
    String operator;
    ASTNode right;

    public AssignmentExpression(ASTNode left, String operator, ASTNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAssignmentExpression(this);
    }

    public ASTNode getLeft() {
        return left;
    }

    public String getOperator() {
        return operator;
    }

    public ASTNode getRight() {
        return right;
    }
}
