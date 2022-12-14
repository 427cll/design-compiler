package parser.nodes;

import visitor.NodeVisitor;
import lexer.token.Token;

public abstract class ASTNode {

    Token token;
    
    public abstract void accept(NodeVisitor visitor);

    public Token getToken() {
        return token;
    }
}
