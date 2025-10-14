package com.api.blog.module.user.domain.exception.email;

public class EmailNotUniqueException extends RuntimeException {
    public EmailNotUniqueException(String message) {
        super(message);
    }

    public EmailNotUniqueException() {
        super("The email is not unique");
    }

}
