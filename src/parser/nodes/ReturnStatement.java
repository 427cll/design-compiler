package parser.nodes;

import visitor.NodeVisitor;
import lexer.token.Token;

public class ReturnStatement extends ASTNode {
    Token token; //token日后可以用来保存识别该 ASTNode 的类型
    ASTNode right;

    public ReturnStatement(Token token, ASTNode right) {
        this.token = token;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitReturnStatement(this);
    }

    public ASTNode getRight() {
        return right;
    }
}
