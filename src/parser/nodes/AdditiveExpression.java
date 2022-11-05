package parser.nodes;


import java.util.Map;

public class AdditiveExpression {
    Map<String, Object> left;
    String operator;
    Map<String, Object> right;

    public AdditiveExpression(Map<String, Object> left, String operator, Map<String, Object> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
