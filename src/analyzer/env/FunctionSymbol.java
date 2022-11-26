package analyzer.env;

import parser.nodes.BlockStatement;
import parser.nodes.FormalParam;
import parser.nodes.Identifier;

import java.util.ArrayList;
import java.util.List;

public class FunctionSymbol extends Symbol {
    List<FormalParam> formalParamList;
    BlockStatement blockStatement;


    public FunctionSymbol(Identifier identifier, List<FormalParam> formalParams) {
        this.id = identifier;

        this.blockStatement = null;
        this.formalParamList =
                formalParams == null
                        ? new ArrayList<>()
                        : formalParams;
    }

    public void setBlockStatement(BlockStatement blockStatement) {
        this.blockStatement = blockStatement;
    }


}
