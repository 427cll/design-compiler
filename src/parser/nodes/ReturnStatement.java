package parser.nodes;

import lexer.token.Token;
import visitor.NodeVisitor;

public class ReturnStatement implements ASTNode {
    Token token; //token日后可以用来保存识别该 ASTNode 的类型
    Integer right;

    public ReturnStatement(Token token, Integer right) {
        this.token = token;
        this.right = right;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitReturnStatement(this);
    }
}
