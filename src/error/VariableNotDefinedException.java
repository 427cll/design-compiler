package error;

import parser.nodes.Identifier;
import utils.BeautifulError;

public class VariableNotDefinedException extends RuntimeException{
    public VariableNotDefinedException(Identifier id) {
        BeautifulError.print(id.getToken(), "Variable"+ id.getName() +" is not Defined");
    }

}
