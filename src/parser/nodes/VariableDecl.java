package parser.nodes;

import visitor.NodeVisitor;

public class VariableDecl extends ASTNode{

    Identifier id;
    ASTNode init;

    public VariableDecl(Identifier id, ASTNode init) {
        this.id = id;
        this.init = init;
    }


    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitVariableDecl(this);
    }

    public Identifier getId() {
        return id;
    }

    public ASTNode getInit() {
        return init;
    }
}
