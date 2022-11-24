package error;

import parser.nodes.Identifier;

public class VariableIsDefinedException extends RuntimeException{
    public VariableIsDefinedException(Identifier id) {
        super();
    }
}
