package analyzer.env;

import parser.nodes.BlockStatement;
import parser.nodes.FormalParam;
import parser.nodes.Identifier;
import parser.nodes.Type;

import java.util.List;

public class FunctionSymbol extends Symbol{
    String funcName;
    List<FormalParam> formalParamList;
    BlockStatement blockStatement;
    Type type;


    public FunctionSymbol(Identifier id, Type type) {
        super(id, type);
    }
}
