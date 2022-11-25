package parser.nodes;


import analyzer.NodeVisitor;

public class FormalParam extends ASTNode{
    Type type;
    Identifier id;

    public FormalParam(Type type, Identifier id) {
        this.type = type;
        this.id = id;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitFormalParam(this);
    }

    public Type getType() {
        return type;
    }

    public Identifier getId() {
        return id;
    }
}
