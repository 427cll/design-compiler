package analyzer.env;

import parser.nodes.Identifier;
import parser.nodes.Type;

public class VariableSymbol extends Symbol {
    Integer offset;

    public VariableSymbol(Identifier id, Type type, Integer offset) {
        super(id, type);
        this.offset = offset;
    }
}
