package parser.nodes;

import java.util.List;

public class Program {
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
}
