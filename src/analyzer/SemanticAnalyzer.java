package analyzer;

import parser.nodes.*;

import java.util.List;

public class SemanticAnalyzer implements NodeVisitor {

    Environment currentEnvironment;

    public SemanticAnalyzer() {
        currentEnvironment = new Environment(null);
    }

    public void analyze(Program program) {
        List<FuncDecl> funcNodes = program.getFuncNodes();
        //每一个函数接受检查
        for (FuncDecl funcNode : funcNodes) {
            funcNode.accept(this);
        }
    }

    @Override
    public void visitAdditiveExpression(BinaryExpression additiveExpression) {

    }

    @Override
    public void visitAssignmentExpression(AssignmentExpression assignmentExpression) {

    }

    @Override
    public void visitBlockStatement(BlockStatement blockStatement) {

    }

    @Override
    public void visitFormalParam(FormalParam formalParam) {

    }

    @Override
    public void visitFuncDecl(FuncDecl funcDecl) {

        this.currentEnvironment.define(funcDecl.getIdentifier(), null);
        currentEnvironment = new Environment(currentEnvironment);

        List<FormalParam> formalParamList = funcDecl.getFormalParamList();
        for (FormalParam formalParam : formalParamList) {
            formalParam.accept(this);
        }

        BlockStatement blockStatement = funcDecl.getBlockStatement();
        blockStatement.accept(this);

        this.currentEnvironment=currentEnvironment.parent;

    }

    @Override
    public void visitIdentifier(Identifier identifier) {

    }

    @Override
    public void visitProgram(Program program) {

    }

    @Override
    public void visitReturnStatement(ReturnStatement returnStatement) {

    }

    @Override
    public void visitType(Type type) {

    }

    @Override
    public void visitVariableDecl(VariableDecl variableDecl) {

    }

    @Override
    public void visitVariableStatement(VariableStatement variableStatement) {

    }

    @Override
    public void visitNumericLiteral() {

    }

    @Override
    public void visitBooleanLiteral() {

    }
}
