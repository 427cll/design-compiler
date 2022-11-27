package error;

import parser.nodes.Identifier;

public class VariableHasBeenDefinedException extends Error {
    public VariableHasBeenDefinedException(Identifier id) {
        showError(id.getToken(), "Variable '"+ id.getName() +"' has been Defined");
    }
}
