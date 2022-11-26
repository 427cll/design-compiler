package visitor;

import parser.nodes.*;

public interface NodeVisitor {
    void visitProgram(Program program);

    void visitFuncDecl(FuncDecl funcDecl);
    void visitFormalParam(FormalParam formalParam);
    void visitBlockStatement(BlockStatement blockStatement);

    void visitAssignmentExpression(AssignmentExpression  assignmentExpression);
    void visitBinaryExpression(BinaryExpression additiveExpression);
    void visitIdentifier(Identifier identifier);
    void visitReturnStatement(ReturnStatement returnStatement);
    void visitVariableStatement(VariableStatement variableStatement);
    void visitVariableDecl(VariableDecl variableDecl);

    void visitType(Type type);

    void visitNumericLiteral(NumericLiteral numericLiteral);
    void visitBooleanLiteral(BooleanLiteral booleanLiteral);
}
