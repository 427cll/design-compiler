package error;

import parser.nodes.Identifier;

public class VariableNotDefinedException extends RuntimeException{
    public VariableNotDefinedException(Identifier id) {
        super();
    }
}
