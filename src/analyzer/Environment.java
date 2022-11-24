package analyzer;

import error.VariableIsDefinedException;
import error.VariableNotDefinedException;
import parser.nodes.Identifier;
import parser.nodes.Type;

import java.util.HashMap;
import java.util.Map;

public class Environment {
    Environment current;
    Environment parent;
    Map<String, Map<Type,String>> record;//<name,<type,value>>
    Map<String,Integer> table;

    public Environment(Environment parent) {
        this.parent = parent;
        this.table = new HashMap<>();
    }

    public void define(Identifier id, String value) {
        String name = id.getName();

        if (this.table.containsKey(name)) {
            throw new VariableIsDefinedException(id);
        }
    }

    public Integer assign(Identifier id, Integer value) {
        String name = id.getName();
        this.resolve(id).table.put(name, value);
        return value;
    }

    public Integer lookup(Identifier id) {
        String name = id.getName();
        return this.resolve(id).table.get(name);
    }

    /**
     * 找到 name 所在的环境
     */
    public Environment resolve(Identifier id) {
        Environment current = this;
        while (current != null) {
            if (current.table.containsKey(id.getName()))
                return current;
            current = current.parent;
        }
        throw new VariableNotDefinedException(id);
    }
}
