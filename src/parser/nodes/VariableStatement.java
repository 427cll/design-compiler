package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class VariableStatement implements ASTNode{
    Type type;
    List<VariableDecl> declarations;

    public VariableStatement(Type type, List<VariableDecl> declarations) {
        this.type = type;
        this.declarations = declarations;
    }

    @Override
    public String toString() {
        return "VariableStatement{" +
                "type=" + type +
                ", declarations=" + declarations +
                '}';
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitVariableStatement(this);
    }
}
