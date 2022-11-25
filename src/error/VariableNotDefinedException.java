package error;

import parser.nodes.Identifier;

public class VariableNotDefinedException extends Error {
    public VariableNotDefinedException(Identifier id) {
        showError(id.getToken(), "Variable"+ id.getName() +" is not Defined");
    }

}
