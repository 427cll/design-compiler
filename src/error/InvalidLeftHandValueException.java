package error;

import lexer.token.Token;


public class InvalidLeftHandValueException extends Error {
    public InvalidLeftHandValueException(Token token){
        showError(token,"Invalid left-hand side in assignment");
    }
}
