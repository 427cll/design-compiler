package error;

import lexer.token.Token;

public class UnexpectedTokenException extends Error {
    public UnexpectedTokenException(Token token){
        showError(token,"Unexpected token type: '" + token.getType()+"'");
    }
}
