package error;

import lexer.token.Token;
import utils.BeautifulError;

public class UnexpectedTokenException extends RuntimeException{
    public UnexpectedTokenException(Token token){
        BeautifulError.print(token,"Unexpected token type: '" + token.getType() + "';");
    }
}
