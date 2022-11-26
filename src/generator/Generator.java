package generator;

import analyzer.Offset;
import parser.nodes.*;
import visitor.NodeVisitor;

public class Generator implements NodeVisitor {
    static String[] parameter_registers = {"rdi", "rsi", "rdx", "rcx", "r8", "r9"};

    public void generate(Program program) {
        for (FuncDecl funcNode : program.getFuncDecls()) {
            funcNode.accept(this);
        }
    }


    @Override
    public void visitProgram(Program program) {
        // PASS
    }

    @Override
    public void visitFuncDecl(FuncDecl funcDecl) {
        Offset.sum = 0;
        String funcName = funcDecl.getIdentifier().getName();
        System.out.println("    .text");
        System.out.println("    .globl " + funcName);
        System.out.println(funcName + ":");

        System.out.println("    push %rbp");
        System.out.println("    mov %rsp, %rbp");
        int stackSize = this.alignTo(funcDecl.getOffset(), 16);// TODO
        System.out.println("    sub $" + stackSize + ", %rsp");

        int i = 0;
        for (FormalParam formalParam : funcDecl.getFormalParamList()) {
            int paramOffset = formalParam.getParameterSymbol().getOffset();
            System.out.println("    mov %" + parameter_registers[i] + ", " + paramOffset + "(%rbp)");
            i += 1;
        }

        funcDecl.getBlockStatement().accept(this);
        System.out.println("." + funcName + ".return:");

        System.out.println("    mov %rbp, %rsp");
        System.out.println("    pop %rbp");
        System.out.println("    ret");
    }

    private int alignTo(int n, int align) {
        return ((n + align - 1) / align) * align;
    }

    @Override
    public void visitFormalParam(FormalParam formalParam) {
        // PASS
    }

    @Override
    public void visitBlockStatement(BlockStatement blockStatement) {
        for (ASTNode astNode : blockStatement.getBody()) {
            astNode.accept(this);
        }
    }

    @Override
    public void visitAssignmentExpression(AssignmentExpression assignmentExpression) {
        ASTNode left = assignmentExpression.getLeft();
        int offset = ((Identifier) left).getSymbol().getOffset();
        System.out.println("    lea " + offset + "(%rbp), %rax");
        System.out.println("    push %rax");

        assignmentExpression.getRight().accept(this);

        System.out.println("    pop %rdi");
        System.out.println("    mov %rax, (%rdi)");
    }

    @Override
    public void visitBinaryExpression(BinaryExpression binaryExpression) {
        binaryExpression.getRight().accept(this);
        System.out.println("    push %rax");
        binaryExpression.getLeft().accept(this);
        System.out.println("    pop %rdi");
        switch (binaryExpression.getOperator().getType()) {
            case TK_PLUS -> System.out.println("    add %rdi, %rax");
            case TK_MINUS -> System.out.println("TODO");
            // etc.
        }


    }

    @Override
    public void visitIdentifier(Identifier identifier) {
        Integer offset = identifier.getSymbol().getOffset();
        System.out.println("    lea " + offset + "(%rbp), %rax");
        System.out.println("    mov (%rax), %rax");
    }

    @Override
    public void visitReturnStatement(ReturnStatement returnStatement) {
        returnStatement.getRight().accept(this);
        System.out.println("    jmp .{node.function_name}.return");
    }

    /**
     * int a = 3;
     * int b = 1, c = 2;
     *
     * @param variableStatement
     */
    @Override
    public void visitVariableStatement(VariableStatement variableStatement) {
        for (VariableDecl declaration : variableStatement.getDeclarations()) {
            declaration.accept(this);
        }

    }

    /**
     * a = 3;
     * b = 2;
     *
     * @param variableDecl
     */
    @Override
    public void visitVariableDecl(VariableDecl variableDecl) {
        if (variableDecl.getInit() != null) {

            Integer offset = variableDecl.getId().getSymbol().getOffset();
            System.out.println("    lea " + offset + "(%rbp), %rax");
            System.out.println("    push %rax");

            variableDecl.getInit().accept(this);
            System.out.println("    pop %rdi");
            System.out.println("    mov %rax, (%rdi)");
        }


    }

    @Override
    public void visitType(Type type) {
        // PASS
    }

    @Override
    public void visitNumericLiteral(NumericLiteral numericLiteral) {
        String value = numericLiteral.getToken().getValue();
        System.out.println("    mov $" + value + ", %rax");
    }

    @Override
    public void visitBooleanLiteral(BooleanLiteral booleanLiteral) {
        if (booleanLiteral.getValue())
            System.out.println("    mov $1, %rax");
        else
            System.out.println("    mov $0, %rax");
    }


}
