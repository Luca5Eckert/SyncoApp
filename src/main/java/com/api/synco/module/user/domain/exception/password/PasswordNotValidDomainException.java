package com.api.synco.module.user.domain.exception.password;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class PasswordNotValidDomainException extends UserDomainException {
    public PasswordNotValidDomainException(String message) {
        super(message);
    }

    public PasswordNotValidDomainException() {
        super("The password is not valid");
    }
}
