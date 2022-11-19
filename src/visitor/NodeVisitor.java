package visitor;

import parser.nodes.*;

public interface NodeVisitor {
    void visitAdditiveExpression(AdditiveExpression additiveExpression);
    void visitAssignmentExpression(AssignmentExpression  assignmentExpression);
    void visitBlockStatement(BlockStatement blockStatement);
    void visitFormalParam(FormalParam formalParam);
    void visitFuncDecl(FuncDecl funcDecl);
    void visitIdentifier(Identifier identifier);
    void visitProgram(Program program);
    void visitReturnStatement(ReturnStatement returnStatement);
    void visitType(Type type);
    void visitVariableDecl(VariableDecl variableDecl);
    void visitVariableStatement(VariableStatement variableStatement);
}
