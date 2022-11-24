package parser.nodes;

import analyzer.NodeVisitor;

public interface ASTNode {
    void accept(NodeVisitor visitor);
}
