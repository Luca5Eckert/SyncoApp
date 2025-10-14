package com.api.blog.module.user.domain.exception.email;

public class EmailBlankException extends RuntimeException {
    public EmailBlankException(String message) {
        super(message);
    }

    public EmailBlankException() {
      super("The email can't be blank");
    }
}
