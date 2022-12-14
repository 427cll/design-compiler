package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class VariableStatement extends ASTNode{
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
