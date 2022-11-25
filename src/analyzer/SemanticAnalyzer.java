package analyzer;

import analyzer.env.*;
import error.InvalidLeftHandValueException;
import parser.nodes.*;

import java.util.List;

public class SemanticAnalyzer implements NodeVisitor {

    Environment currentEnvironment;

    public SemanticAnalyzer() {
        currentEnvironment = new Environment(null);
    }

    @Override
    public void visitProgram(Program program) {
        //pass
    }

    public void analyze(Program program) {
        List<FuncDecl> funcNodes = program.getFuncNodes();
        //每一个函数接受检查
        for (FuncDecl funcNode : funcNodes) {
            funcNode.accept(this);
        }
    }

    @Override
    public void visitFuncDecl(FuncDecl funcDecl) {
        Symbol symbol = new FunctionSymbol(funcDecl.getIdentifier(), funcDecl.getReturnType());
        this.currentEnvironment.define(symbol);
        currentEnvironment = new Environment(currentEnvironment);

        List<FormalParam> formalParamList = funcDecl.getFormalParamList();
        for (FormalParam formalParam : formalParamList) {
            formalParam.accept(this);
        }

        BlockStatement blockStatement = funcDecl.getBlockStatement();
        blockStatement.accept(this);

        this.currentEnvironment = currentEnvironment.parent;

    }

    @Override
    public void visitFormalParam(FormalParam formalParam) {
        Symbol symbol = new ParameterSymbol(formalParam.getId(), formalParam.getType());
        this.currentEnvironment.define(symbol);
    }

    @Override
    public void visitBinaryExpression(BinaryExpression binaryExpression) {
        ASTNode left = binaryExpression.getLeft();
        ASTNode right = binaryExpression.getRight();
        left.accept(this);
        right.accept(this);
    }

    @Override
    public void visitAssignmentExpression(AssignmentExpression assignmentExpression) {
        ASTNode left = assignmentExpression.getLeft();
        String operator = assignmentExpression.getOperator();
        ASTNode right = assignmentExpression.getRight();

        if (!(left instanceof Identifier))
            throw new InvalidLeftHandValueException(left.getToken());

        left.accept(this);
        right.accept(this);

    }

    @Override
    public void visitBlockStatement(BlockStatement blockStatement) {
        for (ASTNode statement : blockStatement.getBody()) {
            statement.accept(this);
        }
    }


    @Override
    public void visitIdentifier(Identifier identifier) {
        Symbol lookup = this.currentEnvironment.lookup(identifier);
    }


    @Override
    public void visitReturnStatement(ReturnStatement returnStatement) {
        returnStatement.getRight().accept(this);
    }

    @Override
    public void visitVariableStatement(VariableStatement variableStatement) {
        Type type = variableStatement.getType();
        for (VariableDecl declaration : variableStatement.getDeclarations()) {
//            declaration.accept(this);
            VariableSymbol symbol = new VariableSymbol(declaration.getId(), type);
            this.currentEnvironment.define(symbol);
        }
    }

    @Override
    public void visitType(Type type) {
        // PASS
    }

    @Override
    public void visitVariableDecl(VariableDecl variableDecl) {
        // PASS
    }

    @Override
    public void visitNumericLiteral() {
        // PASS
    }

    @Override
    public void visitBooleanLiteral() {
        // PASS
    }
}
