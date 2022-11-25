package parser.nodes;

import analyzer.NodeVisitor;

import java.util.List;

public class Program extends ASTNode{
    List<FuncDecl> funcDecls;

    public Program(List<FuncDecl> funcDecls) {
        this.funcDecls = funcDecls;
    }

    public List<FuncDecl> getFuncNodes() {
        return funcDecls;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitProgram(this);
    }
}
