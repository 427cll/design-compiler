package parser.nodes;

import visitor.NodeVisitor;
import lexer.token.Token;

public class ReturnStatement extends ASTNode {
    Token token; //token日后可以用来保存识别该 ASTNode 的类型
    ASTNode right;
    String funcName;

    public ReturnStatement(Token token, ASTNode right, String funcName) {
        this.token = token;
        this.right = right;
        this.funcName = funcName;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitReturnStatement(this);
    }

    public ASTNode getRight() {
        return right;
    }

    public String getFuncName() {
        return funcName;
    }
}
