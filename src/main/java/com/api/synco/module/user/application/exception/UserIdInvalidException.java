package com.api.synco.module.user.application.exception;

public class UserIdInvalidException extends UserApplicationException {
    public UserIdInvalidException(String message) {
        super(message);
    }

    public UserIdInvalidException() {
        super("User's id is invalid");
    }

}
