package parser.nodes;

import analyzer.env.VariableSymbol;
import visitor.NodeVisitor;

public class VariableDecl extends ASTNode{

    Identifier id;
    ASTNode init;

    VariableSymbol symbol;

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

    public VariableSymbol getSymbol() {
        return symbol;
    }

    public void setSymbol(VariableSymbol symbol) {
        this.symbol = symbol;
    }
}
