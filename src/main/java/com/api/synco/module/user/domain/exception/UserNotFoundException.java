package com.api.synco.module.user.domain.exception;

public class UserNotFoundException extends UserException {
    public UserNotFoundException(String email) {
        super("User not found by email: " + email);
    }

    public UserNotFoundException(long id) {
        super("User not found by id: " + id);
    }

}
