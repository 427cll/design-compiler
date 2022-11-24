package error;

import parser.nodes.Identifier;
import utils.BeautifulError;

public class VariableHasBeenDefinedException extends RuntimeException {
    public VariableHasBeenDefinedException(Identifier id) {
        BeautifulError.print(id.getToken(), "Variable"+ id.getName() +" has been Defined;");
    }
}
