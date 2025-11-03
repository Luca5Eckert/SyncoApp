package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class EmailNotUniqueDomainException extends UserDomainException {
    public EmailNotUniqueDomainException(String message) {
        super(message);
    }

    public EmailNotUniqueDomainException() {
        super("The email is not unique");
    }

}
