package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public interface ASTNode {
    void accept(NodeVisitor visitor);
    List<ASTNode> children = null;
}
