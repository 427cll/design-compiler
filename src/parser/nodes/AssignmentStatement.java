package parser.nodes;

import java.util.Map;

public class AssignmentStatement {
    Map<String,Object> left;
    String operator;
    Map<String,Object> right;

    public AssignmentStatement(Map<String, Object> left, String operator, Map<String, Object> right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}
