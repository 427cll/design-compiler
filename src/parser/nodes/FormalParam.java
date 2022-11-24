package parser.nodes;


import analyzer.NodeVisitor;

public class FormalParam implements ASTNode{
    Type type;
    Identifier var;

    public FormalParam(Type type, Identifier var) {
        this.type = type;
        this.var = var;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitFormalParam(this);
    }
}
