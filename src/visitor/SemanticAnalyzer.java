package visitor;

import parser.nodes.*;

import java.util.List;

public class SemanticAnalyzer implements NodeVisitor {

    public void analyze(Program program){
        List<FuncDecl> funcNodes = program.getFuncNodes();
        for (FuncDecl funcNode : funcNodes) {
            funcNode.accept(this);
        }

    }


    @Override
    public void visitAdditiveExpression(AdditiveExpression additiveExpression) {

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
}
