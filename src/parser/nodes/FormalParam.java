package parser.nodes;


import visitor.NodeVisitor;

public class FormalParam implements ASTNode{
    Type type;
    Identifier var;

    public FormalParam(Type type, Identifier var) {
        this.type = type;
        this.var = var;
    }

    @Override
    public String toString() {
        return "FormalParam{" +
                "type=" + type +
                ", var=" + var +
                '}';
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitFormalParam(this);
    }
}
