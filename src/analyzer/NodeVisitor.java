package analyzer;

import parser.nodes.*;

public interface NodeVisitor {
    void visitProgram(Program program);

    void visitFuncDecl(FuncDecl funcDecl);
    void visitFormalParam(FormalParam formalParam);
    void visitBlockStatement(BlockStatement blockStatement);

    void visitAssignmentExpression(AssignmentExpression  assignmentExpression);
    void visitAdditiveExpression(BinaryExpression additiveExpression);
    void visitIdentifier(Identifier identifier);
    void visitReturnStatement(ReturnStatement returnStatement);
    void visitVariableStatement(VariableStatement variableStatement);
    void visitVariableDecl(VariableDecl variableDecl);

    void visitType(Type type);

    void visitNumericLiteral();
    void visitBooleanLiteral();
}
