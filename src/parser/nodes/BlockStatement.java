package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class BlockStatement implements ASTNode{

    List<ASTNode> body;

    public BlockStatement(List<ASTNode> body) {
        this.body = body;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBlockStatement(this);
    }
}
