package parser.nodes;

import visitor.NodeVisitor;

import java.util.Map;

public class VariableDecl implements ASTNode{

    Identifier id;
    Map<String,Object> init;

    public VariableDecl(Identifier id, Map<String, Object> init) {
        this.id = id;
        this.init = init;
    }

    @Override
    public String toString() {
        return "VariableDecl{" +
                "id=" + id +
                ", init=" + init +
                '}';
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitVariableDecl(this);
    }
}
