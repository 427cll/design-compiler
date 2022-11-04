package parser.nodes;

import lexer.token.Token;

public class ReturnStatement implements ASTNode {
    Token token; //token日后可以用来保存识别该 ASTNode 的类型
    Integer right;

    public ReturnStatement(Token token, Integer right) {
        this.token = token;
        this.right = right;
    }

    @Override
    public String toString() {
        return "ReturnStatement{" +
                "token=" + token +
                ", right=" + right +
                '}';
    }
}
