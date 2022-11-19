package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class Program implements ASTNode{
    List<FuncDecl> funcDecls;

    public Program(List<FuncDecl> funcDecls) {
        this.funcDecls = funcDecls;
    }

    public List<FuncDecl> getFuncNodes() {
        return funcDecls;
    }

    @Override
    public String toString() {
        return "Program{" +
                "funcDecls=" + funcDecls +
                '}';
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitProgram(this);
    }
}
