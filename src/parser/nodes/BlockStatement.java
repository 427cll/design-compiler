package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class BlockStatement implements ASTNode{

    List<Object> body;

    public BlockStatement(List<Object> body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "BlockStatement{" +
                "body=" + body +
                '}';
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBlockStatement(this);
    }
}
