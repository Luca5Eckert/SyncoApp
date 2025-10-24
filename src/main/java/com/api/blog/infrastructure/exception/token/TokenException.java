package com.api.blog.infrastructure.exception.token;

public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
