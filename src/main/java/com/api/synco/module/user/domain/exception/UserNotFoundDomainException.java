package com.api.synco.module.user.domain.exception;

public class UserNotFoundDomainException extends UserDomainException {
    public UserNotFoundDomainException(String email) {
        super("User not found by email: " + email);
    }

    public UserNotFoundDomainException(long id) {
        super("User not found by id: " + id);
    }

}
