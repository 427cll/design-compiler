package parser.nodes;


import visitor.NodeVisitor;

import java.util.Map;

public class AdditiveExpression implements ASTNode{
    String operator;
    Map<String, Object> left;
    Map<String, Object> right;

    public AdditiveExpression(Map<String, Object> left, String operator, Map<String, Object> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitAdditiveExpression(this);
    }
}
