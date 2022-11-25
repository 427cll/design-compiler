package parser.nodes;

import visitor.NodeVisitor;

import java.util.List;

public class FuncDecl extends ASTNode{
    Type returnType;
    Identifier identifier;
    List<FormalParam> formalParamList;
    BlockStatement blockStatement;
    Integer offset;

    public FuncDecl(Type type, Identifier identifier, List<FormalParam> formalParamList, BlockStatement blockStatement) {
        this.returnType = type;
        this.identifier = identifier;
        this.formalParamList = formalParamList;
        this.blockStatement = blockStatement;
    }

    @Override
    public void accept(NodeVisitor visitor) {
        visitor.visitFuncDecl(this);
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public List<FormalParam> getFormalParamList() {
        return formalParamList;
    }

    public BlockStatement getBlockStatement() {
        return blockStatement;
    }

    public Type getReturnType() {
        return returnType;
    }
}
