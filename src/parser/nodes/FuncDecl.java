package parser.nodes;

import java.util.List;

public class FuncDecl {
    Type returnType;
    String funcName;
    List<FormalParam> formalParamList;
    BlockStatement blockStatement;

    public FuncDecl(Type type, String funcName, List<FormalParam> formalParamList, BlockStatement blockStatement) {
        this.returnType = type;
        this.funcName = funcName;
        this.formalParamList = formalParamList;
        this.blockStatement = blockStatement;
    }

    @Override
    public String toString() {
        return "FuncDecl{" +
                "returnType=" + returnType +
                ", funcName='" + funcName + '\'' +
                ", formalParamList=" + formalParamList +
                ", blockStatement=" + blockStatement +
                '}';
    }
}
