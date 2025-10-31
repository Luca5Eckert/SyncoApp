package com.api.synco.infrastructure.exception.token;

public class TokenInvalidException extends TokenException {
    public TokenInvalidException() {
        super("Token is invalid");
    }
    public TokenInvalidException(String message) {
        super(message);
    }
}
