package parser.nodes;


import visitor.NodeVisitor;
import analyzer.env.ParameterSymbol;

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

    public ParameterSymbol parameterSymbol;
}
