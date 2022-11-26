package analyzer.env;

import parser.nodes.Identifier;
import parser.nodes.Type;

public class ParameterSymbol extends Symbol {

    public ParameterSymbol(Identifier id, Type type, Integer offset) {
        super(id, type);
        this.offset = offset;
    }

}
