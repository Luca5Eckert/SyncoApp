package com.api.blog.module.user.domain.exception.name;

public class NameBlankException extends RuntimeException {
    public NameBlankException(String message) {
        super(message);
    }

    public NameBlankException() {
      super("The name can't be blank");
    }

}
