package error;

import lexer.token.Token;

public class InvalidCharException extends Error {
    public InvalidCharException(String currChar) {
        Token token = new Token(null, null, Error.lineno, Error.column, 1);
        showError(token, "Invalid Char detected: " + currChar);
    }
}
