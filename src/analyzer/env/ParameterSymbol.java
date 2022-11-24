package analyzer.env;

import parser.nodes.Identifier;
import parser.nodes.Type;

public class ParameterSymbol extends Symbol{

    public ParameterSymbol(Identifier id, Type type) {
        super(id, type);
    }
}
