package parser.nodes;

import analyzer.NodeVisitor;

public class AssignmentExpression implements ASTNode{
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
}
