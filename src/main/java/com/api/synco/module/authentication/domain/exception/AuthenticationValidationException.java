package com.api.synco.module.authentication.domain.exception;

public class AuthenticationValidationException extends AuthenticationException {

    public AuthenticationValidationException(String message) {
        super(message);
    }

    public AuthenticationValidationException(){
        super("Invalid credentials");
    }
}
