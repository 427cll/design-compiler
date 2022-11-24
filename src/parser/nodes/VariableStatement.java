package parser.nodes;

import analyzer.NodeVisitor;

import java.util.List;

public class VariableStatement implements ASTNode{
    Type type;
    List<VariableDecl> declarations;

    public VariableStatement(Type type, List<VariableDecl> declarations) {
        this.type = type;
        this.declarations = declarations;
    }


    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitVariableStatement(this);
    }

    public List<VariableDecl> getDeclarations() {
        return declarations;
    }

    public Type getType() {
        return type;
    }
}
