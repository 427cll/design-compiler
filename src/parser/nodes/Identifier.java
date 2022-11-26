package parser.nodes;

import analyzer.env.Symbol;
import lexer.token.Token;
import visitor.NodeVisitor;

public class Identifier extends ASTNode{
    Symbol symbol;
    String name;

    public Identifier(Token token) {
        this.token = token;
        this.name = token.getValue();
    }

    public String getName() {

        return name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitIdentifier(this);
    }


}
