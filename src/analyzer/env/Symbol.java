package analyzer.env;

import parser.nodes.Identifier;
import parser.nodes.Type;

public class Symbol {
    Type type;
    Identifier id;

    public Symbol(){}

    public Symbol(Identifier id, Type type) {
        this.id = id;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public Identifier getId() {
        return id;
    }
}
