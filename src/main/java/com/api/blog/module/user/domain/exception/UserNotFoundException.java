package com.api.blog.module.user.domain.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(long id) {
        super("User not found by id: " + id);
    }

}
