package parser.nodes;

public class AssignmentStatement {
    Identifier left;
    String operator;
    Integer right;

    public AssignmentStatement(Identifier left, String operator, Integer right) {
        this.left = left;
        this.operator = operator;
        this.right = right;

    }
}
