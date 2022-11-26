package analyzer;

import analyzer.env.*;
import error.InvalidLeftHandValueException;
import parser.nodes.*;
import visitor.NodeVisitor;

import java.util.List;

public class Analyzer implements NodeVisitor {

    Environment currentEnvironment;

    public Analyzer() {
        currentEnvironment = new Environment(null);
    }

    public void analyze(Program program) {
        List<FuncDecl> funcNodes = program.getFuncDecls();
        //每一个函数接受检查
        for (FuncDecl funcNode : funcNodes) {
            funcNode.accept(this);
        }
    }

    @Override
    public void visitProgram(Program program) {
        //pass
    }

    @Override
    public void visitFuncDecl(FuncDecl funcDecl) {
        Offset.sum = 0;
        Identifier id = funcDecl.getIdentifier();
        FunctionSymbol functionSymbol = new FunctionSymbol(id, null);

        this.currentEnvironment.define(functionSymbol);
        currentEnvironment = new Environment(currentEnvironment);

        List<FormalParam> formalParamList = funcDecl.getFormalParamList();
        for (FormalParam formalParam : formalParamList) {
            formalParam.accept(this);
        }

        BlockStatement blockStatement = funcDecl.getBlockStatement();
        blockStatement.accept(this);

        funcDecl.setOffset(Offset.sum);

        this.currentEnvironment = currentEnvironment.parent;

        functionSymbol.setBlockStatement(funcDecl.getBlockStatement());

    }

    @Override
    public void visitFormalParam(FormalParam formalParam) {
        Offset.sum += 8;
        ParameterSymbol symbol = new ParameterSymbol(formalParam.getId(), formalParam.getType(), -Offset.sum);
        formalParam.setParameterSymbol(symbol);
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
        identifier.setSymbol(lookup);
    }

    @Override
    public void visitReturnStatement(ReturnStatement returnStatement) {
        returnStatement.getRight().accept(this);
    }

    @Override
    public void visitVariableStatement(VariableStatement variableStatement) {
        Type type = variableStatement.getType();
//            declaration.accept(this);
        for (VariableDecl declaration : variableStatement.getDeclarations()) {
            Offset.sum += 8;
            VariableSymbol symbol = new VariableSymbol(declaration.getId(), type, -Offset.sum);
            this.currentEnvironment.define(symbol);
            declaration.getId().setSymbol(symbol);
            declaration.getInit().accept(this);
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
    public void visitNumericLiteral(NumericLiteral numericLiteral) {
        // PASS
    }

    @Override
    public void visitBooleanLiteral(BooleanLiteral booleanLiteral) {
        // PASS
    }
}
