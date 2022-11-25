package analyzer.env;

import error.VariableHasBeenDefinedException;
import error.VariableNotDefinedException;
import parser.nodes.Identifier;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    public Environment parent;
    Map<String, Symbol> record;

    public Environment(Environment parent) {
        this.parent = parent;
        this.record = new HashMap<>();
    }

    public void define(Symbol symbol) {
        String name = symbol.getId().getName();

        if (this.record.containsKey(name)) {
            throw new VariableHasBeenDefinedException(symbol.getId());
        }

        record.put(name, symbol);
    }

    /*public Symbol assign(Symbol symbol) {
        String name = symbol.getId().getName();
        this.resolve(symbol).record.put(name, symbol);
        return symbol;
    }*/

    public Symbol lookup(Identifier identifier) {
        String name = identifier.getName();
        return this.resolve(identifier).record.get(name);
    }

    /**
     * 传入 Id 的原因是报错时需要 token 保存的行号和列号
     */
    public Environment resolve(Identifier identifier) {
        String name = identifier.getName();

        Environment current = this;
        while (current != null) {
            if (current.record.containsKey(name))
                return current;
            current = current.parent;
        }

        throw new VariableNotDefinedException(identifier);
    }
}
