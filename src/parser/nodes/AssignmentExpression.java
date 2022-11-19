package parser.nodes;

import visitor.NodeVisitor;

import java.util.Map;

public class AssignmentExpression implements ASTNode{
    Map<String,Object> left;
    String operator;
    Map<String,Object> right;

    public AssignmentExpression(Map<String, Object> left, String operator, Map<String, Object> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAssignmentExpression(this);
    }
}
