package com.api.synco.module.user.domain.exception.email;

import com.api.synco.module.user.domain.exception.UserDomainException;

public class EmailLengthDomainException extends UserDomainException {

    public EmailLengthDomainException(String message) {
        super(message);
    }

    public EmailLengthDomainException(int length) {
        super("The email length is invalid. The maximum caracters is " + length);
    }

}
