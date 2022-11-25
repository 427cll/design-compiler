package parser.nodes;

import analyzer.NodeVisitor;

import java.util.List;

public class BlockStatement extends ASTNode{

    List<ASTNode> body;

    public BlockStatement(List<ASTNode> body) {
        this.body = body;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitBlockStatement(this);
    }

    public List<ASTNode> getBody() {
        return body;
    }
}
