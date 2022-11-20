package parser.nodes;

import visitor.NodeVisitor;

public class VariableDecl implements ASTNode{

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
}
