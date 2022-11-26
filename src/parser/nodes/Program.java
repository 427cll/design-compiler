package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class Program extends ASTNode{
    List<FuncDecl> funcDecls;

    public Program(List<FuncDecl> funcDecls) {
        this.funcDecls = funcDecls;
    }

    public List<FuncDecl> getFuncDecls() {
        return funcDecls;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitProgram(this);
    }
}
